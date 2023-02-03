package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.AppUser;
import mg.cloud.enchere_back_end.Model.AppUserToken;
import mg.cloud.enchere_back_end.Repository.AppUserRepository;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.exceptions.InvalidValueException;
import mg.cloud.enchere_back_end.request.AppUserInput;
import mg.cloud.enchere_back_end.request.AppUserUpdateInput;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserService appUserService, CrudService<AppUser, Long> crudService,
                             AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }


    @PostMapping("users/login")
    public ResponseEntity<?> login(@RequestBody AppUser user) throws InvalidValueException {
        AppUserToken token= appUserService.login(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("users/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) throws InvalidValueException {
        appUserService.logout(token);
        Response response = new Response("Déconnexion réussie");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("users/register")
    public ResponseEntity<?> register(@RequestBody AppUserInput user) throws InvalidValueException {
        AppUserToken token = appUserService.register(user);
        Response response = new Response(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) throws InvalidValueException {
        return appUserService.getUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody AppUserUpdateInput appUser, @RequestHeader("Authorization") String token, @PathVariable Long id) throws InvalidValueException {
        return appUserService.updateUser(appUser, token, id);
    }
}
