package afsj.agrox.entities;

import afsj.agrox.enums.UnitOfMeasure;
import afsj.agrox.validations.DomainValidation;
import jakarta.persistence.*;

@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 50)
   private String name;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private UnitOfMeasure unitOfMeasure;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id", nullable = false)
   private Category category;

   @Embedded
   @AttributeOverride(name = "quantity",
           column = @Column(name = "stock_quantity", nullable = false))
   private Stock stock;

   protected Product() {
   }

   public Product(String name, UnitOfMeasure unitOfMeasure, Category category, int initialStockQuantity) {
      validate(name, unitOfMeasure, category, initialStockQuantity);
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.category = category;
      this.stock = new Stock(initialStockQuantity);
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public UnitOfMeasure getUnitOfMeasure() {
      return unitOfMeasure;
   }

   public Category getCategory() {
      return category;
   }

   public int getStockQuantity() {
      return stock.getQuantity();
   }

   public boolean isLowStock() {
      return stock.isLowStock();
   }

   public void increaseStock(int amount) {
      stock.increase(amount);
   }

   public void decreaseStock(int amount) {
      stock.decrease(amount);
   }

   public void updateDetails(String name, UnitOfMeasure unitOfMeasure, Category category) {
      validateName(name);
      validateUnitOfMeasure(unitOfMeasure);
      validateCategory(category);
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.category = category;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Product product)) return false;
      return id != null && id.equals(product.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }

   @Override
   public String toString() {
      return "Product{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", unitOfMeasure=" + unitOfMeasure +
              ", category=" + category +
              ", stock=" + stock.getQuantity() +
              '}';
   }

   private void validateName(String name) {
      DomainValidation.when(name == null, "Product name is required");
      DomainValidation.when(name.isBlank(), "Product name must not be blank");
      DomainValidation.when(name.length() < 3 || name.length() > 50,
              "Product name must contain between 3 and 50 characters");
   }

   private void validateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
      DomainValidation.when(unitOfMeasure == null, "Unit of measure is required");
   }

   private void validateCategory(Category category) {
      DomainValidation.when(category == null, "Category is required");
   }

   private void validateInitialQuantity(int initialStockQuantity) {
      DomainValidation.when(initialStockQuantity <= 0, "Initial stock must be greater than zero");
   }

   private void validate(String name, UnitOfMeasure unitOfMeasure, Category category, int initialStockQuantity) {
      validateName(name);
      validateUnitOfMeasure(unitOfMeasure);
      validateCategory(category);
      validateInitialQuantity(initialStockQuantity);
   }
}
