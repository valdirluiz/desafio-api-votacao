package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.interactors.SessaoUseCase;
import com.valdirluiz.app.votacao.utils.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
@Import(SessaoController.class)
class SessaoControllerTest {

    @MockBean
    private SessaoUseCase sessaoUseCase;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void deveSalvarSessaoComSucesso() throws Exception {
        when(sessaoUseCase.salvarSessao(any())).thenReturn(Factory.getSessao());
        this.mockMvc.perform(
                        post("/sessoes")
                                .content(Factory.getSessaoAsJson())
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarSessoes() throws Exception {
        when(sessaoUseCase.buscarSessoes()).thenReturn(Factory.getSessoes());
        this.mockMvc.perform(
                        get("/sessoes").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarSessao() throws Exception {
        when(sessaoUseCase.buscarSessao(anyLong())).thenReturn(Factory.getSessao());
        this.mockMvc.perform(
                        get("/sessoes/{id}", 1l)
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
