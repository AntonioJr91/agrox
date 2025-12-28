package afsj.agrox.dtos;

import jakarta.validation.constraints.NotNull;

public record ServiceOrderItemCreateDto(
        @NotNull
        Long productId,

        @NotNull
        int quantity

) {
}