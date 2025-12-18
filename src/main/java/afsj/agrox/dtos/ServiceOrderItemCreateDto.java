package afsj.agrox.dtos;

public class ServiceOrderItemCreateDto {
   private Long productId;
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
