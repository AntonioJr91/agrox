package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductUpdateDto(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,

        @NotNull
        UnitOfMeasure unitOfMeasure,

        @NotNull
        Long categoryId
) {
}