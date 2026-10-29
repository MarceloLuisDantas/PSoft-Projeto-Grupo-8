package br.edu.ufcg.psoft.arena.service;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.exception.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProblemaService {

    private final ProblemaRepository repo;
    private final AtividadeRepository atividadeRepo;

    public ProblemaService(ProblemaRepository repo, AtividadeRepository atividadeRepo) {
        this.repo = repo;
        this.atividadeRepo = atividadeRepo;
    }

    public Problema criar(ProblemaDTO dto) {
        Problema p = new Problema();
        copiar(dto, p);
        p.setStatus(StatusProblema.ATIVO);
        return repo.save(p);
    }

    public List<Problema> listar() { return repo.findAll(); }

    public Problema buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new NaoEncontradoException("Problema não encontrado: " + id));
    }

    public Problema editar(Long id, ProblemaDTO dto) {
        Problema p = buscar(id);
        copiar(dto, p);
        return repo.save(p);
    }

    public Problema desativar(Long id) {
        Problema p = buscar(id);
        p.setStatus(StatusProblema.INATIVO);
        return repo.save(p);
    }

    public Problema ativar(Long id) {
        Problema p = buscar(id);
        p.setStatus(StatusProblema.ATIVO);
        return repo.save(p);
    }

    public void remover(Long id) {
        Problema p = buscar(id);
        if (atividadeRepo.existsByProblemaId(id)) {
            throw new RegraNegocioException("Problema já utilizado em atividade não pode ser removido.");
        }
        repo.delete(p);
    }

    private void copiar(ProblemaDTO dto, Problema p) {
        p.setTitulo(dto.titulo());
        p.setDescricao(dto.descricao());
        p.setRequisitosFuncionais(dto.requisitosFuncionais());
        p.setRequisitosProjeto(dto.requisitosProjeto());
    }
}
