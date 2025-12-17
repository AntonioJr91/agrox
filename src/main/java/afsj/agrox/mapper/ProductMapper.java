package afsj.agrox.mapper;

import afsj.agrox.dtos.CategoryResponseDto;
import afsj.agrox.dtos.ProductCreateDto;
import afsj.agrox.dtos.ProductResponseDto;
import afsj.agrox.entities.Category;
import afsj.agrox.entities.Product;

import java.util.List;

public class ProductMapper {

   public static Product toEntity(ProductCreateDto dto, Category category) {
      return new Product(
              dto.getName(),
              dto.getUnitOfMeasure(),
              category,
              dto.getStockQuantity()
      );
   }

   public static ProductResponseDto toDto(Product product) {
      return new ProductResponseDto(
              product.getId(),
              product.getName(),
              product.getUnitOfMeasure(),
              new CategoryResponseDto(
                      product.getCategory().getId(),
                      product.getCategory().getName()),
              product.getStockQuantity()

      );
   }

   public static List<ProductResponseDto> toDtoList(List<Product> products) {
      return products.stream().map(product ->
              new ProductResponseDto(
                      product.getId(),
                      product.getName(),
                      product.getUnitOfMeasure(),
                      new CategoryResponseDto(
                              product.getCategory().getId(),
                              product.getCategory().getName()),
                      product.getStockQuantity()
              )

      ).toList();
   }
}
