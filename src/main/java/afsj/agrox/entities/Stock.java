package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;

public class Stock {
   private Long id;
   private Product product;
   private int quantity;
   private static final int MINIMUM_QUANTITY = 20;

   protected Stock() {
   }

   public Stock(Product product, int initialQuantity) {
      validate(product, initialQuantity);
      this.product = product;
      this.quantity = initialQuantity;
   }

   Long getId() {
      return id;
   }

   public int getQuantity() {
      return quantity;
   }

   void increase(int amount) {
      validateIncreaseAmount(amount);
      this.quantity += amount;
   }

   void decrease(int amount) {
      validateDecreaseAmount(amount);
      this.quantity -= amount;
   }

   boolean isLowStock() {
      return quantity <= MINIMUM_QUANTITY;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Stock stock)) return false;
      return id != null && id.equals(stock.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }

   private void validateIncreaseAmount(int amount) {
      DomainValidation.when(amount <= 0, "Increase amount must be greater than 0");
   }

   private void validateDecreaseAmount(int amount) {
      DomainValidation.when(amount <= 0, "Decrease amount must be greater than 0");
      DomainValidation.when(amount > quantity, "Insufficient stock");
   }

   private void validate(Product product, int quantity) {
      DomainValidation.when(product == null, "Product is required");
   }
}
