package afsj.agrox.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ServiceOrderCreateDto {
   @NotBlank
   private String description;

   @NotNull
   private Long employeeId;

   private List<ServiceOrderItemCreateDto> items;

   public ServiceOrderCreateDto() {
   }

   public ServiceOrderCreateDto(String description, Long employeeId) {
      this.description = description;
      this.employeeId = employeeId;
   }

   public String getDescription() {
      return description;
   }

   public Long getEmployeeId() {
      return employeeId;
   }

   public List<ServiceOrderItemCreateDto> getItems() {
      return items;
   }
}
