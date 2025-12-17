package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

public class EmployeeUpdateDto {
   private String phoneNumber;
   private ContractType contractType;

   public EmployeeUpdateDto() {
   }

   public EmployeeUpdateDto(String phoneNumber, ContractType contractType) {
      this.phoneNumber = phoneNumber;
      this.contractType = contractType;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public ContractType getContractType() {
      return contractType;
   }
}
