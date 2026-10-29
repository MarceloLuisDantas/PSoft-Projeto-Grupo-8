package br.edu.ufcg.psoft.arena.repository;

import br.edu.ufcg.psoft.arena.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    boolean existsByProblemaId(Long problemaId);
    List<Atividade> findByTurmaId(Long turmaId);
}
