package afsj.agrox.dtos;

import afsj.agrox.enums.ContractType;

public record EmployeeUpdateDto(
        String phoneNumber,
        ContractType contractType
) {
}
