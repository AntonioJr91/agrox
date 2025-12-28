package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;

public class ProductResponseDto {
   private Long id;
   private String name;
   private UnitOfMeasure unitOfMeasure;
   private CategoryResponseDTO categoryDto;
   private int stockQuantity;

   public ProductResponseDto() {
   }

   public ProductResponseDto(Long id, String name, UnitOfMeasure unitOfMeasure,
                             CategoryResponseDTO categoryDto, int stockQuantity) {
      this.id = id;
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.categoryDto = categoryDto;
      this.stockQuantity = stockQuantity;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public UnitOfMeasure getUnitOfMeasure() {
      return unitOfMeasure;
   }

   public CategoryResponseDTO getCategory() {
      return categoryDto;
   }

   public int getStock() {
      return stockQuantity;
   }
}
