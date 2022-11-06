package com.valdirluiz.app.votacao.datasources.feign;

import com.valdirluiz.app.votacao.entities.CpfStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${service.validador-cpf.url}", name = "cpf-client")
public interface ValidadorCpfClient {

    @GetMapping("/users/{cpf}")
    CpfStatus consultarStatus(@PathVariable("cpf") String cpf);
}
