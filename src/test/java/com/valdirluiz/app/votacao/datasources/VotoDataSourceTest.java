package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.database.VotoJpaRepository;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VotoDataSourceTest {

    @InjectMocks
    private VotoDataSource votoDataSource;

    @Mock
    private VotoJpaRepository repository;

    @Test
    void deveSalvarVotoComSucesso(){
        var voto = Factory.getVoto();
        when(repository.saveAndFlush(any())).thenReturn(voto);
        Assertions.assertDoesNotThrow(()-> votoDataSource.salvarVoto(voto));
    }

    @Test
    void deveRecuperarPorCpfEPauta(){
        var voto = Factory.getVoto();
        when(repository.findByCpfAssociadoAndIdPauta(anyString(), anyLong())).thenReturn(Optional.of(voto));
        Assertions.assertDoesNotThrow(()-> votoDataSource.findByCpfAssociadoAndIdPauta("98798766543", 1l));
    }

    @Test
    void deveContarVotosPorTipoESessao(){
        when(repository.countByIdSessaoAndValor(anyLong(), any())).thenReturn(1l);
        var count = votoDataSource.countByValorAndSessao(true, 1l);
        Assertions.assertEquals(1l, count);
    }

    @Test
    void deveContarVotosPorTipoEPauta(){
        when(repository.countByIdPautaAndValor(anyLong(), any())).thenReturn(1l);
        var count = votoDataSource.countByValorAndPauta(true, 1l);
        Assertions.assertEquals(1l, count);
    }


}
