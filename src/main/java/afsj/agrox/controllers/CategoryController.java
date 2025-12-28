package afsj.agrox.controllers;

import afsj.agrox.dtos.CategoryCreateDTO;
import afsj.agrox.dtos.CategoryResponseDTO;
import afsj.agrox.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

   private final CategoryService categoryService;

   public CategoryController(CategoryService categoryService) {
      this.categoryService = categoryService;
   }

   @GetMapping
   public ResponseEntity<Page<CategoryResponseDTO>> getAll(Pageable pageable) {
      Page<CategoryResponseDTO> categories = categoryService.findAllCategories(pageable);
      return ResponseEntity.ok(categories);
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id) {
      CategoryResponseDTO category = categoryService.findCategoryById(id);
      return ResponseEntity.ok(category);
   }

   @PostMapping
   public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryCreateDTO dto){
      CategoryResponseDTO category = categoryService.createCategory(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.id()).toUri();
      return ResponseEntity.created(uri).body(category);
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id){
   categoryService.deleteCategory(id);
   return ResponseEntity.noContent().build();
   }
}
