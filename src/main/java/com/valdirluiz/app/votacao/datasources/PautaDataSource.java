package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.PautaJpaRepository;
import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaDataSource implements PautaRepository {

    private final PautaJpaRepository repository;

    public PautaDataSource(PautaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pauta salvarPauta(Pauta pauta) {
        return repository.saveAndFlush(pauta);
    }

    @Override
    public List<Pauta> buscarPautas() {
        return repository.findAll();
    }

    @Override
    public Optional<Pauta> buscarPauta(Long id) {
        return repository.findById(id);
    }

}
