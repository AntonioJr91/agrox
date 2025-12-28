package afsj.agrox.dtos;

import afsj.agrox.enums.UnitOfMeasure;

public record ProductResponseDto(
        Long id,
        String name,
        UnitOfMeasure unitOfMeasure,
        CategoryResponseDTO categoryDto,
        int stockQuantity
) {
}