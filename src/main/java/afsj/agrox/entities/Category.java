package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;

import java.util.HashSet;
import java.util.Set;

public class Category {
   private Long id;
   private String name;

   private Set<Product> products = new HashSet<>();

   protected Category() {
   }

   public Category(String name) {
      validateName(name);
      this.name = name;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public Set<Product> getProducts() {
      return Set.copyOf(products);
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Category category)) return false;
      return id != null && id.equals(category.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
   }

   @Override
   public String toString() {
      return "Category{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }

   private void validateName(String name) {
      DomainValidation.when(name == null, "Category name is required");
      DomainValidation.when(name.isBlank(), "Category name must not be blank");
      DomainValidation.when(name.length() < 3 || name.length() > 50,
              "Category name must contain between 3 and 50 characters");
   }
}
