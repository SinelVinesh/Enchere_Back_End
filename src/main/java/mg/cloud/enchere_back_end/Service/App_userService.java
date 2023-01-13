package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Admin;
import mg.cloud.enchere_back_end.Model.App_user;
import mg.cloud.enchere_back_end.Repository.App_userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class App_userService {
    @Autowired
    private App_userRepository app_userRepository;

    private final App_user_tokenService app_user_tokenService;

    public App_userService(App_user_tokenService app_user_tokenService) {
        this.app_user_tokenService = app_user_tokenService;
    }


    public String login(App_user app_user) {
        Optional<App_user> data = app_userRepository.findByUsernameOrEmailAndPassword(app_user.getUsername(),app_user.getEmail() ,app_user.getPassword());
        return data.map(app_user_tokenService::generateToken).orElse(null);
    }
}
