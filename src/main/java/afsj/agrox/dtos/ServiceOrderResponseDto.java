package afsj.agrox.dtos;

import afsj.agrox.enums.ServiceOrderStatus;

import java.time.LocalDate;
import java.util.List;

public record ServiceOrderResponseDto(
        Long id,
        String description,
        String employeeName,
        LocalDate createdAt,
        LocalDate finishedAt,
        ServiceOrderStatus status,
        List<ServiceOrderItemResponseDto> items
) {
   public ServiceOrderResponseDto {
      items = items == null ? List.of() : List.copyOf(items);
   }

}