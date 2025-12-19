package afsj.agrox.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StockAmountDto {

   @NotNull
   @Min(1)
   private Integer amount;

   public StockAmountDto() {
   }

   public Integer getAmount() {
      return amount;
   }
}
