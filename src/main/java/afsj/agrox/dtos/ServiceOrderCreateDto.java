package afsj.agrox.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ServiceOrderCreateDto(
        @NotBlank
        String description,

        @NotNull
        Long employeeId,

        List<ServiceOrderItemCreateDto> items
) {
}