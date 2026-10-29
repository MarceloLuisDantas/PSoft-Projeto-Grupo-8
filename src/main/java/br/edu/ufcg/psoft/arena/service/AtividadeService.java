package br.edu.ufcg.psoft.arena.service;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.exception.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AtividadeService {

    private final AtividadeRepository repo;
    private final TurmaService turmaService;
    private final ProblemaService problemaService;
    private final EstrategiaService estrategiaService;

    public AtividadeService(AtividadeRepository repo, TurmaService turmaService,
                            ProblemaService problemaService, EstrategiaService estrategiaService) {
        this.repo = repo;
        this.turmaService = turmaService;
        this.problemaService = problemaService;
        this.estrategiaService = estrategiaService;
    }

    public Atividade criar(AtividadeDTO dto) {
        Turma turma = turmaService.buscar(dto.turmaId());
        if (turma.getStatus() == StatusTurma.ENCERRADA) {
            throw new RegraNegocioException("Turma encerrada não pode receber novas atividades.");
        }
        Problema problema = problemaService.buscar(dto.problemaId());
        if (problema.getStatus() == StatusProblema.INATIVO) {
            throw new RegraNegocioException("Problema inativo não pode ser associado a novas atividades.");
        }
        Atividade a = new Atividade();
        a.setTitulo(dto.titulo());
        a.setTurma(turma);
        a.setProblema(problema);
        if (dto.estrategiaIds() != null) {
            for (Long id : dto.estrategiaIds()) {
                a.getEstrategias().add(estrategiaService.buscar(id));
            }
        }
        return repo.save(a);
    }

    public List<Atividade> listar(Long turmaId) {
        return turmaId == null ? repo.findAll() : repo.findByTurmaId(turmaId);
    }

    public Atividade buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new NaoEncontradoException("Atividade não encontrada: " + id));
    }
}
