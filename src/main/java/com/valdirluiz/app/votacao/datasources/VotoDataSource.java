package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.VotoJpaRepository;
import com.valdirluiz.app.votacao.entities.Voto;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoDataSource implements VotoRepository {

    private final VotoJpaRepository repository;

    public VotoDataSource(VotoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvarVoto(Voto voto) {
        this.repository.saveAndFlush(voto);
    }

    @Override
    public Optional<Voto> findByCpfAssociadoAndIdPauta(String cpfAssociado, Long idPauta) {
        return repository.findByCpfAssociadoAndIdPauta(cpfAssociado, idPauta);
    }
}
