package container.containers.annotationProcessors.processors;

import container.annotations.SoaHttpServer;
import container.containers.annotationProcessors.Processor;
import container.containers.entities.ContainerObject;

/**
 * Created by nick on 16/12/30.
 */
public class SoaHttpServerProcessor implements Processor<SoaHttpServer> {
    @Override
    public void process(ContainerObject object, SoaHttpServer annotation) {
        String uri = annotation.uri() + annotation.suffix();
        object.setServerUri(uri);
    }
}
