package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.configs.GeneralResponse;
import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.interactors.PautaUseCase;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarPautaDTO;
import com.valdirluiz.app.votacao.transportlayers.mapper.PautaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Pautas"})
@RestController
@RequestMapping("/pautas")
public class PautaController {

    private final PautaUseCase pautaUseCase;

    public PautaController(PautaUseCase pautaUseCase) {
        this.pautaUseCase = pautaUseCase;
    }

    @PostMapping
    @ApiOperation(value = "Serviço responsável por salvar uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pauta salvada", response = Pauta.class),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<Pauta> salvarPauta(@RequestBody SalvarPautaDTO pauta){
        var response = this.pautaUseCase.salvarPauta(PautaMapper.INSTANCE.map(pauta));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Serviço responsável por recuperar todas as pautas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todas as pautas", response = Pauta.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<List<Pauta>> buscarPautas(){
        var response = this.pautaUseCase.buscarPautas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Serviço responsável por recuperar uma pauta específica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a pauta solicitada", response = Pauta.class),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<Pauta> buscarPauta(@PathVariable Long id){
        var response = this.pautaUseCase.buscarPauta(id);
        return ResponseEntity.ok(response);
    }
}
