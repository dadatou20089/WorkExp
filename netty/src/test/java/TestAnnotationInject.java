import container.annotations.SoaHttpServer;
import me.ele.bpm.bus.policy.core.provider.BizPolicyProvider;

import java.util.Arrays;

/**
 * Created by nick on 17/1/4.
 */
public class TestAnnotationInject {

    @SoaHttpServer
    private BizPolicyProvider bizPolicyProvider;

    public void test() {
        bizPolicyProvider.shopRelaterInfos(Arrays.asList(968984));
    }

}
