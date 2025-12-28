package afsj.agrox.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateDTO(@NotBlank
                                @Size(min = 3, max = 50)
                                String name) {
}
