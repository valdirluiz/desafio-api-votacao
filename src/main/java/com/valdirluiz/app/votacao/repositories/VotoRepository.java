package com.valdirluiz.app.votacao.repositories;

import com.valdirluiz.app.votacao.entities.Voto;

import java.util.Optional;

public interface VotoRepository {

    void salvarVoto(Voto voto);
    Optional<Voto> findByCpfAssociadoAndIdPauta(String cpfAssociado, Long idPauta);
}
