package mg.cloud.enchere_back_end.Controller;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Response.ErrorResponse;
import mg.cloud.enchere_back_end.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
public class AuthentificationAdminController {

    private final AdminService adminService;

    public AuthentificationAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin){
        String token= adminService.login(admin);
        HashMap<String,String> responseData = new HashMap<>();
        responseData.put("token",token);
        if(token != null){
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            ErrorResponse response = new ErrorResponse(404,"admin not found");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) {
        if(adminService.logout(token)) {
            ErrorResponse response = new ErrorResponse(200, "successfully logout");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            ErrorResponse response = new ErrorResponse(500, "failed to logout");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}