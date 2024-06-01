package mx.uv.djdl.champBuild;

import jakarta.validation.Valid;
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
    public ResponseEntity<List<ChampBuild>> getAllBuilds() {
        List<ChampBuild> builds = champBuildService.getAllBuilds();
        return ResponseEntity.ok(builds);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ChampBuild>> getBuildByName(@PathVariable String name) {
        List<ChampBuild> build = champBuildService.getBuildByName(name);
        if (build != null) {
            return ResponseEntity.ok(build);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createBuild(@Valid @RequestBody ChampBuild build) {
        try {
            champBuildService.saveBuild(build);
            return ResponseEntity.status(HttpStatus.CREATED).body("La build fue creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud: " + e.getMessage());
        }
    }


    @PutMapping("/{name}")
    public ResponseEntity<Void> updateBuild(@PathVariable String name, @Valid @RequestBody ChampBuild build) {
        if (champBuildService.getBuildByName(name) != null) {
            build.setChampName(name);
            champBuildService.updateBuild(build);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteBuild(@PathVariable String name) {
        if (champBuildService.getBuildByName(name) != null) {
            champBuildService.deleteBuild(name);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
