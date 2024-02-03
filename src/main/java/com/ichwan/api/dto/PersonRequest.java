package com.ichwan.api.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonRequest(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String address) {
}
