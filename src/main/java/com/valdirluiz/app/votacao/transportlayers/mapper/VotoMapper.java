package com.valdirluiz.app.votacao.transportlayers.mapper;

import com.valdirluiz.app.votacao.entities.Voto;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarVotoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    Voto map(SalvarVotoDTO dto);
}
