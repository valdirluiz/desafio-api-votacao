package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.entities.Voto;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.CpfRepository;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class VotoUseCase {

    private static Logger logger = LoggerFactory.getLogger(VotoUseCase.class);

    private final SessaoRepository sessaoRepository;
    private final CpfRepository cpfRepository;

    public VotoUseCase(SessaoRepository sessaoRepository, CpfRepository cpfRepository) {
        this.sessaoRepository = sessaoRepository;
        this.cpfRepository = cpfRepository;
    }

    public void computarVoto(Voto voto){
        logger.info("Iniciando processo de computação de voto");
        voto.validarCampos();
        var sessao = sessaoRepository.buscarSessao(voto.getIdSessao())
                .orElseThrow(sessaoNaoLocalizada(voto));
        sessao.validaExpiracao();
        validaCpf(voto);
    }

    private void validaCpf(Voto voto) {
        try {
            var statusCpf = cpfRepository.consultaCpf(voto.getCpfAssociado());
            if(!"ABLE_TO_VOTE".equals(statusCpf.getStatus())){
                throw new BadRequestException("CPF não habilitado para votar");
            }
        } catch (BadRequestException e){
            throw e;
        } catch (Exception e) {
            logger.error("Falha ao validar cpf", e);
            throw new BadRequestException("CPF inválido");
        }
    }

    private static Supplier<BadRequestException> sessaoNaoLocalizada(Voto voto) {
        return () ->
                new BadRequestException(String.format("Sessão com o id %d não localizada", voto.getIdSessao()));
    }
}