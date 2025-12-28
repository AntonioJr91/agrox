package afsj.agrox.controllers;

import afsj.agrox.dtos.EmployeeCreateDto;
import afsj.agrox.dtos.EmployeeResponseDto;
import afsj.agrox.dtos.EmployeeUpdateDto;
import afsj.agrox.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

   private final EmployeeService employeeService;

   public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
   }

   @GetMapping
   public ResponseEntity<Page<EmployeeResponseDto>> getAll(Pageable pageable) {
      return ResponseEntity.ok(employeeService.findAllEmployees(pageable));
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
      return ResponseEntity.ok(employeeService.findEmployeeById(id));
   }

   @PostMapping
   public ResponseEntity<EmployeeResponseDto> create(@RequestBody @Valid EmployeeCreateDto dto) {
      EmployeeResponseDto emp = employeeService.createEmployee(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.id()).toUri();
      return ResponseEntity.created(uri).body(emp);
   }


   @PatchMapping(value = "/{id}")
   public ResponseEntity<EmployeeResponseDto> update(@PathVariable Long id, @RequestBody @Valid EmployeeUpdateDto dto) {
      return ResponseEntity.ok(employeeService.updateEmployeeDetails(id, dto));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable Long id) {
      employeeService.deleteEmployee(id);
      return ResponseEntity.noContent().build();
   }
}
