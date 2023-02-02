package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.inputs.AppUserInput;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class AppUserAuthenticationController {
    private final AppUserService appUserService;

    public AppUserAuthenticationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser user) throws InvalidValueException {
        AppUserToken token= appUserService.login(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) throws InvalidValueException {
        appUserService.logout(token);
        Response response = new Response("Déconnexion réussie");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUserInput user) throws InvalidValueException {
        AppUserToken token = appUserService.register(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
