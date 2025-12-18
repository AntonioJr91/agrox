package afsj.agrox.dtos;

import afsj.agrox.enums.ServiceOrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderResponseDto {
   private Long id;
   private String description;
   private String employeeName;
   private LocalDate createdAt;
   private LocalDate finishedAt;
   private ServiceOrderStatus status;
   private List<ServiceOrderItemResponseDto> items = new ArrayList<>();

   public ServiceOrderResponseDto() {
   }

   public ServiceOrderResponseDto(Long id, String description, String employeeName,
                                  LocalDate createdAt, LocalDate finishedAt,
                                  ServiceOrderStatus status, List<ServiceOrderItemResponseDto> items) {
      this.id = id;
      this.description = description;
      this.employeeName = employeeName;
      this.createdAt = createdAt;
      this.finishedAt = finishedAt;
      this.status = status;
      this.items = items;
   }

   public Long getId() {
      return id;
   }

   public String getDescription() {
      return description;
   }

   public String getEmployeeName() {
      return employeeName;
   }

   public LocalDate getCreatedAt() {
      return createdAt;
   }

   public LocalDate getFinishedAt() {
      return finishedAt;
   }

   public ServiceOrderStatus getStatus() {
      return status;
   }

   public List<ServiceOrderItemResponseDto> getItems() {
      return items;
   }
}
