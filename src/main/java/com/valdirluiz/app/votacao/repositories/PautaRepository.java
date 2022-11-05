package com.valdirluiz.app.votacao.repositories;

import com.valdirluiz.app.votacao.entities.Pauta;

import java.util.List;
import java.util.Optional;

public interface PautaRepository {

    Pauta salvarPauta(Pauta pauta);
    List<Pauta> buscarPautas();
    Optional<Pauta> buscarPauta(Long id);
}
