package br.edu.ufcg.psoft.arena.repository;

import br.edu.ufcg.psoft.arena.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
    List<Turma> findByProfessorResponsavel(String professorResponsavel);
}
