package afsj.agrox.entities;


import afsj.agrox.enums.ContractType;
import afsj.agrox.validations.DomainValidation;

import java.time.LocalDate;

public class Employee {
   private Long id;
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

   public Long getId() {
      return id;
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

   public void dismiss(LocalDate dismissalDate) {
      validateIsDismissed();
      validateDismissalDate(dismissalDate);
      this.dismissalDate = dismissalDate;
   }

   @Override
   public boolean equals(Object o) {
      if (!(o instanceof Employee employee)) return false;
      return id != null && id.equals(employee.id);
   }

   @Override
   public int hashCode() {
      return getClass().hashCode();
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

   public boolean isDismissed() {
      return this.dismissalDate != null;
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

   private void validateName(String name) {
      DomainValidation.when(name == null, "Name is required");
      DomainValidation.when(name.isBlank(), "Name must not be blank");
      DomainValidation.when(name.length() < 3 || name.length() > 50, "Name must contain between 3 and 50 characters");
   }

   private void validateCpf(String cpf) {
      DomainValidation.when(cpf == null, "CPF is required");
      DomainValidation.when(cpf.length() != 11, "CPF must contain exactly 11 digits");
      DomainValidation.when(!cpf.matches("\\d{11}"), "CPF must contain only digits");
   }

   private void validatePhoneNumber(String phoneNumber) {
      DomainValidation.when(phoneNumber == null, "Phone number is required");
      DomainValidation.when(phoneNumber.length() != 11, "Phone number must contain 11 digits (DDD + 9-digit number)");
   }

   private void validateDateOfBirth(LocalDate dateOfBirth) {
      DomainValidation.when(dateOfBirth == null, "Date of birth is required");
      DomainValidation.when(dateOfBirth.getYear() < 1970, "Date of birth must be from 1970 onwards");
      DomainValidation.when(dateOfBirth.isAfter(LocalDate.now()), "Date of birth cannot be in the future");
   }

   private void validateContractType(ContractType contractType) {
      DomainValidation.when(contractType == null, "Contract type is required");
   }

   private void validateAdmissionDate(LocalDate admissionDate) {
      DomainValidation.when(admissionDate == null, "Admission date is required");
      DomainValidation.when(!admissionDate.equals(LocalDate.now()), "Admission date must be today");
   }

   private void validateDismissalDate(LocalDate dismissalDate) {
      DomainValidation.when(dismissalDate == null, "Dismissal date is required");
      DomainValidation.when(dismissalDate.isBefore(admissionDate), "Dismissal date cannot be before admission date");
      DomainValidation.when(dismissalDate.isAfter(LocalDate.now()), "Dismissal date cannot be in the future");
   }

   private void validateIsDismissed() {
      DomainValidation.when(isDismissed(), "Employee is already dismissed");
   }
}
