package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.entities.Apuracao;
import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoUseCase {

    private static Logger logger = LoggerFactory.getLogger(SessaoUseCase.class);

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    public SessaoUseCase(SessaoRepository sessaoRepository, PautaRepository pautaRepository, VotoRepository votoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.votoRepository = votoRepository;
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
        var sessoes = sessaoRepository.buscarSessoes();
        sessoes.forEach(sessao -> setApuracao(sessao));
        return sessoes;
    }

    public Sessao buscarSessao(Long id) {
        logger.info("Recuperando pauta da base de dados com o id {}", id);
        var sessaoOptional = sessaoRepository.buscarSessao(id);
        if(sessaoOptional.isEmpty()){
            logger.warn("Pauta com o id {} não localizada", id);
            throw new BadRequestException(String.format("Pauta com o id %d não localizada", id));
        }
        var sessao =  sessaoOptional.get();
        setApuracao(sessao);
        return sessao;
    }

    private void setApuracao(Sessao sessao) {
        var votosSim = votoRepository.countByValorAndSessao(true, sessao.getId());
        var votosNao = votoRepository.countByValorAndSessao(false, sessao.getId());
        sessao.setApuracao(new Apuracao(votosSim, votosNao));
    }

}
