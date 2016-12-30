package container;

import container.containers.entities.ContainerModule;

/**
 * Created by nick on 16/12/30.
 */
public interface ContainerFactory {
    /**
     * 获取bean
     */
    public Object getBean(String beanName);

    /**
     * 获取配置
     */
    public ContainerModule getModule();
}
