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
   void shouldCreateWhenValid() {
      Assertions.assertEquals(validName, emp.getName());
      Assertions.assertEquals(validCpf, emp.getCpf());
      Assertions.assertEquals(validPhone, emp.getPhoneNumber());
      Assertions.assertEquals(validBirthDate, emp.getDateOfBirth());
      Assertions.assertEquals(validContractType, emp.getContractType());
      Assertions.assertEquals(validAdmissionDate, emp.getAdmissionDate());
   }

   @Test
   void shouldThrowExceptionWhenCreateEmployeeWithNullName() {
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
   void shouldThrowExceptionWhenCreateEmployeeWithInvalidCpfLength() {
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
   void shouldThrowExceptionWhenCreateEmployeeWithInvalidDateOfBirth() {
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
   void shouldThrowExceptionWhenCreateEmployeeWithNullContractType() {
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
   void shouldThrowExceptionWhenCreateEmployeeWithAdmissionDateInPast() {
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
   void shouldThrowExceptionWhenCreateEmployeeWithAdmissionDateInFuture() {
      Assertions.assertThrows(
              DomainException.class,
              () -> new Employee(
                      validName,
                      validCpf,
                      validPhone,
                      validBirthDate,
                      validContractType,
                      LocalDate.now().plusDays(1)
              )
      );
   }

   @Test
   void shouldDismissEmployeeWithTodayDate() {
      emp.dismiss(LocalDate.now());

      Assertions.assertNotNull(emp.getDismissalDate());
      Assertions.assertEquals(LocalDate.now(), emp.getDismissalDate());
   }

   @Test
   void shouldThrowExceptionWhenAllowDismissTwice() {
      emp.dismiss(LocalDate.now());

      Assertions.assertThrows(
              DomainException.class, () -> emp.dismiss(LocalDate.now())
      );
   }

   @Test
   void shouldThrowExceptionWhenAllowDismissBeforeAdmissionDate() {
      Employee employee = new Employee(
              "Ana",
              "12345678910",
              "73123456789",
              LocalDate.of(1990, 1, 1),
              ContractType.CLT,
              LocalDate.now()
      );

      LocalDate dateBeforeAdmission = employee.getAdmissionDate().minusDays(1);

      Assertions.assertThrows(
              DomainException.class,
              () -> employee.dismiss(dateBeforeAdmission)
      );
   }

   @Test
   void shouldThrowExceptionWhenDismissInFuture() {
      Assertions.assertThrows(
              DomainException.class,
              () -> emp.dismiss(LocalDate.now().plusDays(1))
      );
   }
}
