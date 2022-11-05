package com.valdirluiz.app.votacao.interactors;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import com.valdirluiz.app.votacao.repositories.PautaRepository;
import com.valdirluiz.app.votacao.repositories.VotoRepository;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PautaUseCaseTest {

    @InjectMocks
    private PautaUseCase pautaUseCase;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private VotoRepository votoRepository;

    @Test
    void deveCadastrarPautaComSucesso(){
        var pauta = Factory.getPauta();
        when(pautaRepository.salvarPauta(any())).thenReturn(pauta);
        assertDoesNotThrow(()-> pautaUseCase.salvarPauta(pauta));
    }

    @Test
    void deveDarFalhaAoSalvarPautaComNomeNulo(){
        var pauta = Factory.getPauta();
        pauta.setNome(null);
        var error =
                assertThrows(BadRequestException.class, ()-> pautaUseCase.salvarPauta(pauta));
        assertEquals("É obrigatório informar o campo 'nome'", error.getMessage());
    }

    @Test
    void deveDarFalhaAoSalvarPautaComNomeVazio(){
        var pauta = Factory.getPauta();
        pauta.setNome("");
        var error =
                assertThrows(BadRequestException.class, ()-> pautaUseCase.salvarPauta(pauta));
        assertEquals("É obrigatório informar o campo 'nome'", error.getMessage());
    }

    @Test
    void deveRecuperarPautas(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(pautaRepository.buscarPautas()).thenReturn(Factory.getPautas());
        var pautas  = pautaUseCase.buscarPautas();
        assertFalse(pautas.isEmpty());
    }

    @Test
    void deveRecuperarPauta(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(pautaRepository.buscarPauta(anyLong())).thenReturn(Optional.of(Factory.getPauta()));
        var pauta  = pautaUseCase.buscarPauta(1l);
        assertTrue(Objects.nonNull(pauta));
        assertTrue(Objects.nonNull(pauta.getApuracao()));
        assertTrue(Objects.nonNull(pauta.getNome()));
    }

    @Test
    void deveLancarFalhaAoNaoEncontrarPauta(){
        when(votoRepository.countByValorAndPauta(any(), anyLong())).thenReturn(1l);
        when(pautaRepository.buscarPauta(anyLong())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, ()-> pautaUseCase.buscarPauta(1l));
    }
}
