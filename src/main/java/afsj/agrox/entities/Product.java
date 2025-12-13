package afsj.agrox.entities;

import afsj.agrox.enums.UnitOfMeasure;
import afsj.agrox.validations.DomainValidation;

public class Product {
   private Long id;
   private String name;
   private UnitOfMeasure unitOfMeasure;

   private Category category;

   protected Product() {
   }

   public Product(String name, UnitOfMeasure unitOfMeasure, Category category) {
      validate(name, unitOfMeasure, category);
      this.name = name;
      this.unitOfMeasure = unitOfMeasure;
      this.category = category;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      validateName(name);
      this.name = name;
   }

   public UnitOfMeasure getUnitOfMeasure() {
      return unitOfMeasure;
   }

   public Category getCategory() {
      return category;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Product product)) return false;
      return id != null && id.equals(product.id);
   }

   @Override
   public int hashCode() {
      return 31;
   }

   @Override
   public String toString() {
      return "Product{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", unitOfMeasure=" + unitOfMeasure +
              ", category=" + category +
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

   private void validate(String name, UnitOfMeasure unitOfMeasure, Category category) {
      validateName(name);
      validateUnitOfMeasure(unitOfMeasure);
      validateCategory(category);
   }
}
