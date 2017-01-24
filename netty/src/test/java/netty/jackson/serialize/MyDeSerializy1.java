package netty.jackson.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import netty.jackson.entities.MyJson1;

import java.io.IOException;

/**
 * Created by nick on 16/12/20.
 */
public class MyDeSerializy1 extends JsonDeserializer<MyJson1> {

    @Override
    public MyJson1 deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        String name = node.get("name1").asText();
        String description = node.get("description1").asText();
        String number = node.get("number1").asText();

        MyJson1 myJson1 = new MyJson1();
        myJson1.setName1(name);
        myJson1.setDescription1(description);
        myJson1.setNumber1(number);

        return myJson1;
    }
}
