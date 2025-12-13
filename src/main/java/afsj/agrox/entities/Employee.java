package afsj.agrox.entities;


import afsj.agrox.enums.ContractType;
import afsj.agrox.validations.DomainValidation;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
   private String name;
   private String cpf;
   private String phoneNumber;
   private LocalDate dateOfBirth;
   private ContractType contractType;
   private LocalDate admissionDate;
   private LocalDate dismissalDate;

   protected Employee() {
   }

   public Employee(String name, String cpf, String phoneNumber, LocalDate dateOfBirth,
                   ContractType contractType, LocalDate admissionDate) {
      validation(name, cpf, phoneNumber, dateOfBirth, contractType, admissionDate);
      this.name = name;
      this.cpf = cpf;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
      this.contractType = contractType;
      this.admissionDate = admissionDate;
      this.dismissalDate = null;
   }


   public String getName() {
      return name;
   }

   public String getCpf() {
      return cpf;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      validatePhoneNumber(phoneNumber);
      this.phoneNumber = phoneNumber;
   }

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public ContractType getContractType() {
      return contractType;
   }

   public void setContractType(ContractType contractType) {
      validateContractType(contractType);
      this.contractType = contractType;
   }

   public LocalDate getAdmissionDate() {
      return admissionDate;
   }

   public LocalDate getDismissalDate() {
      return dismissalDate;
   }

   public void dismiss() {
      if (isDismissed()) {
         throw new IllegalStateException("Employee has already been dismissed");
      }

      LocalDate today = LocalDate.now();

      if (today.isBefore(this.admissionDate)) {
         throw new IllegalStateException("Dismissal date before admission date");
      }

      this.dismissalDate = today;
   }

   private boolean isDismissed() {
      return this.dismissalDate != null;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      Employee employee = (Employee) o;
      return Objects.equals(cpf, employee.cpf);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(cpf);
   }

   @Override
   public String toString() {
      return "Employee{" +
              "name='" + name + '\'' +
              ", cpf='" + cpf + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", dateOfBirth=" + dateOfBirth +
              ", contractType=" + contractType +
              ", admissionDate=" + admissionDate +
              ", dismissalDate=" + dismissalDate +
              '}';
   }

   private void validation(String name, String cpf, String phoneNumber,
                           LocalDate dateOfBirth, ContractType contractType,
                           LocalDate admissionDate) {

      validateName(name);
      validateCpf(cpf);
      validatePhoneNumber(phoneNumber);
      validateDateOfBirth(dateOfBirth);
      validateContractType(contractType);
      validateAdmissionDate(admissionDate);
   }

   private static void validateName(String name) {
      DomainValidation.when(name == null, "Name is required");
      DomainValidation.when(name.isBlank(), "Name must not be blank");
      DomainValidation.when(name.length() < 3 || name.length() > 50, "Name must contain between 3 and 50 characters");
   }

   private static void validateCpf(String cpf) {
      DomainValidation.when(cpf == null, "CPF is required");
      DomainValidation.when(cpf.length() != 11, "CPF must contain exactly 11 digits");
   }

   private static void validatePhoneNumber(String phoneNumber) {
      DomainValidation.when(phoneNumber == null, "Phone number is required");
      DomainValidation.when(phoneNumber.length() != 11, "Phone number must contain 11 digits (DDD + 9-digit number)");
   }

   private static void validateDateOfBirth(LocalDate dateOfBirth) {
      DomainValidation.when(dateOfBirth == null, "Date of birth is required");
      DomainValidation.when(dateOfBirth.getYear() < 1970, "Date of birth must be from 1970 onwards");
   }

   private static void validateContractType(ContractType contractType) {
      DomainValidation.when(contractType == null, "Contract type is required");
   }

   private static void validateAdmissionDate(LocalDate admissionDate) {
      DomainValidation.when(admissionDate == null, "Admission date is required");
      DomainValidation.when(admissionDate.isBefore(LocalDate.now()), "Admission date cannot be before the current date");
   }
}
