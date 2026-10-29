package br.edu.ufcg.psoft.arena.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record AtividadeDTO(
        @NotBlank String titulo,
        @NotNull Long turmaId,
        @NotNull Long problemaId,
        Set<Long> estrategiaIds) {}
