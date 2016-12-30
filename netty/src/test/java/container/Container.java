package container;

import com.google.common.base.MoreObjects;
import com.google.common.reflect.ClassPath;
import container.entities.ContainerModual;
import container.entities.ContainerObject;
import net.sf.cglib.proxy.Enhancer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nick on 16/12/30.
 */
public class Container {

    private ContainerModual modual;
    private String packageName = "";


    public Container(String packageName) {
        this.packageName = packageName;
        initContainer();
    }

    public Container() {
        this.packageName = "me.ele.bpm.bus.policy.core.provider";
        initContainer();
    }

    /**
     * 初始化容器
     */
    private void initContainer() {
        modual = new ContainerModual();
        if (isNotEmpty(packageName)) {
            initModual();
        }
    }

    /**
     * 初始化模块
     */
    private void initModual() {
        ClassLoader loader = MoreObjects.firstNonNull(
                Thread.currentThread().getContextClassLoader(),
                Container.class.getClassLoader()
        );

        ClassPath classPath;

        try {
            classPath = ClassPath.from(loader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //获取底层类列表
        List<String> classNames = classPath.getTopLevelClasses(packageName)
                .stream().map((ci) -> (ci.getName())).collect(Collectors.toList());
        for (String className : classNames) {
            initContainerObject(className);
        }
    }

    private void initContainerObject(String className) {
        try {
            Class clazz = Class.forName(className);
            ContainerObject object = new ContainerObject();
            object.setBeanName(className);
            object.setClazz(clazz);
            modual.addObject(object);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String beanName) {
        ContainerObject containerObject = modual.getContainerObject(beanName);

        if (containerObject == null) {
            throw new RuntimeException("bean不存在!");
        }

        if (containerObject.getProxy() != null) {
            return containerObject.getProxy();
        } else {
            return getProxy(containerObject);
        }
    }

    private synchronized Object getProxy(ContainerObject containerObject) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(containerObject.getClazz());
        enhancer.setCallback(new container.proxy.HttpInterceptor());
        Object proxy = enhancer.create();
        containerObject.setProxy(proxy);
        return containerObject.getProxy();
    }


    private boolean isNotEmpty(String str) {
        if (str == null || str.replace(" ", "").length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Container container = new Container("me.ele.bpm.bus.policy.core.provider");
    }
}
