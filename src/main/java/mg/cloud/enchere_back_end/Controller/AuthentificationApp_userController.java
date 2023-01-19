package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Response.ErrorResponse;
import mg.cloud.enchere_back_end.Service.App_userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/app_user")
public class AuthentificationApp_userController {
    private final App_userService app_userService;

    public AuthentificationApp_userController(App_userService app_userService) {
        this.app_userService = app_userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody App_user app_user){
        String token= app_userService.login(app_user);
        HashMap<String,String> responseData = new HashMap<>();
        responseData.put("token",token);
        if(token != null){
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            ErrorResponse response = new ErrorResponse(404,"user not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
}
