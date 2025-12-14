package entities;

import afsj.agrox.entities.Category;
import afsj.agrox.entities.Product;
import afsj.agrox.enums.UnitOfMeasure;
import afsj.agrox.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductTests {
   Product product;

   private String name;
   private UnitOfMeasure unitOfMeasure;
   private Category category = new Category("other");
   private int initialStockQuantity = 50;


   @BeforeEach
   void setUp() {
      name = "New Product";
      unitOfMeasure = UnitOfMeasure.UN;
      product = new Product(name, unitOfMeasure, category,
              50);
   }

   @Test
   void shouldCreateCreateWhenIsValid() {
      Assertions.assertEquals(name, product.getName());
      Assertions.assertEquals(unitOfMeasure, product.getUnitOfMeasure());
      Assertions.assertSame(category, product.getCategory());
      Assertions.assertEquals(initialStockQuantity, product.getStockQuantity());
   }

   @Test
   void shouldThrowExceptionWhenNameIsNull() {
      Assertions.assertThrows(DomainException.class,
              () -> new Product(null, unitOfMeasure, category, initialStockQuantity));
   }

   @Test
   void shouldThrowExceptionWhenNameHasLessThan3Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Product("aa", unitOfMeasure, category, initialStockQuantity)
      );
   }
   @Test
   void shouldThrowExceptionWhenNameHasMoreThan50Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Product("a".repeat(51), unitOfMeasure, category, initialStockQuantity)
      );
   }

   @Test
   void shouldThrowExceptionWhenCategoryIsNull(){
      Assertions.assertThrows(
              DomainException.class,
              () -> new Product(name, unitOfMeasure, null, initialStockQuantity)
      );
   }

   @Test
   void shouldThrowExceptionWhenInitialQuantityIsInvalid(){
      Assertions.assertThrows(
              DomainException.class,
              () -> new Product(name, unitOfMeasure, category, 0)
      );
   }

   @Test
   void shouldThrowExceptionWhenUnitOfMeasureIsNull(){
      Assertions.assertThrows(
              DomainException.class,
              () -> new Product(name, null, category, initialStockQuantity)
      );
   }

   @Test
   void shouldIncreaseStockQuantity(){
      product.increaseStock(10);
      Assertions.assertEquals(60, product.getStockQuantity());
   }

   @Test
   void shouldThrowExceptionWhenIncreaseWithZeroOrNegativeAmount(){
      Assertions.assertThrows(
              DomainException.class,
              () -> product.increaseStock(0)
      );
      Assertions.assertThrows(
              DomainException.class,
              () -> product.increaseStock(-5)
      );
   }

   @Test
   void shouldDecreaseStockQuantity(){
      product.decreaseStock(20);

      Assertions.assertEquals(30, product.getStockQuantity());
   }

   @Test
   void shouldThrowExceptionWhenDecreaseMoreThanAvailableStock(){
      Assertions.assertThrows(
              DomainException.class,
              () ->product.decreaseStock(60)
      );
   }

   @Test
   void shouldThrowExceptionWhenDecreaseWithZeroOrNegativeAmount(){
      Assertions.assertThrows(
              DomainException.class,
              () -> product.decreaseStock(0)
      );
      Assertions.assertThrows(
              DomainException.class,
              () -> product.decreaseStock(-10)
      );
   }

   @Test
   void shouldReturnTrueWhenStockIsLow(){
      product.decreaseStock(30);
      Assertions.assertTrue(product.isLowStock());
   }
   @Test
   void shouldReturnFalseWhenStockIsNotLow(){
      Assertions.assertFalse(product.isLowStock());
   }
}
