package afsj.agrox.dtos;

import jakarta.validation.constraints.NotNull;

public class ServiceOrderItemCreateDto {
   @NotNull
   private Long productId;

   @NotNull
   private int quantity;

   public ServiceOrderItemCreateDto() {
   }

   public ServiceOrderItemCreateDto(Long productId, int quantity) {
      this.productId = productId;
      this.quantity = quantity;
   }

   public Long getProductId() {
      return productId;
   }

   public int getQuantity() {
      return quantity;
   }
}
