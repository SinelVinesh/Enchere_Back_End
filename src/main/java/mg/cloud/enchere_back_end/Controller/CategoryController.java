package mg.cloud.enchere_back_end.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.cloud.enchere_back_end.Model.Category;
import mg.cloud.enchere_back_end.Repository.CategoryRepository;
import mg.cloud.enchere_back_end.Service.CategoryService;
import mg.cloud.enchere_back_end.Service.CrudService;
import mg.cloud.enchere_back_end.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CrudService<Category, Long> crudServiceCategory;
    private final CategoryService categoryService;
    public CategoryController(
            CategoryRepository categoryRepository,
            CrudService<Category, Long> crudServiceCategory,
            CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.crudServiceCategory = crudServiceCategory;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(
    ){
        List<Category> category = categoryService.getAllCategory();
        HashMap<String, Object> responseData = new HashMap<>();

        if(category!=null){
            responseData.put("data",category);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }else{
            Response error = new Response("category is null");
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

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
