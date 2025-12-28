package afsj.agrox.mapper;

import afsj.agrox.dtos.ServiceOrderCreateDto;
import afsj.agrox.dtos.ServiceOrderItemResponseDto;
import afsj.agrox.dtos.ServiceOrderResponseDto;
import afsj.agrox.entities.Employee;
import afsj.agrox.entities.ServiceOrder;

import java.util.List;

public class ServiceOrderMapper {

   public static ServiceOrder toEntity(ServiceOrderCreateDto dto, Employee emp) {
      return new ServiceOrder(dto.description(), emp);
   }

   public static ServiceOrderResponseDto toDto(ServiceOrder serviceOrder) {
      List<ServiceOrderItemResponseDto> items = itemList(serviceOrder);

      return new ServiceOrderResponseDto(
              serviceOrder.getId(),
              serviceOrder.getDescription(),
              serviceOrder.getEmployee().getName(),
              serviceOrder.getCreatedAt(),
              serviceOrder.getFinishedAt(),
              serviceOrder.getStatus(),
              items
      );
   }

   public static List<ServiceOrderResponseDto> toDtoList(List<ServiceOrder> serviceOrders) {
      return serviceOrders.stream().map(ServiceOrderMapper::toDto).toList();
   }

   private static List<ServiceOrderItemResponseDto> itemList(ServiceOrder serviceOrder) {
      return serviceOrder.getServiceOrderItems()
              .stream()
              .map(ServiceOrderItemMapper::toDto)
              .toList();
   }

}
