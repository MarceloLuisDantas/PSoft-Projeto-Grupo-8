package br.edu.ufcg.psoft.arena.dto;

import br.edu.ufcg.psoft.arena.model.TipoEstrategia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstrategiaDTO(
        @NotBlank String nome,
        @NotNull TipoEstrategia tipo,
        @NotBlank String descricao,
        @NotBlank String prompt) {}
