package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.configs.GeneralResponse;
import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarSessaoDTO;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarVotoDTO;
import com.valdirluiz.app.votacao.transportlayers.mapper.SessaoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Votos"})
@RestController
@RequestMapping("/votos")
public class VotoController {

    @PostMapping
    @ApiOperation(value = "Serviço responsável por computar um novo voto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Voto computado com sucesso"),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<?> salvarSessao(@RequestBody SalvarVotoDTO voto){
        //var response = this.sessaoUseCase.salvarSessao(SessaoMapper.INSTANCE.map(sessao));
        return ResponseEntity.noContent().build();
    }

}
