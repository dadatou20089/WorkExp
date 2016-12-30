
import container.ContainerFactory;
import container.containers.entities.ContainerPackages;
import container.single.SingleContainer;
import me.ele.bpm.bus.policy.core.provider.BizPolicyProvider;

import java.util.Arrays;


/**
 * Created by nick on 16/12/29.
 */
public class TestHttpRequest {

    public static void main(String[] args) {

        ContainerPackages packages = new ContainerPackages();
        packages.addPackage("me.ele.bpm.bus.policy.core.provider");
        ContainerFactory container = SingleContainer.getInstance(packages);

        BizPolicyProvider provider = (BizPolicyProvider) container.getBean("BizPolicyProvider");
        provider.shopRelaterInfos(Arrays.asList(968984));
        provider.ping();
    }

}
