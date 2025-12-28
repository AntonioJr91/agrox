package afsj.agrox.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockAmountDto(
        @NotNull
        @Min(1)
        Integer amount
) {
}