package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SessaoUseCaseTest {

    @InjectMocks
    private SessaoUseCase sessaoUseCase;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private VotoRepository votoRepository;

    @Test
    void deveCadastrarSessaoComSucesso(){
        var sessao = Factory.getSessao();
        when(sessaoRepository.salvarSessao(any())).thenReturn(sessao);
        when(pautaRepository.buscarPauta(anyLong())).thenReturn(Optional.of(Factory.getPauta()));
        assertDoesNotThrow(()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveDarFalhaAoCadastrarSessaoComNomeNulo(){
        var sessao = Factory.getSessao();
        sessao.setNome(null);
        when(pautaRepository.buscarPauta(anyLong())).thenReturn(Optional.of(Factory.getPauta()));
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveDarFalhaAoCadastrarSessaoComNomeVazio(){
        var sessao = Factory.getSessao();
        sessao.setNome("");
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveDarFalhaAoCadastrarSessaoSemValidade(){
        var sessao = Factory.getSessao();
        sessao.setValidadeMinutos(null);
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveDarFalhaAoCadastrarSessaoSemPauta(){
        var sessao = Factory.getSessao();
        sessao.setIdPauta(null);
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveDarFalhaAoNaoEncontrarPauta(){
        var sessao = Factory.getSessao();
        when(pautaRepository.buscarPauta(anyLong())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.salvarSessao(sessao));
    }

    @Test
    void deveRecuperarSessoes(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(sessaoRepository.buscarSessoes()).thenReturn(Factory.getSessoes());
        var sessoes  = sessaoUseCase.buscarSessoes();
        assertFalse(sessoes.isEmpty());
    }

    @Test
    void deveRecuperarSessao(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(sessaoRepository.buscarSessao(anyLong())).thenReturn(Optional.of(Factory.getSessao()));
        var sessao  = sessaoUseCase.buscarSessao(1l);
        assertTrue(Objects.nonNull(sessao));
        assertTrue(Objects.nonNull(sessao.getApuracao()));
        assertTrue(Objects.nonNull(sessao.getApuracao().getVotosNao()));
        assertTrue(Objects.nonNull(sessao.getApuracao().getVotosSim()));
        assertTrue(Objects.nonNull(sessao.getNome()));
        assertTrue(Objects.nonNull(sessao.getValidadeMinutos()));
        assertTrue(Objects.nonNull(sessao.getValidadeMinutos()));
    }

    @Test
    void deveLancarFalhaAoNaoEncontrarSessao(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(sessaoRepository.buscarSessao(anyLong())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> sessaoUseCase.buscarSessao(1l));
    }
}
