package me.ele.bpm.bus.policy.core.provider;

import container.annotations.SoaHttpServer;

@SoaHttpServer
public interface BizPollingCators {
    public void ping();
    public void pong();
}
