package afsj.agrox.dtos;

import java.time.Instant;

public record ErrorResponseDto(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}