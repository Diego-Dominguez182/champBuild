package mx.uv.djdl.champBuild.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uv.djdl.champBuild.model.ChampBuild;
import mx.uv.djdl.champBuild.repository.ChampBuildRepository;

import java.util.List;

@Service
public class ChampBuildService {

    @Autowired
    private ChampBuildRepository champBuildRepository;

    public List<ChampBuild> getAllBuilds() {
        return champBuildRepository.findAll();
    }

    public List<ChampBuild> getBuildByName(String name) {
        return champBuildRepository.findByName(name);
    }

    public void saveBuild(ChampBuild build) {
        champBuildRepository.save(build);
    }

    public void updateBuild(ChampBuild build) {
        champBuildRepository.update(build);
    }

    public void deleteBuild(String name) {
        champBuildRepository.delete(name);
    }
}
