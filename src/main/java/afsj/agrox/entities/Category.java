package afsj.agrox.entities;

import afsj.agrox.validations.DomainValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, unique = true, length = 50)
   private String name;

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
