package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Repository.AdminRepository;
import mg.cloud.enchere_back_end.Repository.Admin_tokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    private final Admin_tokenService admin_tokenService;


    public AdminService(Admin_tokenService admin_tokenService) {
        this.admin_tokenService = admin_tokenService;
    }

    public String login(Admin admin) {
        Optional<Admin> data = adminRepository.findByUsernameOrEmailAndPassword(admin.getUsername(),admin.getEmail() ,admin.getPassword());
        return data.map(admin_tokenService::generateToken).orElse(null);
    }

}
