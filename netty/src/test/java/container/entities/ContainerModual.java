package container.entities;

import java.util.List;
import java.util.Vector;

/**
 * Created by nick on 16/12/30.
 */
public class ContainerModual {
    private List<ContainerObject> objects = new Vector<>();

    public void addObject(ContainerObject object) {
        this.objects.add(object);
    }

    public ContainerObject getContainerObject(String beanName) {
        if (beanName == null || beanName.equals("")) {
            return null;
        }

        for (ContainerObject object : objects) {
            if (object.getBeanName().contains(beanName)) {
                return object;
            }
        }

        return null;
    }
}
