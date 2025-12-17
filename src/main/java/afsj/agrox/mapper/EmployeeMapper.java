package afsj.agrox.mapper;

import afsj.agrox.dtos.EmployeeCreateDto;
import afsj.agrox.dtos.EmployeeResponseDto;
import afsj.agrox.entities.Employee;

import java.util.List;

public class EmployeeMapper {

   public static Employee toEntity(EmployeeCreateDto dto) {
      return new Employee(
              dto.getName(),
              dto.getCpf(),
              dto.getPhoneNumber(),
              dto.getDateOfBirth(),
              dto.getContractType(),
              dto.getAdmissionDate()
      );
   }

   public static EmployeeResponseDto toDto(Employee employee) {
      return new EmployeeResponseDto(
              employee.getId(),
              employee.getName(),
              employee.getCpf(),
              employee.getPhoneNumber(),
              employee.getDateOfBirth(),
              employee.getContractType(),
              employee.getAdmissionDate(),
              employee.getDismissalDate()
      );
   }

   public static List<EmployeeResponseDto> toDtoList(List<Employee> employees) {
      return employees.stream().map(EmployeeMapper::toDto).toList();
   }

}
