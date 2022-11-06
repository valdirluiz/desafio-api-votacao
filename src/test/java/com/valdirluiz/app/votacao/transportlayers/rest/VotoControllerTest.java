package com.valdirluiz.app.votacao.transportlayers.rest;

import com.valdirluiz.app.votacao.interactors.VotoUseCase;
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
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.mock;

@WebMvcTest(VotoController.class)
@Import(VotoController.class)
class VotoControllerTest {

    @MockBean
    private VotoUseCase votoUseCase;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveSalvarVotoComSucesso() throws Exception {
        doNothing().when(votoUseCase).computarVoto(any());
        this.mockMvc.perform(
                        post("/votos")
                                .content(Factory.getVotoAsJson())
                                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }
}
