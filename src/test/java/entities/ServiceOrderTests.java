package entities;

import afsj.agrox.entities.Category;
import afsj.agrox.entities.Employee;
import afsj.agrox.entities.Product;
import afsj.agrox.entities.ServiceOrder;
import afsj.agrox.enums.ContractType;
import afsj.agrox.enums.ServiceOrderStatus;
import afsj.agrox.enums.UnitOfMeasure;
import afsj.agrox.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class ServiceOrderTests {
   ServiceOrder so;
   Product p;

   private String description;
   private Employee emp;
   private ServiceOrderStatus status;

   @BeforeEach
   void setUp() {
      description = "description";
      emp = new Employee(
              "Ana",
              "12345678910",
              "73123456789",
              LocalDate.of(1990, 1, 1),
              ContractType.CLT,
              LocalDate.now());
      status = ServiceOrderStatus.PENDING;

      so = new ServiceOrder(description, emp);
      p = new Product("Product", UnitOfMeasure.UN, new Category("others"), 50);
   }

   @Test
   void shouldCreateServiceOrderWhenIsValid() {
      Assertions.assertEquals(description, so.getDescription());
      Assertions.assertSame(emp, so.getEmployee());
      Assertions.assertEquals(status, so.getStatus());
   }

   @Test
   void shouldThrowExceptionWhenDescriptionIsNull() {
      Assertions.assertThrows(
              DomainException.class,
              () -> so = new ServiceOrder(null, emp)
      );
   }

   @Test
   void shouldThrowExceptionWhenDescriptionIsLessThan3Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> so = new ServiceOrder("a", emp)
      );
   }

   @Test
   void shouldThrowExceptionWhenDescriptionIsMoreThan50Characters() {
      Assertions.assertThrows(
              DomainException.class,
              () -> so = new ServiceOrder("a".repeat(256), emp)
      );
   }

   @Test
   void shouldThrowExceptionWhenProductIsEmployeeIsNull() {
      Assertions.assertThrows(
              DomainException.class,
              () -> so = new ServiceOrder(description, null)
      );
   }

   @Test
   void shouldThrowExceptionWhenServiceOrderIsPending() {
      so.finish();
      Assertions.assertThrows(
              DomainException.class, () -> so.cancel()
      );
   }

   @Test
   void shouldFinishServiceOrderWhenPending() {
      so.finish();
      Assertions.assertEquals(ServiceOrderStatus.COMPLETED, so.getStatus());
      Assertions.assertNotNull(so.getFinishedAt());
   }

   @Test
   void shouldThrowExceptionWhenServiceOrderNotPending() {
      so.finish();
      Assertions.assertThrows(DomainException.class, () -> so.finish());
   }

   @Test
   void shouldAddItemWhenPending() {
      so.addItem(p, 1);
      Assertions.assertEquals(1, so.getServiceOrderItems().size());
   }

   @Test
   void shouldThrowExceptionWhenAddItemWhenNotPending() {
      so.finish();
      Assertions.assertThrows(DomainException.class, () -> so.addItem(p, 1));
   }
}
