package br.edu.ufcg.psoft.arena.repository;

import br.edu.ufcg.psoft.arena.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstrategiaAvaliacaoRepository extends JpaRepository<EstrategiaAvaliacao, Long> {
    List<EstrategiaAvaliacao> findByTipo(TipoEstrategia tipo);
}
