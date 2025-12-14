package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;

public class ServiceOrderItem {
   private Long id;
   private ServiceOrder serviceOrder;
   private Product product;
   private int quantity;

   protected ServiceOrderItem() {
   }

   public ServiceOrderItem(ServiceOrder serviceOrder, Product product, int quantity) {
      validation(serviceOrder, product, quantity);
      this.serviceOrder = serviceOrder;
      this.product = product;
      this.quantity = quantity;
   }

   public Long getId() {
      return id;
   }

   public Product getProduct() {
      return product;
   }

   public int getQuantity() {
      return quantity;
   }

   void increaseQuantity(int amount) {
      validateAmount(amount);
      this.quantity += amount;
   }

   void decreaseQuantity(int amount) {
      validateAmount(amount);
      this.quantity -= amount;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof ServiceOrderItem that)) return false;
      return id != null && id.equals(that.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }

   private void validateProduct(Product product) {
      DomainValidation.when(product == null, "Product is required");
   }

   private void validateServiceOrder(ServiceOrder serviceOrder) {
      DomainValidation.when(serviceOrder == null, "Service order is required");
   }

   private void validateQuantity(int quantity) {
      DomainValidation.when(quantity <= 0, "Quantity must be greater than 0");
   }

   private void validateAmount(int amount) {
      DomainValidation.when(amount <= 0, "Amount must be greater than zero");
      DomainValidation.when(this.quantity - amount <= 0, "Resulting quantity must be greater than zero");
   }

   private void validation(ServiceOrder serviceOrder, Product product, int quantity) {
      validateServiceOrder(serviceOrder);
      validateProduct(product);
      validateQuantity(quantity);
   }
}
