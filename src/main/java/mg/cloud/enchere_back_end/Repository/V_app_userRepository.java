package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.V_app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface V_app_userRepository extends JpaRepository<V_app_user,Long> {
    @Query(value = "select * from v_app_user where app_userid = :id",nativeQuery = true)
    Optional<V_app_user> getV_app_user(Long id);
}
