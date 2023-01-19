package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.App_user_recharge_request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface App_user_recharge_requestRepository  extends JpaRepository<App_user_recharge_request,Long> {
    @Override
    Optional<App_user_recharge_request> findById(Long aLong);
}
