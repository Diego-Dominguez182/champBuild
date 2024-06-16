package mx.uv.djdl.champBuild.model;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampBuild {

    @NotEmpty(message = "El titulo de la build no puede estar vacío")
    private String buildTitle;

    @NotEmpty(message = "El nombre del campeón no puede estar vacío")
    private String champName;

    @NotEmpty(message = "la build no puede estar vacía")
    @Size(min = 6, max = 6, message = "La lista debe tener exactamente 6 elementos")
    private List<String> items;

    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String userName;
}
