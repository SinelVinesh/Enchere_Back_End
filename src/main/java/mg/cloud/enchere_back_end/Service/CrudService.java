package mg.cloud.enchere_back_end.Service;


import jakarta.persistence.Id;
import mg.cloud.enchere_back_end.exceptions.InvalidFieldValue;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CrudService<T,ID> {
    private JpaRepository<T,ID> repository;

    @SuppressWarnings("unchecked")
    public ResponseEntity<Response> handle(String method,JpaRepository<T,ID> repository,Optional<ID> id, T data) {
        this.repository = repository;
        switch (method) {
            case "GET":
                if (id.isPresent()) {
                    return getOne(id.get());
                } else {
                    return getAll();
                }

            case "POST":
                try {
                    return save(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
                }
            case "PUT":
                if(id.isPresent()) {
                    return update(id.get(),data);
                }
                break;
            case "DELETE":
                if(id.isPresent()) {
                    return delete(id.get());
                }
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }
    private ResponseEntity<Response> getAll() {
        List<T> data = repository.findAll();
        Response response = new Response(data);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Response> getOne(ID id) {
        Optional<T> data = repository.findById(id);
        if(data.isPresent()) {
            Response response = new Response(data.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Response> save(T entity) {
        entity = repository.save(entity);
        Response response = new Response(entity);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Response> update(ID id,T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        T data = repository.findById(id).orElse(null);
        try {
            for (Field field : fields) {
                String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method setter = entity.getClass().getMethod(setterName, field.getType());
                String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method getter = entity.getClass().getMethod(getterName);
                Object value = getter.invoke(entity);
                if (value != null) {
                    setter.invoke(data, value);
                }
            }
            data = repository.save(data);
            Response response = new Response(data);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Response> delete(ID id) {
        repository.deleteById(id);
        return ResponseEntity.ok(new Response("Successfull delete"));
    }
}
