package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.AppUserFullBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserFullBalanceRepository extends JpaRepository<AppUserFullBalance, Long> {
    @Query(value = "select * from  v_user_full_balance where id = :id",nativeQuery = true)
    Optional<AppUserFullBalance> getAppUSerBalance(Long id);
}
