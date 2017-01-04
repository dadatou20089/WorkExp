package container.containers.annotationProcessors;

import container.containers.entities.ContainerObject;

import java.lang.annotation.Annotation;

/**
 * Created by nick on 16/12/30.
 */
public interface Processor<T extends Annotation> {
    public void process(ContainerObject object, T annotation);
}
