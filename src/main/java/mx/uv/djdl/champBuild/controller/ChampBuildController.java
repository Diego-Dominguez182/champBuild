package mx.uv.djdl.champBuild.controller;

import jakarta.validation.Valid;
import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.service.ChampBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builds")
public class ChampBuildController {

    @Autowired
    private ChampBuildService champBuildService;

    @GetMapping
    public ResponseEntity<List<ChampBuildDTO>> getAllBuilds() {
        List<ChampBuildDTO> builds = champBuildService.getAllBuilds();
        return ResponseEntity.ok(builds);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ChampBuildDTO>> getBuildByName(@PathVariable String name) {
        List<ChampBuildDTO> build = champBuildService.getBuildByName(name);
        if (!build.isEmpty()) {
            return ResponseEntity.ok(build);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createBuild(@Valid @RequestBody ChampBuildDTO buildDTO) {
        try {
            champBuildService.saveBuild(buildDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("La build fue creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> updateBuild(@PathVariable String name, @Valid @RequestBody ChampBuildDTO buildDTO) {
        List<ChampBuildDTO> existingBuilds = champBuildService.getBuildByName(name);
        if (!existingBuilds.isEmpty()) {
            buildDTO.setChampName(name);
            champBuildService.updateBuild(buildDTO);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{name}/{buildTitle}")
    public ResponseEntity<Void> deleteBuild(@PathVariable String name, @PathVariable String buildTitle) {
        List<ChampBuildDTO> existingBuilds = champBuildService.getBuildByName(name);
        if (!existingBuilds.isEmpty()) {
            champBuildService.deleteBuild(buildTitle);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
