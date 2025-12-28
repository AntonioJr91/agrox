package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductCreateDto(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,

        @NotNull
        UnitOfMeasure unitOfMeasure,

        @NotNull
        Long categoryId,

        @NotNull
        @Min(1)
        int stockQuantity
) {
}


