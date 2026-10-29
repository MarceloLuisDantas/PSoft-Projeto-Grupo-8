package br.edu.ufcg.psoft.arena.repository;

import br.edu.ufcg.psoft.arena.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    boolean existsByMatricula(String matricula);
    boolean existsByMatriculaAndIdNot(String matricula, Long id);
}
