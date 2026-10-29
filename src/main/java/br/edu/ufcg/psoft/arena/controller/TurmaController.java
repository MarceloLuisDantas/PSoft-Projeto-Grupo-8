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
@RequestMapping("/turmas")
@Tag(name = "Turmas (US4)")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar turma (criada como ATIVA)")
    public Turma criar(@RequestBody @Valid TurmaDTO dto) { return service.criar(dto); }

    @GetMapping
    @Operation(summary = "Listar turmas (filtro opcional por professor responsável)")
    public List<Turma> listar(@RequestParam(required = false) String professor) { return service.listar(professor); }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar turma")
    public Turma buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    @Operation(summary = "Editar turma")
    public Turma editar(@PathVariable Long id, @RequestBody @Valid TurmaDTO dto) { return service.editar(id, dto); }

    @PatchMapping("/{id}/encerrar")
    @Operation(summary = "Encerrar turma (status ENCERRADA)")
    public Turma encerrar(@PathVariable Long id) { return service.encerrar(id); }
}
