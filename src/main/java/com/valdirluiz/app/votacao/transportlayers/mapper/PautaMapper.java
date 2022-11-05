package com.valdirluiz.app.votacao.transportlayers.mapper;

import com.valdirluiz.app.votacao.entities.Pauta;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarPautaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta map(SalvarPautaDTO dto);
}
