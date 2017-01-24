package netty.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import netty.jackson.entities.MyJson1;

import java.io.IOException;

/**
 * Created by nick on 16/12/20.
 */
public class MySerializy extends JsonSerializer<MyJson1> {
    @Override
    public void serialize(MyJson1 value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("name_name", value.getName1());
        gen.writeStringField("description_description", value.getDescription1());
        gen.writeStringField("number_number", value.getNumber1());
        gen.writeEndObject();
    }
}
