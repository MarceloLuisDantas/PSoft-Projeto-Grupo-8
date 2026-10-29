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

    public TurmaService(TurmaRepository repo) {
        this.repo = repo;
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

    private void copiar(TurmaDTO dto, Turma t) {
        t.setNome(dto.nome());
        t.setCodigo(dto.codigo());
        t.setPeriodoLetivo(dto.periodoLetivo());
        t.setProfessorResponsavel(dto.professorResponsavel());
    }
}
