package mg.cloud.enchere_back_end.Service;

import mg.cloud.enchere_back_end.Model.Category;
import mg.cloud.enchere_back_end.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
}
