package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

import java.time.LocalDate;

public class EmployeeResponseDto {
   private Long id;
   private String name;
   private String cpf;
   private String phoneNumber;
   private LocalDate dateOfBirth;
   private ContractType contractType;
   private LocalDate admissionDate;
   private LocalDate dismissalDate;

   public EmployeeResponseDto() {
   }

   public EmployeeResponseDto(Long id, String name, String cpf, String phoneNumber,
                              LocalDate dateOfBirth, ContractType contractType,
                              LocalDate admissionDate, LocalDate dismissalDate) {
      this.id = id;
      this.name = name;
      this.cpf = cpf;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
      this.contractType = contractType;
      this.admissionDate = admissionDate;
      this.dismissalDate = dismissalDate;
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

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public ContractType getContractType() {
      return contractType;
   }

   public LocalDate getAdmissionDate() {
      return admissionDate;
   }

   public LocalDate getDismissalDate() {
      return dismissalDate;
   }
}
