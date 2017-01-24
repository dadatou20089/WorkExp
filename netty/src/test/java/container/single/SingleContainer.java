package container.single;

import container.Container;
import container.containers.DefaultContainer;
import container.containers.entities.ContainerPackages;

/**
 * Created by nick on 16/12/30.
 */
public class SingleContainer {

    private static Container container;

    public static Container getInstance(ContainerPackages packages) {
        if (container == null) {
            synchronized (SingleContainer.class) {
                if (container == null) {
                    container = new DefaultContainer(packages);
                }
            }
        }
        return container;
    }

    public static Container getInstance() {
        if (container == null) {
            throw new RuntimeException("容器未初始化");
        }
        return container;
    }

}
