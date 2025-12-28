package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

import java.time.LocalDate;

public record EmployeeCreateDto(
        String name,
        String cpf,
        String phoneNumber,
        LocalDate dateOfBirth,
        ContractType contractType,
        LocalDate admissionDate
) {
}
