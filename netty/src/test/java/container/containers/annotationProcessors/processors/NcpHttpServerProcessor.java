package container.containers.annotationProcessors.processors;

import container.annotations.NcpHttpServer;
import container.containers.annotationProcessors.Processor;
import container.containers.entities.ContainerObject;

/**
 * Created by nick on 17/1/4.
 */
public class NcpHttpServerProcessor implements Processor<NcpHttpServer>{
    @Override
    public void process(ContainerObject object, NcpHttpServer annotation) {
        String uri = annotation.uri() + annotation.suffix();
        object.setServerUri(uri);
        if (annotation.name() != null && !annotation.name().isEmpty()) {
            object.setBeanName(annotation.name());
        }
    }
}
