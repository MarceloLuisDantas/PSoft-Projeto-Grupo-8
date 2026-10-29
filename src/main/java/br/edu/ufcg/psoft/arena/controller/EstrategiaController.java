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
@RequestMapping("/estrategias")
@Tag(name = "Estratégias de Avaliação (US3)")
public class EstrategiaController {

    private final EstrategiaService service;

    public EstrategiaController(EstrategiaService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar estratégia de avaliação")
    public EstrategiaAvaliacao criar(@RequestBody @Valid EstrategiaDTO dto) { return service.criar(dto); }

    @GetMapping
    @Operation(summary = "Listar estratégias (filtro opcional por tipo)")
    public List<EstrategiaAvaliacao> listar(@RequestParam(required = false) TipoEstrategia tipo) { return service.listar(tipo); }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar estratégia")
    public EstrategiaAvaliacao buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    @Operation(summary = "Editar estratégia")
    public EstrategiaAvaliacao editar(@PathVariable Long id, @RequestBody @Valid EstrategiaDTO dto) { return service.editar(id, dto); }

    @PatchMapping("/{id}/prompt")
    @Operation(summary = "Alterar somente o prompt da estratégia")
    public EstrategiaAvaliacao alterarPrompt(@PathVariable Long id, @RequestBody @Valid PromptDTO dto) {
        return service.alterarPrompt(id, dto.prompt());
    }

    @GetMapping("/tipos")
    @Operation(summary = "Listar tipos de estratégia disponíveis")
    public TipoEstrategia[] tipos() { return TipoEstrategia.values(); }
}
