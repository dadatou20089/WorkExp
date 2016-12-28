package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyJson2 {
    @JsonProperty("name")
    private String name2;
    @JsonProperty("description")
    private String description2;

    @Override
    public String toString() {
        return "name2:" + name2 + ", description2: " + description2;
    }
}
