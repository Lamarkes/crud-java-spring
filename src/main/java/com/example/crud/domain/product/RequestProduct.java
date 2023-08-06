package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProduct(@NotBlank String name, @NotNull Number price_in_cents, Long id) {
}
