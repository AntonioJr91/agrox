package afsj.agrox.entities;

import afsj.agrox.enums.ServiceOrderStatus;
import afsj.agrox.validations.DomainValidation;

import java.time.LocalDate;

public class ServiceOrder {
   private Long id;
   private String description;
   private Employee employee;
   private LocalDate createdAt;
   private LocalDate finishedAt;
   private ServiceOrderStatus status;

   protected ServiceOrder() {
   }

   public ServiceOrder(String description, Employee employee) {
      validation(description, employee);
      this.description = description;
      this.employee = employee;
      this.createdAt = LocalDate.now();
      this.finishedAt = null;
      this.status = ServiceOrderStatus.PENDING;
   }

   public Long getId() {
      return id;
   }

   public String getDescription() {
      return description;
   }

   public Employee getEmployee() {
      return employee;
   }

   public LocalDate getCreatedAt() {
      return createdAt;
   }

   public LocalDate getFinishedAt() {
      return finishedAt;
   }

public void finish(){
      DomainValidation.when(this.status != ServiceOrderStatus.PENDING, "Only pending service orders can be finished");
      this.finishedAt = LocalDate.now();
      this.status = ServiceOrderStatus.COMPLETED;
}
public void cancel(){
      DomainValidation.when(this.status != ServiceOrderStatus.PENDING, "Only pending service orders can be finished");
      this.finishedAt = LocalDate.now();
      this.status = ServiceOrderStatus.CANCELLED;
}

   public ServiceOrderStatus getStatus() {
      return status;
   }

   public void setStatus(ServiceOrderStatus status) {
      validateServiceOrderStatus(status);
      this.status = status;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof ServiceOrder that)) return false;
      return id !=null && id.equals(that.id);
   }

   @Override
   public int hashCode() {
      return 31;
   }

   @Override
   public String toString() {
      return "ServiceOrder{" +
              "id=" + id +
              ", description='" + description + '\'' +
              ", employee=" + employee +
              ", createdAt=" + createdAt +
              ", finishedAt=" + finishedAt +
              ", status=" + status +
              '}';
   }

   private void validateDescription(String description) {
      DomainValidation.when(description == null, "Description is required");
      DomainValidation.when(description.length() < 3 || description.length() > 255, "Description must contain between 50 and 255 characters");
   }

   private void validateEmployee(Employee employee) {
      DomainValidation.when(employee == null, "Employee is required");
   }

   private void validateFinishedAt(LocalDate finishedAt) {
      DomainValidation.when(finishedAt == null, "Finished date is required");
      DomainValidation.when(finishedAt.isBefore(this.createdAt), "Finished date cannot be before the creation date");
   }

   private void validateServiceOrderStatus(ServiceOrderStatus status) {
      DomainValidation.when(status == null, "Service order status is required");
   }

   private void validation(String description, Employee employee) {
      validateDescription(description);
      validateEmployee(employee);
   }
}
