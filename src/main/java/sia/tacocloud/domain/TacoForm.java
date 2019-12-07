package sia.tacocloud.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TacoForm {
    private Long id;
    private String name;
    private Date createdAt;
    private List<String> ingredients;
}
