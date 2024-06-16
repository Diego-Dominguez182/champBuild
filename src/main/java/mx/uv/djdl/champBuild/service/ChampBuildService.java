package mx.uv.djdl.champBuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.mapper.ChampBuildMapper;
import mx.uv.djdl.champBuild.model.ChampBuild;
import mx.uv.djdl.champBuild.repository.ChampBuildRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampBuildService {

    @Autowired
    private ChampBuildRepository champBuildRepository;

    private final ChampBuildMapper champBuildMapper = ChampBuildMapper.INSTANCE;

    public List<ChampBuildDTO> getAllBuilds() {
        List<ChampBuild> builds = champBuildRepository.findAll();
        return builds.stream()
                     .map(champBuildMapper::toDTO)
                     .collect(Collectors.toList());
    }

    public List<ChampBuildDTO> getBuildByName(String name) {
        List<ChampBuild> builds = champBuildRepository.findByName(name);
        return builds.stream()
                     .map(champBuildMapper::toDTO)
                     .collect(Collectors.toList());
    }

    public void saveBuild(ChampBuildDTO buildDTO) {
        ChampBuild build = champBuildMapper.toEntity(buildDTO);
        champBuildRepository.save(build);
    }

    public void updateBuild(ChampBuildDTO buildDTO) {
        ChampBuild build = champBuildMapper.toEntity(buildDTO);
        champBuildRepository.update(build);
    }

    public void deleteBuild(ChampBuildDTO buildDTO) {
        ChampBuild build = champBuildMapper.toEntity(buildDTO);
        champBuildRepository.delete(build);
    }
}
