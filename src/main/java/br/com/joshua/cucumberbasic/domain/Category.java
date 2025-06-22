package br.com.joshua.cucumberbasic.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record Category(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank(message = "Descrição é obrigatória") String description,
        @DecimalMin(value = "0.00", inclusive = true, message = "Preço não pode ser negativo")
        BigDecimal price
) {}