package afsj.agrox.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDto {
   @NotBlank
   @Size(min = 3, max = 50)
   private String name;

   public CategoryCreateDto() {
   }

   public CategoryCreateDto(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
