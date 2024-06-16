package mx.uv.djdl.champBuild.mapper;

import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.model.ChampBuild;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChampBuildMapper {
    ChampBuildMapper INSTANCE = Mappers.getMapper(ChampBuildMapper.class);

    ChampBuildDTO toDTO(ChampBuild champBuild);
    ChampBuild toEntity(ChampBuildDTO champBuildDTO);
}
