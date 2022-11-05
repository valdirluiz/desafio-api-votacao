package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoUseCase {

    private static Logger logger = LoggerFactory.getLogger(SessaoUseCase.class);

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoUseCase(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
    }

    public Sessao salvarSessao(Sessao sessao){
        logger.info("Salvando uma nova sessão");
        sessao.validarCampos();
        if(pautaRepository.buscarPauta(sessao.getIdPauta()).isEmpty()){
            throw new BadRequestException(String.format("Pauta com o id %d não localizada", sessao.getIdPauta()));
        }
        sessao.calcularValidade();
        return this.sessaoRepository.salvarSessao(sessao);
    }

    public List<Sessao> buscarSessoes() {
        logger.info("Recuperando pautas da base de dados...");
        return sessaoRepository.buscarSessoes();
    }

    public Sessao buscarSessao(Long id) {
        logger.info("Recuperando pauta da base de dados com o id {}", id);
        var sessao = sessaoRepository.buscarSessao(id);
        if(sessao.isEmpty()){
            logger.warn("Pauta com o id {} não localizada", id);
            throw new BadRequestException(String.format("Pauta com o id %d não localizada", id));
        }
        return sessao.get();
    }

}
