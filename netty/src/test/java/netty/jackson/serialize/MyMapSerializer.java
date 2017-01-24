package netty.jackson.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by nick on 16/12/30.
 */
public class MyMapSerializer extends JsonSerializer<Map> {
    @Override
    public void serialize(Map value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        Set<Map.Entry> set = value.entrySet();
        Iterator<Map.Entry> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            addValue(key, obj, gen);
        }
        gen.writeEndObject();
    }

    private void addValue(String key, Object object, JsonGenerator gen) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        try {
            String value = "";
            if (object instanceof String) {
                value = (String) object;
            } else if (object instanceof Map) {
                module.addSerializer(new MyMapSerializer());
                objectMapper.registerModule(module);
                value = objectMapper.writeValueAsString(object);
            } else {
                value = objectMapper.writeValueAsString(object);
            }

            gen.writeStringField(key, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
