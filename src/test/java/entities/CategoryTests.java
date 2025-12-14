package entities;

import afsj.agrox.entities.Category;
import afsj.agrox.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTests {
   Category category;
   private String validName;

   @BeforeEach
   void setUp() {
      validName = "Food";

      category = new Category(validName);
   }

   @Test
   void shouldCreateWhenIsValid() {
      Assertions.assertEquals(validName, category.getName());
   }

   @Test
   void shouldThrowExceptionWhenNameIsNull() {
      Assertions.assertThrows(
              DomainException.class, () -> new Category(null)
      );
   }
   @Test
   void shouldThrowExceptionWhenNameIsBlank() {
      Assertions.assertThrows(
              DomainException.class, () -> new Category(" ")
      );
   }

   @Test
   void shouldThrowExceptionWhenNameHasLessThan3Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Category("a".repeat(2))
      );
   }
   @Test
   void shouldThrowExceptionWhenNameHasMoreThan50Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Category("a".repeat(51))
      );
   }
}
