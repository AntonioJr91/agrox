package afsj.agrox.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ServiceOrderItemUpdateQuantityDto(
        @NotNull
        @Min(1)
        int amount
) {
}