package mx.uv.djdl.champBuild.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.model.ChampBuild;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-16T17:02:41-0600",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
public class ChampBuildMapperImpl implements ChampBuildMapper {

    @Override
    public ChampBuildDTO toDTO(ChampBuild champBuild) {
        if ( champBuild == null ) {
            return null;
        }

        ChampBuildDTO champBuildDTO = new ChampBuildDTO();

        champBuildDTO.setBuildTitle( champBuild.getBuildTitle() );
        champBuildDTO.setChampName( champBuild.getChampName() );
        List<String> list = champBuild.getItems();
        if ( list != null ) {
            champBuildDTO.setItems( new ArrayList<String>( list ) );
        }
        champBuildDTO.setUserName( champBuild.getUserName() );

        return champBuildDTO;
    }

    @Override
    public ChampBuild toEntity(ChampBuildDTO champBuildDTO) {
        if ( champBuildDTO == null ) {
            return null;
        }

        ChampBuild champBuild = new ChampBuild();

        champBuild.setBuildTitle( champBuildDTO.getBuildTitle() );
        champBuild.setChampName( champBuildDTO.getChampName() );
        List<String> list = champBuildDTO.getItems();
        if ( list != null ) {
            champBuild.setItems( new ArrayList<String>( list ) );
        }
        champBuild.setUserName( champBuildDTO.getUserName() );

        return champBuild;
    }
}
