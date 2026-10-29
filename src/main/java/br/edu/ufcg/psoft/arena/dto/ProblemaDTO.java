package br.edu.ufcg.psoft.arena.dto;

import jakarta.validation.constraints.NotBlank;

public record ProblemaDTO(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotBlank String requisitosFuncionais,
        @NotBlank String requisitosProjeto) {}
