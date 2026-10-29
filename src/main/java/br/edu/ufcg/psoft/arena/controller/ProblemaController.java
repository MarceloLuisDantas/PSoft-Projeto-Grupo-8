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
@RequestMapping("/problemas")
@Tag(name = "Problemas (US1)")
public class ProblemaController {

    private final ProblemaService service;

    public ProblemaController(ProblemaService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar problema (criado como ATIVO)")
    public Problema criar(@RequestBody @Valid ProblemaDTO dto) { return service.criar(dto); }

    @GetMapping
    @Operation(summary = "Listar problemas")
    public List<Problema> listar() { return service.listar(); }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar problema")
    public Problema buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    @Operation(summary = "Editar problema")
    public Problema editar(@PathVariable Long id, @RequestBody @Valid ProblemaDTO dto) { return service.editar(id, dto); }

    @PatchMapping("/{id}/desativar")
    @Operation(summary = "Desativar problema (status INATIVO)")
    public Problema desativar(@PathVariable Long id) { return service.desativar(id); }

    @PatchMapping("/{id}/ativar")
    @Operation(summary = "Reativar problema")
    public Problema ativar(@PathVariable Long id) { return service.ativar(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover problema (somente se nunca usado em atividade)")
    public void remover(@PathVariable Long id) { service.remover(id); }
}
