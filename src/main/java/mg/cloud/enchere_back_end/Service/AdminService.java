package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Repository.AdminRepository;
import mg.cloud.enchere_back_end.Repository.Admin_tokenRepository;
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

    private final Admin_tokenService admin_tokenService;


    public AdminService(Admin_tokenService admin_tokenService) {
        this.admin_tokenService = admin_tokenService;
    }

    public ResponseEntity<Response> login(Admin admin) {
        Optional<Admin> data = adminRepository.findByUsernameOrEmail(admin.getUsername(),admin.getEmail());
        if(data.isPresent()){
            Admin adminAccount = data.get();
            if(adminAccount.getPassword().equals(admin.getPassword())){
                HashMap<String,String> responseData = new HashMap<>();
                responseData.put("token",admin_tokenService.generateToken(adminAccount));
                return ResponseEntity.ok(new Response((Object)responseData));
            } else {
                return ResponseEntity.badRequest().body(new Response("Le mot de passe saisie est incorrecte"));
            }
        } else {
            return ResponseEntity.badRequest().body(new Response("Le nom d'utilisateur / email saisie est incorrecte"));
        }
    }

    public boolean logout(String token) {
        return admin_tokenService.removeToken(token);
    }

}
