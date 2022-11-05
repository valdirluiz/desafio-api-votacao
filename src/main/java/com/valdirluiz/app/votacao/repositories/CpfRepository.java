package com.valdirluiz.app.votacao.repositories;

import com.valdirluiz.app.votacao.entities.CpfStatus;

public interface CpfRepository {

    CpfStatus consultaCpf(String cpf);
}
