package container.containers.annotationProcessors;

import container.annotations.NcpHttpServer;
import container.annotations.SoaHttpServer;
import container.containers.annotationProcessors.processors.NcpHttpServerProcessor;
import container.containers.annotationProcessors.processors.SoaHttpServerProcessor;

import java.lang.annotation.Annotation;

/**
 * Created by nick on 16/12/30.
 */
public class ProcessUtil {

    public static Processor getProcessor(Annotation annotation) {
        if (annotation instanceof SoaHttpServer) {
            return new SoaHttpServerProcessor();
        } else if (annotation instanceof NcpHttpServer) {
            return new NcpHttpServerProcessor();
        } else {
            return null;
        }
    }

}
