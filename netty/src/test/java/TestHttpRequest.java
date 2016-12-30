import container.Container;
import http.HttpInterceptor;
import me.ele.bpm.bus.policy.core.provider.BizPolicyProvider;
import net.sf.cglib.proxy.Enhancer;

import java.util.Arrays;

/**
 * Created by nick on 16/12/29.
 */
public class TestHttpRequest {

    public static void main(String[] args) {
        Container container = new Container("me.ele.bpm.bus.policy.core.provider");
        BizPolicyProvider provider = (BizPolicyProvider) container.getBean("BizPolicyProvider");
        provider.shopRelaterInfos(Arrays.asList(968984));
    }

}
