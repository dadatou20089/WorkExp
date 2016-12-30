package me.ele.bpm.bus.policy.core.provider;

import container.annotations.SoaServer;

import java.util.List;

@SoaServer
public interface BizPolicyProvider {
    default Object shopRelaterInfos(List<Integer> rstIdList) {
        return null;
    }
}
