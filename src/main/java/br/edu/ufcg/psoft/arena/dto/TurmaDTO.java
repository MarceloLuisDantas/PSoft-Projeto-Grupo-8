package br.edu.ufcg.psoft.arena.dto;

import jakarta.validation.constraints.NotBlank;

public record TurmaDTO(
        @NotBlank String nome,
        @NotBlank String codigo,
        @NotBlank String periodoLetivo,
        @NotBlank String professorResponsavel) {}
