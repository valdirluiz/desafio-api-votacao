package com.valdirluiz.app.votacao.datasources.database;

import com.valdirluiz.app.votacao.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotoJpaRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByCpfAssociadoAndIdPauta(String cpfAssociado, Long idPauta);
}
