package mg.cloud.enchere_back_end;


import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.ServletException;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {InvalidValueException.class, ServletException.class, JsonMappingException.class})
    public ResponseEntity<Object> customError(Exception e) {
        e.printStackTrace();
        String message = "";
        if(e.getCause() instanceof JsonMappingException) {
            message = e.getCause().getCause().getMessage();
        } else {
            message = e.getMessage();
        }
        Response response = new Response(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
