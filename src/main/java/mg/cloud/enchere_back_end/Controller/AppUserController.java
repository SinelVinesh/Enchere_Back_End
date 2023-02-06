package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import mg.cloud.enchere_back_end.Service.AppUserService;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.AppUserInput;
import mg.cloud.enchere_back_end.request.AppUserUpdateInput;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("users/login")
    public ResponseEntity<?> login(@RequestBody AppUser user) throws InvalidValueException {
        AppUserToken token = appUserService.login(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("users/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) throws InvalidValueException {
        appUserService.logout(token.split(" ")[1]);
        Response response = new Response("Déconnexion réussie");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("users/register")
    public ResponseEntity<?> register(@RequestBody AppUserInput user) throws InvalidValueException {
        AppUserToken token = appUserService.register(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) throws InvalidValueException {
        return appUserService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Response> updateUser(
            @RequestBody AppUserUpdateInput appUser,
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) throws InvalidValueException {
        return appUserService.updateUser(appUser, token, id);
    }

    @GetMapping("/appUsers")
    public ResponseEntity<?> getAllUser(
    ) {
        List<AppUser> user = appUserService.getAllUsername();
        HashMap<String, Object> responseData = new HashMap<>();

        if (user != null) {
            responseData.put("data", user);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            Response error = new Response("username is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }

}
