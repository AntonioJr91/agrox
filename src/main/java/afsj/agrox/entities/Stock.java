package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;

public class Stock {
   private Long id;
   private Product product;
   private long quantity;
   private static final int MINIMUM_QUANTITY = 20;

   protected Stock() {
   }

   public Stock(Product product, long initialQuantity) {
      validate(product, initialQuantity);
      this.product = product;
      this.quantity = initialQuantity;
   }

   public Long getId() {
      return id;
   }

   public Product getProduct() {
      return product;
   }

   public long getQuantity() {
      return quantity;
   }

   public void increase(long amount) {
      validateIncreaseAmount(amount);
      this.quantity += amount;
   }

   public void decrease(long amount) {
      validateDecreaseAmount(amount);
      this.quantity -= amount;
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

   private void validateIncreaseAmount(long amount) {
      DomainValidation.when(amount <= 0, "Increase amount must be greater than 0");
   }

   private void validateDecreaseAmount(long amount) {
      DomainValidation.when(amount <= 0, "Decrease amount must be greater than 0");
      DomainValidation.when(amount > quantity, "Insufficient stock");
   }

   private void validate(Product product, long quantity) {
      DomainValidation.when(product == null, "Product is required");
      DomainValidation.when(quantity < 0, "Initial stock quantity cannot be negative");
   }

   public boolean isLowStock() {
      return quantity <= MINIMUM_QUANTITY;
   }
}
