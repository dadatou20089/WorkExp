package netty.utils;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by nick on 16/12/20.
 */
public class MyMutibleJson {

    private ObjectMapper mapper;

    public MyMutibleJson() {
        mapper = new ObjectMapper();
    }

    public MyMutibleJson(SimpleModule module) {
        this();
        mapper.registerModule(module);
    }

    public void setDesrilize(JsonDeserializer desrilize) {
        mapper.findAndRegisterModules();
        mapper = new ObjectMapper();
        SerializationConfig config = mapper.getSerializationConfig();
    }

}
