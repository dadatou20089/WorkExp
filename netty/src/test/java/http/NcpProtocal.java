package http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by nick on 16/12/29.
 */
public class NcpProtocal {

    public String getNcpRequestBody(Map map) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> args = new HashMap<>();
        NcpRequest request = new NcpRequest();

        Set<Map.Entry> set = map.entrySet();
        try {
            Iterator<Map.Entry> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                String key = mapper.writeValueAsString(entry.getKey());
                String values = mapper.writeValueAsString(entry.getValue());
                args.put(key, values);
            }
            request.setArgs(args);
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class NcpRequest {
        private String ver = "1.0";
        private NcpRequestSoa soa = new NcpRequestSoa();
        private String iface = "me.ele.bpm.bus.policy.core.provider.BizPolicyProvider";
        private String method = "shopRelaterInfos";
        private Map<String, String> args = new HashMap<>();
        private String metas;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class NcpRequestSoa {
        private String rpc = "napos.shop.api.melody|1.14";
        private String req = "napos.shop.api.melody^^8b62b87c-bb11-4dc9-8e76-fcd0a0ee132a|1482486393155";
    }

}
