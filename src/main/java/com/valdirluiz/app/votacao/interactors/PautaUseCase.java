package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.entities.Apuracao;
import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaUseCase {

    private static Logger logger = LoggerFactory.getLogger(PautaUseCase.class);

    private final PautaRepository repository;
    private final VotoRepository votoRepository;

    public PautaUseCase(PautaRepository repository, VotoRepository votoRepository) {
        this.repository = repository;
        this.votoRepository = votoRepository;
    }

    public Pauta salvarPauta(Pauta pauta){
        logger.info("Salvando uma nova pauta...");
        pauta.validarCampos();
        return repository.salvarPauta(pauta);
    }

    public List<Pauta> buscarPautas() {
        logger.info("Recuperando pautas da base de dados...");
        var pautas =  repository.buscarPautas();
        pautas.forEach(pauta -> setApuracao(pauta));
        return  pautas;
    }

    public Pauta buscarPauta(Long id) {
        logger.info("Recuperando pauta da base de dados com o id {}", id);
        var pautaOptional = repository.buscarPauta(id);
        if(pautaOptional.isEmpty()){
            logger.warn("Pauta com o id {} não localizada", id);
            throw new BadRequestException(String.format("Pauta com o id %d não localizada", id));
        }
        var pauta =  pautaOptional.get();
        setApuracao(pauta);
        return pauta;
    }

    private void setApuracao(Pauta pauta) {
        var votosSim = votoRepository.countByValorAndPauta(true, pauta.getId());
        var votosNao = votoRepository.countByValorAndPauta(false, pauta.getId());
        pauta.setApuracao(new Apuracao(votosSim, votosNao));
    }
}
