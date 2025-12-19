package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductUpdateDto {

   @NotBlank
   @Size(min = 3, max = 50)
   private String name;

   @NotNull
   private UnitOfMeasure unitOfMeasure;

   @NotNull
   private Long categoryId;

   public ProductUpdateDto() {
   }

   public ProductUpdateDto(String name, UnitOfMeasure unitOfMeasure, Long categoryId) {
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.categoryId = categoryId;

   }

   public String getName() {
      return name;
   }

   public UnitOfMeasure getUnitOfMeasure() {
      return unitOfMeasure;
   }

   public Long getCategoryId() {
      return categoryId;
   }

}
