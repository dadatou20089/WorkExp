package me.ele.bpm.bus.policy.core.provider;

import container.annotations.SoaHttpServer;

import java.util.List;

@SoaHttpServer
public interface BizPolicyProvider {
    default Object shopRelaterInfos(List<Integer> rstIdList) {
        return null;
    }
    void ping();
}
