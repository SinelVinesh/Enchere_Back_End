package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.App_userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            Response response = new Response("user not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) {
        if(app_userService.logout(token)) {
            Response response = new Response("successfully logout");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            Response response = new Response("failed to logout");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
