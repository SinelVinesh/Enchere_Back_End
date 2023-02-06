package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.response.Response;
import mg.cloud.enchere_back_end.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AuthenticationAdminController {

    private final AdminService adminService;

    public AuthenticationAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody Admin admin){
        return adminService.login(admin);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if(adminService.logout(token)) {
            Response response = new Response("successfully logout");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            Response response = new Response("failed to logout");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}