package br.edu.ufcg.psoft.arena.service;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.exception.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TurmaService {

    private final TurmaRepository repo;
    private final EstudanteService estudanteService;

    public TurmaService(TurmaRepository repo, EstudanteService estudanteService) {
        this.repo = repo;
        this.estudanteService = estudanteService;
    }

    public Turma criar(TurmaDTO dto) {
        if (repo.existsByCodigo(dto.codigo())) {
            throw new RegraNegocioException("Já existe turma com o código " + dto.codigo());
        }
        Turma t = new Turma();
        copiar(dto, t);
        t.setStatus(StatusTurma.ATIVA);
        return repo.save(t);
    }

    public List<Turma> listar(String professor) {
        return professor == null ? repo.findAll() : repo.findByProfessorResponsavel(professor);
    }

    public Turma buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new NaoEncontradoException("Turma não encontrada: " + id));
    }

    public Turma editar(Long id, TurmaDTO dto) {
        Turma t = buscar(id);
        if (repo.existsByCodigoAndIdNot(dto.codigo(), id)) {
            throw new RegraNegocioException("Já existe turma com o código " + dto.codigo());
        }
        copiar(dto, t);
        return repo.save(t);
    }

    public Turma encerrar(Long id) {
        Turma t = buscar(id);
        t.setStatus(StatusTurma.ENCERRADA);
        return repo.save(t);
    }

    @Transactional
    public Turma adicionarEstudante(Long turmaId, Long estudanteId) {
        Turma t = buscar(turmaId);
        if (t.getStatus() == StatusTurma.ENCERRADA) {
            throw new RegraNegocioException("Turma encerrada não pode receber novos estudantes.");
        }
        Estudante e = estudanteService.buscar(estudanteId);
        if (!t.getEstudantes().add(e)) {
            throw new RegraNegocioException("Estudante já está associado a esta turma.");
        }
        return repo.save(t);
    }

    @Transactional
    public Turma removerEstudante(Long turmaId, Long estudanteId) {
        Turma t = buscar(turmaId);
        Estudante e = estudanteService.buscar(estudanteId);
        if (!t.getEstudantes().remove(e)) {
            throw new RegraNegocioException("Estudante não está associado a esta turma.");
        }
        return repo.save(t);
    }

    public Set<Estudante> listarEstudantes(Long turmaId) {
        return buscar(turmaId).getEstudantes();
    }

    /** Usado pelas atividades: só estudantes associados podem participar. */
    public boolean estudantePertence(Long turmaId, Long estudanteId) {
        return buscar(turmaId).getEstudantes().stream().anyMatch(e -> e.getId().equals(estudanteId));
    }

    private void copiar(TurmaDTO dto, Turma t) {
        t.setNome(dto.nome());
        t.setCodigo(dto.codigo());
        t.setPeriodoLetivo(dto.periodoLetivo());
        t.setProfessorResponsavel(dto.professorResponsavel());
    }
}
