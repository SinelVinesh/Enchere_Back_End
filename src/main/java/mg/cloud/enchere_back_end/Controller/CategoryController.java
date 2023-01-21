package mg.cloud.enchere_back_end.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.Category;
import mg.cloud.enchere_back_end.Repository.CategoryRepository;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CrudService<Category, Long> crudServiceCategory;

    public CategoryController(
            CategoryRepository categoryRepository,
            CrudService<Category, Long> crudServiceCategory
    ) {
        this.categoryRepository = categoryRepository;
        this.crudServiceCategory = crudServiceCategory;
    }

    @RequestMapping(value = {"/categories", "/categories/{id}"})
    public ResponseEntity<Response> crudCategories (
            @PathVariable(value = "id") Optional<Long> id,
            @RequestBody Optional<Category> category,
            HttpServletRequest request
            ) {
        Category data = category.orElse(null);
        return crudServiceCategory.handle(request.getMethod(), categoryRepository, id, data);
    }
}
