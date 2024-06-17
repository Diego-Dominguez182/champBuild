package mx.uv.djdl.champBuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.repository.ChampBuildRepository;

import java.util.List;

@Service
public class ChampBuildService {

    @Autowired
    private ChampBuildRepository champBuildRepository;


    public List<ChampBuildDTO> getAllBuilds() {
        List<ChampBuildDTO> builds = champBuildRepository.findAll();
        return builds;
    }

    public List<ChampBuildDTO> getBuildByName(String name) {
        List<ChampBuildDTO> builds = champBuildRepository.findByName(name);
        return builds;
    }

    public void saveBuild(ChampBuildDTO buildDTO) {
        champBuildRepository.save(buildDTO);
    }

    public void updateBuild(ChampBuildDTO buildDTO) {
        champBuildRepository.update(buildDTO);
    }

    public void deleteBuild(String buildTitle) {
        champBuildRepository.delete(buildTitle);
    }
}
