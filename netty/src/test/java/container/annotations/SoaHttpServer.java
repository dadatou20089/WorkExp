package container.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nick on 16/12/30.
 */
@Target({
        ElementType.TYPE,
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface SoaHttpServer {
    String name() default "";
    String uri() default "http://192.168.113.225:8488";
    String suffix() default "/rpc";
}
