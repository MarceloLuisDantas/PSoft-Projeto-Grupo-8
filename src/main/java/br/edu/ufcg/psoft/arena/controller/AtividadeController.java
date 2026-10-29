package br.edu.ufcg.psoft.arena.controller;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/atividades")
@Tag(name = "Atividades (base para regras das US1 e US4)")
public class AtividadeController {

    private final AtividadeService service;

    public AtividadeController(AtividadeService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar atividade (turma ativa e problema ativo)")
    public Atividade criar(@RequestBody @Valid AtividadeDTO dto) { return service.criar(dto); }

    @GetMapping
    @Operation(summary = "Listar atividades (filtro opcional por turma)")
    public List<Atividade> listar(@RequestParam(required = false) Long turmaId) { return service.listar(turmaId); }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar atividade")
    public Atividade buscar(@PathVariable Long id) { return service.buscar(id); }
}
