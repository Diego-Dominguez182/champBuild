package mx.uv.djdl.champBuild.dto;

import java.util.List;
import lombok.Data;

@Data
public class ChampBuildDTO {
    private String buildTitle;
    private String champName;
    private List<String> items;
    private String userName;
}
