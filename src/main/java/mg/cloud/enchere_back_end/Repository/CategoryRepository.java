package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByOrderByIdAsc();
}
