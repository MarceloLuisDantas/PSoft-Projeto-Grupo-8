package br.edu.ufcg.psoft.arena.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EstudanteDTO(
        @NotBlank String nome,
        @NotBlank String matricula,
        @NotBlank @Email String email) {}
