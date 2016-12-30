package container.containers.annotationProcessors.processors;

import container.annotations.SoaHttpServer;
import container.containers.annotationProcessors.Processor;
import container.containers.entities.ContainerObject;

/**
 * Created by nick on 16/12/30.
 */
public class SoaServerProcessor implements Processor<SoaHttpServer> {
    @Override
    public void processor(ContainerObject object, SoaHttpServer annotation) {
        object.setServerUri(annotation.uri());
    }
}
