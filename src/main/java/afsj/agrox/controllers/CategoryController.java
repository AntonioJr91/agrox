package afsj.agrox.controllers;

import afsj.agrox.dtos.CategoryCreateDto;
import afsj.agrox.dtos.CategoryResponseDto;
import afsj.agrox.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

   private final CategoryService categoryService;

   public CategoryController(CategoryService categoryService) {
      this.categoryService = categoryService;
   }

   @GetMapping
   public ResponseEntity<List<CategoryResponseDto>> getAll() {
      List<CategoryResponseDto> categories = categoryService.findAllCategories();
      return ResponseEntity.ok(categories);
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id) {
      CategoryResponseDto category = categoryService.findCategoryById(id);
      return ResponseEntity.ok(category);
   }

   @PostMapping
   public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryCreateDto dto){
      CategoryResponseDto category = categoryService.createCategory(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getName()).toUri();
      return ResponseEntity.created(uri).body(category);
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id){
   categoryService.deleteCategory(id);
   return ResponseEntity.noContent().build();
   }
}
