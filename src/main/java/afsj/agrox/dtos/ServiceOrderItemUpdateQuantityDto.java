package afsj.agrox.dtos;

public class ServiceOrderItemUpdateQuantityDto {
   private int amount;

   public ServiceOrderItemUpdateQuantityDto(int amount) {
      this.amount = amount;
   }

   public int getAmount() {
      return amount;
   }
}
