package br.edu.ufcg.psoft.arena.service;

import br.edu.ufcg.psoft.arena.dto.*;
import br.edu.ufcg.psoft.arena.exception.*;
import br.edu.ufcg.psoft.arena.model.*;
import br.edu.ufcg.psoft.arena.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EstudanteService {

    private final EstudanteRepository repo;

    public EstudanteService(EstudanteRepository repo) { this.repo = repo; }

    public Estudante criar(EstudanteDTO dto) {
        if (repo.existsByMatricula(dto.matricula())) {
            throw new RegraNegocioException("Já existe estudante com a matrícula " + dto.matricula());
        }
        Estudante e = new Estudante();
        copiar(dto, e);
        return repo.save(e);
    }

    public List<Estudante> listar() { return repo.findAll(); }

    public Estudante buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new NaoEncontradoException("Estudante não encontrado: " + id));
    }

    public Estudante editar(Long id, EstudanteDTO dto) {
        Estudante e = buscar(id);
        if (repo.existsByMatriculaAndIdNot(dto.matricula(), id)) {
            throw new RegraNegocioException("Já existe estudante com a matrícula " + dto.matricula());
        }
        copiar(dto, e);
        return repo.save(e);
    }

    private void copiar(EstudanteDTO dto, Estudante e) {
        e.setNome(dto.nome());
        e.setMatricula(dto.matricula());
        e.setEmail(dto.email());
    }
}
