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
@RequestMapping("/estudantes")
@Tag(name = "Estudantes (US2)")
public class EstudanteController {

    private final EstudanteService service;

    public EstudanteController(EstudanteService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar estudante (matrícula única)")
    public Estudante criar(@RequestBody @Valid EstudanteDTO dto) { return service.criar(dto); }

    @GetMapping
    @Operation(summary = "Listar estudantes")
    public List<Estudante> listar() { return service.listar(); }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar estudante")
    public Estudante buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    @Operation(summary = "Editar estudante")
    public Estudante editar(@PathVariable Long id, @RequestBody @Valid EstudanteDTO dto) { return service.editar(id, dto); }
}
