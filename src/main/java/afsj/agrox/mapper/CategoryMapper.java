package afsj.agrox.mapper;

import afsj.agrox.dtos.CategoryCreateDto;
import afsj.agrox.dtos.CategoryResponseDto;
import afsj.agrox.entities.Category;

import java.util.List;

public class CategoryMapper {

   public static Category toEntity(CategoryCreateDto dto) {
      return new Category(dto.getName());
   }

   public static CategoryResponseDto toDto(Category category) {
      return new CategoryResponseDto(category.getId(), category.getName());
   }

   public static List<CategoryResponseDto> toDtoList(List<Category> categories) {
      return categories.stream().map(category -> CategoryMapper.toDto(category)).toList();
   }

}
