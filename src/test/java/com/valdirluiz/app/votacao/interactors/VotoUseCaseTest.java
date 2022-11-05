package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.CpfRepository;
import com.valdirluiz.app.votacao.repositories.SessaoRepository;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VotoUseCaseTest {

    @InjectMocks
    private VotoUseCase votoUseCase;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private CpfRepository cpfRepository;
    @Mock
    private VotoRepository votoRepository;

    @Test
    void deveComputarVotoComSucesso(){
        var voto = Factory.getVoto();
        var sessao = Factory.getSessao();
        when(sessaoRepository.buscarSessao(any())).thenReturn(Optional.of(sessao));
        when(cpfRepository.consultaCpf(anyString())).thenReturn(Factory.statusCpf());
        doNothing().when(votoRepository).salvarVoto(any());
        assertDoesNotThrow(()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoInformarCpfInvalido(){
        var voto = Factory.getVoto();
        var sessao = Factory.getSessao();
        when(sessaoRepository.buscarSessao(any())).thenReturn(Optional.of(sessao));
        when(cpfRepository.consultaCpf(anyString())).thenThrow(RuntimeException.class);
        var erro =
                assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
        assertEquals("CPF inválido", erro.getMessage());
    }

    @Test
    void deveDarFalhaComStatusCpfInvalido(){
        var voto = Factory.getVoto();
        var sessao = Factory.getSessao();
        var statusCpf = Factory.statusCpf();
        statusCpf.setStatus("UNABLE_TO_VOTE");
        when(sessaoRepository.buscarSessao(any())).thenReturn(Optional.of(sessao));
        when(cpfRepository.consultaCpf(anyString())).thenReturn(statusCpf);
        var erro =
                assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
        assertEquals("CPF não habilitado para votar", erro.getMessage());
    }

    @Test
    void deveDarFalhaAoNaoEncontrarSessao(){
        var voto = Factory.getVoto();
        when(sessaoRepository.buscarSessao(any())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoInformarCpfNulo(){
        var voto = Factory.getVoto();
        voto.setCpfAssociado(null);
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoInformarCpfVazio(){
        var voto = Factory.getVoto();
        voto.setCpfAssociado("");
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoInformarSessaoNula(){
        var voto = Factory.getVoto();
        voto.setIdSessao(null);
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoNaoInformarValor(){
        var voto = Factory.getVoto();
        voto.setValor(null);
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }

    @Test
    void deveDarFalhaAoVotarComSessaoExpirada(){
        var voto = Factory.getVoto();
        var sessao = Factory.getSessao();
        sessao.setDataValidade(LocalDateTime.now().minusDays(1));
        when(sessaoRepository.buscarSessao(any())).thenReturn(Optional.of(sessao));
        assertThrows(BadRequestException.class, ()-> votoUseCase.computarVoto(voto));
    }
}
