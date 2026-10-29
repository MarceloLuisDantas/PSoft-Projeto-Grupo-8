package br.edu.ufcg.psoft.arena.service;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.exception.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EstrategiaService {

    private final EstrategiaAvaliacaoRepository repo;

    public EstrategiaService(EstrategiaAvaliacaoRepository repo) { this.repo = repo; }

    public EstrategiaAvaliacao criar(EstrategiaDTO dto) {
        EstrategiaAvaliacao e = new EstrategiaAvaliacao();
        copiar(dto, e);
        return repo.save(e);
    }

    public List<EstrategiaAvaliacao> listar(TipoEstrategia tipo) {
        return tipo == null ? repo.findAll() : repo.findByTipo(tipo);
    }

    public EstrategiaAvaliacao buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new NaoEncontradoException("Estratégia não encontrada: " + id));
    }

    public EstrategiaAvaliacao editar(Long id, EstrategiaDTO dto) {
        EstrategiaAvaliacao e = buscar(id);
        copiar(dto, e);
        return repo.save(e);
    }

    public EstrategiaAvaliacao alterarPrompt(Long id, String prompt) {
        EstrategiaAvaliacao e = buscar(id);
        e.setPrompt(prompt);
        return repo.save(e);
    }

    private void copiar(EstrategiaDTO dto, EstrategiaAvaliacao e) {
        e.setNome(dto.nome());
        e.setTipo(dto.tipo());
        e.setDescricao(dto.descricao());
        e.setPrompt(dto.prompt());
    }
}
