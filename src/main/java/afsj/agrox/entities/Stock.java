package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Stock {

   @Column(nullable = false)
   private int quantity;
   private static final int MINIMUM_QUANTITY = 20;

   protected Stock() {
   }

   public Stock(int initialQuantity) {
      validate(initialQuantity);
      this.quantity = initialQuantity;
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

   private void validateIncreaseAmount(int amount) {
      DomainValidation.when(amount <= 0, "Increase amount must be greater than 0");
   }

   private void validateDecreaseAmount(int amount) {
      DomainValidation.when(amount <= 0, "Decrease amount must be greater than 0");
      DomainValidation.when(amount > quantity, "Insufficient stock");
   }

   private void validate(int quantity) {
      DomainValidation.when(quantity <= 0, "Initial stock must be greater than zero");
   }
}
