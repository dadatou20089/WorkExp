package serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import entity.MyJson1;
import utils.MyJson;

import java.io.IOException;

/**
 * Created by nick on 16/12/20.
 */
public class MyDeSerializy extends JsonDeserializer<MyJson1> {

    @Override
    public MyJson1 deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        String name = node.get("name_name").asText();
        String description = node.get("description_description").asText();
        String number = node.get("number_number").asText();

        MyJson1 myJson1 = new MyJson1();
        myJson1.setName1(name);
        myJson1.setDescription1(description);
        myJson1.setNumber1(number);

        return myJson1;
    }
}
