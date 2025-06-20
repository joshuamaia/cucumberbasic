package br.com.joshua.cucumberbasic.domain;

import jakarta.validation.constraints.NotBlank;

public record Category(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank(message = "Descrição é obrigatória") String description
) {}