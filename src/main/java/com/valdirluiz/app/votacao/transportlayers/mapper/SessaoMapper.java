package com.valdirluiz.app.votacao.transportlayers.mapper;

import com.valdirluiz.app.votacao.entities.Sessao;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarSessaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessaoMapper {

    SessaoMapper INSTANCE = Mappers.getMapper(SessaoMapper.class);

    Sessao map(SalvarSessaoDTO dto);
}
