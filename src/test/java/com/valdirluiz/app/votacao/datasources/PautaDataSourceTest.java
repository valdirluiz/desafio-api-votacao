package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.PautaJpaRepository;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PautaDataSourceTest {

    @InjectMocks
    private PautaDataSource pautaDataSource;

    @Mock
    private PautaJpaRepository repository;

    @Test
    void deveSalvarPautaComSucesso(){
        var pauta = Factory.getPauta();
        when(repository.saveAndFlush(any())).thenReturn(pauta);
        var pautaSalva = pautaDataSource.salvarPauta(pauta);
        Assertions.assertEquals("testes", pautaSalva.getNome());
        Assertions.assertEquals(1l, pautaSalva.getId());
        Assertions.assertEquals(2l, pautaSalva.getApuracao().getVotosNao());
        Assertions.assertEquals(1l, pautaSalva.getApuracao().getVotosSim());
    }

    @Test
    void deveRecuperarPautaComSucesso(){
        var pauta = Factory.getPauta();
        when(repository.findById(anyLong())).thenReturn(Optional.of(pauta));
        var pautaSalva = pautaDataSource.buscarPauta(1l).get();
        Assertions.assertTrue(Objects.nonNull(pautaSalva));
    }

    @Test
    void deveRecuperarPautasComSucesso(){
        var pautas = Factory.getPautas();
        when(repository.findAll()).thenReturn(pautas);
        var pautasSalvas = pautaDataSource.buscarPautas();
        Assertions.assertTrue(Objects.nonNull(pautasSalvas));
        Assertions.assertFalse(pautasSalvas.isEmpty());
    }
}
