package afsj.agrox.entities;


import afsj.agrox.enums.ContractType;

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

   public Employee() {
   }

   public Employee(String name, String cpf, String phoneNumber, LocalDate dateOfBirth,
                   ContractType contractType, LocalDate admissionDate, LocalDate dismissalDate) {
      this.name = name;
      this.cpf = cpf;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
      this.contractType = contractType;
      this.admissionDate = admissionDate;
      this.dismissalDate = dismissalDate;
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
      this.phoneNumber = phoneNumber;
   }

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public ContractType getContractType() {
      return contractType;
   }

   public void setContractType(ContractType contractType) {
      this.contractType = contractType;
   }

   public LocalDate getAdmissionDate() {
      return admissionDate;
   }

   public LocalDate getDismissalDate() {
      return dismissalDate;
   }

   public void setDismissalDate(LocalDate dismissalDate) {
      this.dismissalDate = dismissalDate;
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
}
