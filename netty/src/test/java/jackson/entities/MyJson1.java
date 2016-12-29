package jackson.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jackson.serialize.MySerializy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = MySerializy.class)
public class MyJson1 {
    @JsonProperty("name")
    private String name1;
    @JsonProperty("description")
    private String description1;
    @JsonProperty("number")
    private String number1;
    @JsonIgnore
    private String number2;

    @Override
    public String toString() {
        return "name1:" + name1 + ", description1: " + description1 +
                ", number1:" + number1 + ", number2: " + number2;
    }
}
