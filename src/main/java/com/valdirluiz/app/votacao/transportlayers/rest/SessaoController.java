package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.configs.GeneralResponse;
import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.interactors.SessaoUseCase;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarSessaoDTO;
import com.valdirluiz.app.votacao.transportlayers.mapper.PautaMapper;
import com.valdirluiz.app.votacao.transportlayers.mapper.SessaoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Sessões"})
@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    private final SessaoUseCase sessaoUseCase;

    public SessaoController(SessaoUseCase sessaoUseCase) {
        this.sessaoUseCase = sessaoUseCase;
    }

    @PostMapping
    @ApiOperation(value = "Serviço responsável por salvar uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pauta salvada", response = Pauta.class),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<Sessao> salvarSessao(@RequestBody SalvarSessaoDTO sessao){
        var response = this.sessaoUseCase.salvarSessao(SessaoMapper.INSTANCE.map(sessao));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Serviço responsável por recuperar todas as sessões")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todas as sessões", response = Pauta.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<List<Sessao>> buscarSessoes(){
        var response = this.sessaoUseCase.buscarSessoes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Serviço responsável por recuperar uma sessão específica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a sessão solicitada", response = Pauta.class),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<Sessao> buscarSessao(@PathVariable Long id){
        var response = this.sessaoUseCase.buscarSessao(id);
        return ResponseEntity.ok(response);
    }
}
