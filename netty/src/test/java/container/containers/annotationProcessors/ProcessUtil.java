package container.containers.annotationProcessors;

import container.annotations.SoaHttpServer;
import container.containers.annotationProcessors.processors.SoaServerProcessor;

import java.lang.annotation.Annotation;

/**
 * Created by nick on 16/12/30.
 */
public class ProcessUtil {

    public static Processor getProcessor(Annotation annotation) {
        if (annotation instanceof SoaHttpServer) {
            return new SoaServerProcessor();
        } else {
            return null;
        }
    }

}
