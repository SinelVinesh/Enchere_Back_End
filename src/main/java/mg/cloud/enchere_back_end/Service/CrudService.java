package mg.cloud.enchere_back_end.Service;


import mg.cloud.enchere_back_end.exceptions.InvalidFieldValue;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                    Constructor<T> constructor = (Constructor<T>) data.getClass().getConstructor(data.getClass());
                    T tosave = constructor.newInstance(data);
                    return save(tosave);
                } catch (InvocationTargetException e) {
                    if(e.getTargetException() instanceof InvalidFieldValue targetException) {
                        return new ResponseEntity<>(new Response(targetException.getMessage()), HttpStatus.BAD_REQUEST);
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case "PUT":
                if(id.isPresent()) {
                    ID idData = id.get();
                    try {
                        Method setter = data.getClass().getMethod("setId",idData.getClass());
                        setter.invoke(data,idData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return save(data);
                }
            case "DELETE":
                if(id.isPresent()) {
                    return delete(id.get());
                }
            default:
                return ResponseEntity.notFound().build();
        }
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
        repository.save(entity);
        Response response = new Response(entity);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Response> delete(ID id) {
        repository.deleteById(id);
        return ResponseEntity.ok(new Response("Successfull delete"));
    }
}
