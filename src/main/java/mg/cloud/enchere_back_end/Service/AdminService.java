package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Repository.AdminRepository;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    private final AdminTokenService admin_tokenService;


    public AdminService(AdminTokenService admin_tokenService) {
        this.admin_tokenService = admin_tokenService;
    }

    public ResponseEntity<Response> login(Admin admin) {
        Optional<Admin> data = adminRepository.findByUsernameOrEmail(admin.getUsername(),admin.getEmail());
        if(data.isPresent()){
            Admin adminAccount = data.get();
            if(adminAccount.getPassword().equals(admin.getPassword())){
                HashMap<String,Object> responseData = new HashMap<>();
                responseData.put("token",admin_tokenService.generateToken(adminAccount));
                return ResponseEntity.ok(new Response(responseData));
            } else {
                return ResponseEntity.badRequest().body(new Response("Le mot de passe saisie est incorrecte"));
            }
        } else {
            return ResponseEntity.badRequest().body(new Response("Le nom d'utilisateur / email saisie est incorrecte"));
        }
    }

    public boolean logout(String token) {
        token = token.split(" ")[1];
        return admin_tokenService.removeToken(token);
    }

}
