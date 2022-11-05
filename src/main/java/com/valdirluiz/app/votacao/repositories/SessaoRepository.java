package com.valdirluiz.app.votacao.repositories;

import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.entities.Sessao;

import java.util.List;
import java.util.Optional;

public interface SessaoRepository {

    Sessao salvarSessao(Sessao sessao);
    List<Sessao> buscarSessoes();
    Optional<Sessao> buscarSessao(Long id);
}
