package http;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import netty.jackson.serialize.MyMapSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nick on 16/12/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoaBody {
    private String ver = "1.0";
    private Soa soa = new Soa();
    private String iface;
    private String method;

    @JsonSerialize(using = MyMapSerializer.class)
    private Map<String, Object> args = new HashMap<>();
    private Metas metas = null;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Soa {
        private String rpc = "napos.shop.api.melody|1.14";
        private String req = "napos.shop.api.melody^^8b62b87c-bb11-4dc9-8e76-fcd0a0ee132a|1482486393155";
    }

    @Data
    public class Metas {

    }
}


