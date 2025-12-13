package entities;

import afsj.agrox.entities.Employee;
import afsj.agrox.enums.ContractType;
import afsj.agrox.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmployeeTests {
   Employee emp;
   private String validName;
   private String validCpf;
   private String validPhone;
   private LocalDate validBirthDate;
   private ContractType validContractType;
   private LocalDate validAdmissionDate;


   @BeforeEach
   void setUp() {
      validName = "John Wick";
      validCpf = "12345678910";
      validPhone = "73123456789";
      validBirthDate = LocalDate.of(1990, 1, 1);
      validContractType = ContractType.SHARECROPPER;
      validAdmissionDate = LocalDate.now();

      emp = new Employee(
              validName,
              validCpf,
              validPhone,
              validBirthDate,
              validContractType,
              validAdmissionDate
      );
   }

   @Test
   void employeeShouldCreateWhenValid() {
      Assertions.assertEquals(validName, emp.getName());
      Assertions.assertEquals(validCpf, emp.getCpf());
      Assertions.assertEquals(validPhone, emp.getPhoneNumber());
      Assertions.assertEquals(validBirthDate, emp.getDateOfBirth());
      Assertions.assertEquals(validContractType, emp.getContractType());
      Assertions.assertEquals(validAdmissionDate, emp.getAdmissionDate());
      Assertions.assertNull(emp.getDismissalDate());
   }

   @Test
   void shouldNotCreateEmployeeWithNullName() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      null,
                      validCpf,
                      validPhone,
                      validBirthDate,
                      validContractType,
                      validAdmissionDate
              )
      );
   }

   @Test
   void shouldNotCreateEmployeeWithInvalidCpfLength() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      "123",
                      validPhone,
                      validBirthDate,
                      validContractType,
                      validAdmissionDate
              )
      );
   }

   @Test
   void shouldNotCreateEmployeeWithInvalidPhoneNumberLength() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      validCpf,
                      "999",
                      validBirthDate,
                      validContractType,
                      validAdmissionDate
              )
      );
   }

   @Test
   void shouldNotCreateEmployeeWithInvalidDateOfBirth() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      validCpf,
                      validPhone,
                      LocalDate.of(1960, 1, 1),
                      validContractType,
                      validAdmissionDate
              )
      );
   }

   @Test
   void shouldNotCreateEmployeeWithNullContractType() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      validCpf,
                      validPhone,
                      validBirthDate,
                      null,
                      validAdmissionDate
              )
      );
   }

   @Test
   void shouldNotCreateEmployeeWithAdmissionDateBeforeToday() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      validCpf,
                      validPhone,
                      validBirthDate,
                      validContractType,
                      LocalDate.now().minusDays(1)
              )
      );
   }

   @Test
   void shouldDismissEmployeeWithTodayDate() {
      emp.dismiss();

      Assertions.assertNotNull(emp.getDismissalDate());
      Assertions.assertEquals(LocalDate.now(), emp.getDismissalDate());
   }

   @Test
   void shouldNotAllowDismissTwice() {
      emp.dismiss();

      Assertions.assertThrows(
              IllegalStateException.class, () -> emp.dismiss()
      );
   }

   @Test
   void shouldNotAllowDismissBeforeAdmissionDate() {
      Employee futureEmployee = new Employee(
              "Ana",
              "12345678910",
              "73123456789",
              LocalDate.of(1990, 1, 1),
              ContractType.CLT,
              LocalDate.now().plusDays(1)
      );

      Assertions.assertThrows(
              IllegalStateException.class,
              futureEmployee::dismiss
      );
   }
}
