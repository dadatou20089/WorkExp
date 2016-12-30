package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nick on 16/12/29.
 */
public class NcpProtocal {
    public String getNcpRequestObjectBody(Object object, String iface, String method) {
        Map<String, Object> args = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                args.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return getNcpRequestBody(args, iface, method);
    }

    public String getNcpRequestBody(Map map, String iface, String method) {
        ObjectMapper mapper = new ObjectMapper();
        SoaBody body = new SoaBody();

        body.setIface(iface);
        body.setMethod(method);
        body.setArgs(map);

        try {
            return mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
