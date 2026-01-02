package afsj.agrox.entities;

import afsj.agrox.enums.ServiceOrderStatus;
import afsj.agrox.exceptions.DomainException;
import afsj.agrox.validations.DomainValidation;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ServiceOrder {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 255)
   private String description;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "employee_id", nullable = false)
   private Employee employee;

   @Column(nullable = false, updatable = false)
   private LocalDate createdAt;

   @Column()
   private LocalDate finishedAt;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private ServiceOrderStatus status;

   @OneToMany(mappedBy = "serviceOrder",
           cascade = CascadeType.ALL,
           orphanRemoval = true)
   private List<ServiceOrderItem> serviceOrderItems = new ArrayList<>();

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

   public void finish() {
      DomainValidation.when(this.status != ServiceOrderStatus.PENDING, "Only pending service orders can be finished");
      this.finishedAt = LocalDate.now();
      this.status = ServiceOrderStatus.COMPLETED;
   }

   public void cancel() {
      DomainValidation.when(this.status != ServiceOrderStatus.PENDING, "Only pending service orders can be cancelled");
      this.finishedAt = LocalDate.now();
      this.status = ServiceOrderStatus.CANCELLED;
   }

   public ServiceOrderStatus getStatus() {
      return status;
   }

   public List<ServiceOrderItem> getServiceOrderItems() {
      return List.copyOf(serviceOrderItems);
   }

   public void addItem(Product product, int quantity) {
      DomainValidation.when(!isPending(), "Cannot add items to a finalized service order");
      validateProductDuplicate(product);
      ServiceOrderItem item = new ServiceOrderItem(this, product, quantity);
      this.serviceOrderItems.add(item);
   }

   public void removeItem(Product product) {
      DomainValidation.when(!isPending(), "Cannot remove items from a finalized service order");
      ServiceOrderItem item = findItemByProduct(product);
      serviceOrderItems.remove(item);
   }

   public void increaseItemQuantity(Product product, int amount) {
      DomainValidation.when(!isPending(), "Cannot change items of a finalized order");

      ServiceOrderItem item = findItemByProduct(product);
      item.increaseQuantity(amount);
   }

   public void decreaseItemQuantity(Product product, int amount) {
      DomainValidation.when(!isPending(), "Cannot change items of a finalized order");

      ServiceOrderItem item = findItemByProduct(product);
      item.decreaseQuantity(amount);
   }

   private ServiceOrderItem findItemByProduct(Product product) {
      validateProductIsNull(product);
      return serviceOrderItems.stream()
              .filter(i -> i.getProduct().equals(product))
              .findFirst()
              .orElseThrow(() -> new DomainException("Product not found in service order"));
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof ServiceOrder that)) return false;
      return id != null && id.equals(that.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
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
      DomainValidation.when(description.length() < 3 || description.length() > 255, "Description must contain between 3 and 255 characters");
   }

   private void validateEmployee(Employee employee) {
      DomainValidation.when(employee == null, "Employee is required");
   }

   private void validation(String description, Employee employee) {
      validateDescription(description);
      validateEmployee(employee);
   }

   private void validateProductIsNull(Product product) {
      DomainValidation.when(product == null, "Product is required");
   }

   private void validateProductDuplicate(Product product) {
      validateProductIsNull(product);

      var exists = serviceOrderItems.stream().anyMatch(i -> i.getProduct().equals(product));
      DomainValidation.when(exists, "Product already added to service order");
   }

   public boolean isPending() {
      return status == ServiceOrderStatus.PENDING;
   }
}
