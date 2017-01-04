package container.protocals.ncp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import container.protocals.ProtocolInterceptor;
import container.protocals.ncp.entities.SoaBody;

import java.util.Map;

/**
 * Created by nick on 16/12/30.
 */
public class NcpProtocolInterceptor extends ProtocolInterceptor {

    @Override
    public String getParameters(Map<String, Object> params, String className, String methodName) {
        ObjectMapper mapper = new ObjectMapper();
        SoaBody body = new SoaBody();

        body.setIface(className);
        body.setMethod(methodName);
        body.setArgs(params);

        try {
            return mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
