package afsj.agrox.dtos;

public class ServiceOrderItemResponseDto {
   private Long id;
   private Long productId;
   private String productName;
   private int quantity;

   public ServiceOrderItemResponseDto() {
   }

   public ServiceOrderItemResponseDto(Long id, Long productId, String productName, int quantity) {
      this.id = id;
      this.productId = productId;
      this.productName = productName;
      this.quantity = quantity;
   }

   public Long getId() {
      return id;
   }

   public Long getProductId() {
      return productId;
   }

   public String getProductName() {
      return productName;
   }

   public int getQuantity() {
      return quantity;
   }
}
