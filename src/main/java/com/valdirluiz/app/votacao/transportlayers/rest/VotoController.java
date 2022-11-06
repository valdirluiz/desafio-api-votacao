package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.configs.GeneralResponse;
import com.valdirluiz.app.votacao.interactors.VotoUseCase;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarVotoDTO;
import com.valdirluiz.app.votacao.transportlayers.mapper.VotoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Votos"})
@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoUseCase votoUseCase;

    public VotoController(VotoUseCase votoUseCase) {
        this.votoUseCase = votoUseCase;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Serviço responsável por computar um novo voto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Voto computado com sucesso"),
            @ApiResponse(code = 400, message = "Falha de negócio", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Falha inesperada", response = GeneralResponse.class),
    })
    public ResponseEntity<?> salvarVoto(@RequestBody SalvarVotoDTO voto){
        this.votoUseCase.computarVoto(VotoMapper.INSTANCE.map(voto));
        return ResponseEntity.noContent().build();
    }

}
