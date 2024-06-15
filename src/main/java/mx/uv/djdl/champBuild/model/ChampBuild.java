    package mx.uv.djdl.champBuild.model;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ChampBuild {
    @NotEmpty(message = "El nombre del campeón no puede estar vacío")
    private String champName;

    @NotEmpty(message = "la build no puede estar vacía")
    @Size(min = 6, max = 6, message = "La lista debe tener exactamente 6 elementos")
    private List<String> items;

    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String userName;

    public String getChampName() {
        return champName;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
