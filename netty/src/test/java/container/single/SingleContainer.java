package container.single;

import container.ContainerFactory;
import container.containers.DefaultContainer;
import container.containers.entities.ContainerPackages;

/**
 * Created by nick on 16/12/30.
 */
public class SingleContainer {

    private static ContainerFactory container;

    public static ContainerFactory getInstance(ContainerPackages packages) {
        if (container == null) {
            synchronized (SingleContainer.class) {
                if (container == null) {
                    container = new DefaultContainer(packages);
                }
            }
        }
        return container;
    }

    public static ContainerFactory getInstance() {
        if (container == null) {
            throw new RuntimeException("容器未初始化");
        }
        return container;
    }

}
