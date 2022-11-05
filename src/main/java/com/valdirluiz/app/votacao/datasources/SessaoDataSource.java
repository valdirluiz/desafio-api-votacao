package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.SessaoJpaRepository;
import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessaoDataSource implements SessaoRepository {

    private final SessaoJpaRepository sessaoJpaRepository;

    public SessaoDataSource(SessaoJpaRepository sessaoJpaRepository) {
        this.sessaoJpaRepository = sessaoJpaRepository;
    }

    @Override
    public Sessao salvarSessao(Sessao sessao) {
        return sessaoJpaRepository.saveAndFlush(sessao);
    }

    @Override
    public List<Sessao> buscarSessoes() {
        return sessaoJpaRepository.findAll();
    }

    @Override
    public Optional<Sessao> buscarSessao(Long id) {
        return sessaoJpaRepository.findById(id);
    }
}
