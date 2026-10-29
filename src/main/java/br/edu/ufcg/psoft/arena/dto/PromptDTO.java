package br.edu.ufcg.psoft.arena.dto;

import jakarta.validation.constraints.NotBlank;

public record PromptDTO(@NotBlank String prompt) {}
