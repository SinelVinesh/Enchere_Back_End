package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.App_user_recharge_state_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface App_user_recharge_state_historyRepository extends JpaRepository<App_user_recharge_state_history,Long> {
    @Override
    Optional<App_user_recharge_state_history> findById(Long id);

    @Query(value = " select * from app_user_recharge_state_history where app_user_recharge_requestid not in(select app_user_recharge_requestid from app_user_recharge_state_history where recharge_stateid != 1)",nativeQuery = true)
    Optional<List<App_user_recharge_state_history>> getRechargeRequest();
}
