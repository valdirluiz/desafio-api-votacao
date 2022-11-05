package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaUseCase {

    private static Logger logger = LoggerFactory.getLogger(PautaUseCase.class);


    private final PautaRepository repository;

    public PautaUseCase(PautaRepository repository) {
        this.repository = repository;
    }

    public Pauta salvarPauta(Pauta pauta){
        logger.info("Salvando uma nova pauta...");
        pauta.validarCampos();
        return repository.salvarPauta(pauta);
    }

    public List<Pauta> buscarPautas() {
        logger.info("Recuperando pautas da base de dados...");
        return repository.buscarPautas();
    }

    public Pauta buscarPauta(Long id) {
        logger.info("Recuperando pauta da base de dados com o id {}", id);
        var pauta = repository.buscarPauta(id);
        if(pauta.isEmpty()){
            logger.warn("Pauta com o id {} não localizada", id);
            throw new BadRequestException(String.format("Pauta com o id %d não localizada", id));
        }
        return pauta.get();
    }
}
