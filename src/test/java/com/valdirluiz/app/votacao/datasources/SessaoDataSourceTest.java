package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.SessaoJpaRepository;
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
class SessaoDataSourceTest {

    @InjectMocks
    private SessaoDataSource sessaoDataSource;

    @Mock
    private SessaoJpaRepository repository;

    @Test
    void deveSalvarSessaoComSucesso(){
        var sessao = Factory.getSessao();
        when(repository.saveAndFlush(any())).thenReturn(sessao);
        var pautaSalva = sessaoDataSource.salvarSessao(sessao);
        Assertions.assertEquals("teste", pautaSalva.getNome());
        Assertions.assertEquals(1l, pautaSalva.getId());
        Assertions.assertEquals(2l, pautaSalva.getApuracao().getVotosNao());
        Assertions.assertEquals(1l, pautaSalva.getApuracao().getVotosSim());
    }

    @Test
    void deveRecuperarSessaoComSucesso(){
        var sessao = Factory.getSessao();
        when(repository.findById(anyLong())).thenReturn(Optional.of(sessao));
        var pautaSalva = sessaoDataSource.buscarSessao(1l).get();
        Assertions.assertTrue(Objects.nonNull(pautaSalva));
    }

    @Test
    void deveRecuperarSessoesComSucesso(){
        var sessoes = Factory.getSessoes();
        when(repository.findAll()).thenReturn(sessoes);
        var pautasSalvas = sessaoDataSource.buscarSessoes();
        Assertions.assertTrue(Objects.nonNull(pautasSalvas));
        Assertions.assertFalse(pautasSalvas.isEmpty());
    }
}
