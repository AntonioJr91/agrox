package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

import java.time.LocalDate;

public class EmployeeCreateDto {
   private String name;
   private String cpf;
   private String phoneNumber;
   private LocalDate dateOfBirth;
   private ContractType contractType;
   private LocalDate admissionDate;

   public EmployeeCreateDto() {
   }

   public EmployeeCreateDto(String name, String cpf, String phoneNumber,
                            LocalDate dateOfBirth, ContractType contractType, LocalDate admissionDate) {
      this.name = name;
      this.cpf = cpf;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
      this.contractType = contractType;
      this.admissionDate = admissionDate;
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

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public ContractType getContractType() {
      return contractType;
   }

   public LocalDate getAdmissionDate() {
      return admissionDate;
   }
}
