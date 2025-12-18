package afsj.agrox.mapper;

import afsj.agrox.dtos.ServiceOrderItemResponseDto;
import afsj.agrox.entities.ServiceOrderItem;

public class ServiceOrderItemMapper {

   public static ServiceOrderItemResponseDto toDto(ServiceOrderItem item) {
      return new ServiceOrderItemResponseDto(
              item.getId(),
              item.getProduct().getId(),
              item.getProduct().getName(),
              item.getQuantity()
      );
   }
}