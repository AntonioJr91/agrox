package afsj.agrox.services;

import afsj.agrox.dtos.EmployeeCreateDto;
import afsj.agrox.dtos.EmployeeResponseDto;
import afsj.agrox.dtos.EmployeeUpdateDto;
import afsj.agrox.entities.Employee;
import afsj.agrox.exceptions.ResourceNotFoundException;
import afsj.agrox.mapper.EmployeeMapper;
import afsj.agrox.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

   private final EmployeeRepository employeeRepository;

   public EmployeeService(EmployeeRepository employeeRepository) {
      this.employeeRepository = employeeRepository;
   }

   @Transactional(readOnly = true)
   public Page<EmployeeResponseDto> findAllEmployees(Pageable pageable) {
      return employeeRepository.findAll(pageable).map(emp -> EmployeeMapper.toDto(emp));
   }

   @Transactional(readOnly = true)
   public EmployeeResponseDto findEmployeeById(Long id) {
      Employee emp = employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
      return EmployeeMapper.toDto(emp);
   }

   @Transactional
   public EmployeeResponseDto createEmployee(EmployeeCreateDto dto) {
      Employee emp = employeeRepository.findByCpf(dto.cpf());

      if (emp != null) {
         throw new IllegalArgumentException();
      }

      Employee newEmployee = EmployeeMapper.toEntity(dto);

      Employee saved = employeeRepository.save(newEmployee);

      return EmployeeMapper.toDto(saved);
   }

   @Transactional
   public EmployeeResponseDto updateEmployeeDetails(Long id, EmployeeUpdateDto dto) {
      Employee employee = employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

      employee.updateDetails(
              dto.phoneNumber(),
              dto.contractType()
      );

      return EmployeeMapper.toDto(employee);
   }

   @Transactional
   public void deleteEmployee(Long id) {
      if (!employeeRepository.existsById(id)) {
         throw new ResourceNotFoundException();
      }
      employeeRepository.deleteById(id);
   }

}
