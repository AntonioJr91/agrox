package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

import java.time.LocalDate;

public record EmployeeResponseDto(
        Long id,
        String name,
        String cpf,
        String phoneNumber,
        LocalDate dateOfBirth,
        ContractType contractType,
        LocalDate admissionDate,
        LocalDate dismissalDate
) {

}