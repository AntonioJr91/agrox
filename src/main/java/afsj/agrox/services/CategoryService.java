package afsj.agrox.services;

import afsj.agrox.dtos.CategoryCreateDto;
import afsj.agrox.dtos.CategoryResponseDto;
import afsj.agrox.entities.Category;
import afsj.agrox.exceptions.ResourceNotFoundException;
import afsj.agrox.mapper.CategoryMapper;
import afsj.agrox.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

   private final CategoryRepository categoryRepository;

   public CategoryService(CategoryRepository categoryRepository) {
      this.categoryRepository = categoryRepository;
   }

   @Transactional(readOnly = true)
   public List<CategoryResponseDto> findAllCategories() {
      return CategoryMapper.toDtoList(categoryRepository.findAll());
   }

   @Transactional(readOnly = true)
   public CategoryResponseDto findCategoryById(Long id) {
      Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
      return CategoryMapper.toDto(category);
   }

   @Transactional
   public CategoryResponseDto createCategory(CategoryCreateDto dto) {
      Category category = CategoryMapper.toEntity(dto);
      Category saved = categoryRepository.save(category);
      return CategoryMapper.toDto(saved);
   }

   @Transactional
   public void deleteCategory(Long id) {
      if (!categoryRepository.existsById(id)) {
         throw new ResourceNotFoundException();
      }
      categoryRepository.deleteById(id);
   }
}
