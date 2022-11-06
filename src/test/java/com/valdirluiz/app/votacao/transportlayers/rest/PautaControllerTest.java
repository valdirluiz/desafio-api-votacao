package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.interactors.PautaUseCase;
import com.valdirluiz.app.votacao.interactors.SessaoUseCase;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
@Import(PautaController.class)
class PautaControllerTest {

    @MockBean
    private PautaUseCase pautaUseCase;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveSalvarPautaComSucesso() throws Exception {
        when(pautaUseCase.salvarPauta(any())).thenReturn(Factory.getPauta());
        this.mockMvc.perform(
                        post("/pautas")
                                .content(Factory.getPautaAsJson())
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPautas() throws Exception {
        when(pautaUseCase.buscarPautas()).thenReturn(Factory.getPautas());
        this.mockMvc.perform(
                        get("/pautas").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarPauta() throws Exception {
        when(pautaUseCase.buscarPauta(anyLong())).thenReturn(Factory.getPauta());
        this.mockMvc.perform(
                        get("/pautas/{id}", 1l)
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
