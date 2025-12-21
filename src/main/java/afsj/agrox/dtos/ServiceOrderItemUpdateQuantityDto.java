package afsj.agrox.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ServiceOrderItemUpdateQuantityDto {
   @NotNull
   @Min(1)
   private int amount;

   public ServiceOrderItemUpdateQuantityDto(int amount) {
      this.amount = amount;
   }

   public int getAmount() {
      return amount;
   }
}
