package mx.uv.djdl.champBuild.controller;

import mx.uv.djdl.champBuild.dto.ChampBuildDTO;
import mx.uv.djdl.champBuild.service.ChampBuildService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChampBuildControllerTest {

    @Mock
    private ChampBuildService champBuildService;

    @InjectMocks
    private ChampBuildController champBuildController;

    private ChampBuildDTO testBuild;

    @BeforeEach
    public void setUp() {
        testBuild = new ChampBuildDTO();
        testBuild.setBuildTitle("Test Build");
        testBuild.setChampName("Test Champ");
        testBuild.setItems(Arrays.asList("Item1", "Item2", "Item3", "Item4", "Item5", "Item6"));
        testBuild.setUserName("Test User");
    }

    @Test
    public void testGetAllBuilds() {
        List<ChampBuildDTO> builds = Collections.singletonList(testBuild);
        when(champBuildService.getAllBuilds()).thenReturn(builds);

        ResponseEntity<List<ChampBuildDTO>> response = champBuildController.getAllBuilds();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(builds, response.getBody());
    }

    @Test
    public void testGetBuildByName() {
        String name = "Test Champ";
        List<ChampBuildDTO> builds = Collections.singletonList(testBuild);
        when(champBuildService.getBuildByName(name)).thenReturn(builds);

        ResponseEntity<List<ChampBuildDTO>> response = champBuildController.getBuildByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(builds, response.getBody());
    }

    @Test
    public void testGetBuildByName_NotFound() {
        String name = "Nonexistent ";
        when(champBuildService.getBuildByName(name)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ChampBuildDTO>> response = champBuildController.getBuildByName(name);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateBuild() {
        ResponseEntity<String> response = champBuildController.createBuild(testBuild);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("La build fue creada exitosamente", response.getBody());
        verify(champBuildService, times(1)).saveBuild(testBuild);
    }

    @Test
    public void testCreateBuild_InternalServerError() {
        doThrow(RuntimeException.class).when(champBuildService).saveBuild(any());

        ResponseEntity<String> response = champBuildController.createBuild(testBuild);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocurrió un error al procesar la solicitud: null", response.getBody());
    }

    @Test
    public void testUpdateBuild() {
        String name = "Test Champ";
        when(champBuildService.getBuildByName(name)).thenReturn(Collections.singletonList(testBuild));

        ResponseEntity<Void> response = champBuildController.updateBuild(name, testBuild);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(champBuildService, times(1)).updateBuild(testBuild);
    }

    @Test
    public void testUpdateBuild_NotFound() {
        String name = "Nonexistent Champ";
        when(champBuildService.getBuildByName(name)).thenReturn(Collections.emptyList());

        ResponseEntity<Void> response = champBuildController.updateBuild(name, testBuild);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(champBuildService, never()).updateBuild(testBuild);
    }

    @Test
    public void testDeleteBuild() {
        String name = "Test Champ";
        String buildTitle = "Test Build";
        when(champBuildService.getBuildByName(name)).thenReturn(Collections.singletonList(testBuild));

        ResponseEntity<Void> response = champBuildController.deleteBuild(name, buildTitle);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(champBuildService, times(1)).deleteBuild(buildTitle);
    }

    @Test
    public void testDeleteBuild_NotFound() {
        String name = "Nonexistent Champ";
        String buildTitle = "Test Build";
        when(champBuildService.getBuildByName(name)).thenReturn(Collections.emptyList());

        ResponseEntity<Void> response = champBuildController.deleteBuild(name, buildTitle);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(champBuildService, never()).deleteBuild(buildTitle);
    }
}
