package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.feign.ValidadorCpfClient;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.valdirluiz.app.votacao.utils.Factory.statusCpf;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CpfDataSourceTest {

    @InjectMocks
    private CpfDataSource cpfDataSource;

    @Mock
    private ValidadorCpfClient validadorCpfClient;

    @Test
    void deveRecuperarStatusComSucesso(){
        when(validadorCpfClient.consultarStatus(anyString())).thenReturn(statusCpf());
        var statusCpf = cpfDataSource.consultaCpf("07607867877");
        Assertions.assertEquals("ABLE_TO_VOTE", statusCpf.getStatus());
    }


}
