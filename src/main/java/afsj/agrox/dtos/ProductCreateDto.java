package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCreateDto {

   @NotBlank
   @Size(min = 3, max = 50)
   private String name;

   @NotNull
   private UnitOfMeasure unitOfMeasure;

   @NotNull
   private Long categoryId;

   @NotNull
   @Min(1)
   private int stockQuantity;

   public ProductCreateDto() {
   }

   public ProductCreateDto(String name, UnitOfMeasure unitOfMeasure, Long categoryId, int stockQuantity) {
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.categoryId = categoryId;
      this.stockQuantity = stockQuantity;
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

   public int getStockQuantity() {
      return stockQuantity;
   }
}
