package afsj.agrox.mapper;

import afsj.agrox.dtos.CategoryCreateDTO;
import afsj.agrox.dtos.CategoryResponseDTO;
import afsj.agrox.entities.Category;

import java.util.List;

public class CategoryMapper {

   public static Category toEntity(CategoryCreateDTO dto) {
      return new Category(dto.name());
   }

   public static CategoryResponseDTO toDto(Category category) {
      return new CategoryResponseDTO(category.getId(), category.getName());
   }

   public static List<CategoryResponseDTO> toDtoList(List<Category> categories) {
      return categories.stream().map(category -> CategoryMapper.toDto(category)).toList();
   }

}
