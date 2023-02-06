package mg.cloud.enchere_back_end.Repository;

import mg.cloud.enchere_back_end.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Override
    Optional<Category> findById(Long id);

    List<Category> findAll();

    List<Category> findAllByOrderByIdAsc();
}
