package com.valdirluiz.app.votacao.datasources;

import com.valdirluiz.app.votacao.datasources.feign.ValidadorCpfClient;
import com.valdirluiz.app.votacao.entities.CpfStatus;
import com.valdirluiz.app.votacao.repositories.CpfRepository;
import org.springframework.stereotype.Service;

@Service
public class CpfDataSource implements CpfRepository {

    private final ValidadorCpfClient validadorCpfClient;

    public CpfDataSource(ValidadorCpfClient validadorCpfClient) {
        this.validadorCpfClient = validadorCpfClient;
    }

    @Override
    public CpfStatus consultaCpf(String cpf) {
        return validadorCpfClient.consultarStatus(cpf);
    }
}
