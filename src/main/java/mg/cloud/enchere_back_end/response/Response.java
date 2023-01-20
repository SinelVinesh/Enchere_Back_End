package mg.cloud.enchere_back_end.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String message;
    private Object data;

    public Response(String message) {
        this.message = message;
    }

    public Response(Object data) {
        this.data = data;
    }
}
