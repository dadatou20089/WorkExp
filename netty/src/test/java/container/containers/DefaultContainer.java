package container.containers;

import com.google.common.base.MoreObjects;
import com.google.common.reflect.ClassPath;
import container.ContainerFactory;
import container.containers.annotationProcessors.ProcessUtil;
import container.containers.annotationProcessors.Processor;
import container.containers.entities.ContainerModule;
import container.containers.entities.ContainerObject;
import container.containers.entities.ContainerPackages;
import container.protocals.NcpProtocolInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.apache.log4j.Logger;
import utils.MyLogger;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nick on 16/12/30.
 */
public class DefaultContainer implements ContainerFactory {
    private static Logger logger = MyLogger.getLogger(DefaultContainer.class);
    private static ContainerModule module;
    private static ContainerPackages packages;

    public DefaultContainer(ContainerPackages packages) {
        this.packages = packages;
        initContainer();
    }

    @Override
    public Object getBean(String beanName) {
        ContainerObject containerObject = module.getContainerObject(beanName);
        return getProxy(containerObject);
    }

    @Override
    public ContainerModule getModule() {
        return module;
    }

    /**
     * 初始化容器
     */
    private void initContainer() {
        module = new ContainerModule();
        initModule();
    }

    /**
     * 初始化模块
     */
    private void initModule() {
        List<String> packageNames = packages.getPackages();
        if (packageNames.size() == 0) {
            throw new RuntimeException("请传入可用包名");
        }


        for (String packageName : packageNames) {
            ClassLoader loader = MoreObjects.firstNonNull(
                    Thread.currentThread().getContextClassLoader(),
                    DefaultContainer.class.getClassLoader()
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
    }

    private void initContainerObject(String className) {
        try {
            Class clazz = Class.forName(className);
            ContainerObject object = new ContainerObject();
            object.setBeanName(className);
            object.setClazz(clazz);
            object.setClassName(className);
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                Processor processor = ProcessUtil.getProcessor(annotation);
                processor.processor(object, annotation);
            }

            logger.info("Init Container Object: " + className);

            module.addObject(object);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getProxy(ContainerObject containerObject) {
        if (containerObject == null) {
            throw new RuntimeException("bean不存在!");
        }

        if (containerObject.getProxy() != null) {
            return containerObject.getProxy();
        }

        synchronized (this) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(containerObject.getClazz());
            enhancer.setCallback(new NcpProtocolInterceptor());
            Object proxy = enhancer.create();
            containerObject.setProxy(proxy);
        }

        return containerObject.getProxy();
    }
}
