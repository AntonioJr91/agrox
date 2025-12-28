package afsj.agrox.dtos;

public record ServiceOrderItemResponseDto(
        Long id,

        Long productId,

        String productName,

        int quantity
) {
}