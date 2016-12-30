package container.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nick on 16/12/30.
 */
@Target({
    ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface SoaServer {
}
