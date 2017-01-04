package container.containers.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;

/**
 * Created by nick on 16/12/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerObject {
    /**
     * bean名称
     */
    private String beanName;
    /**
     * 类名称
     */
    private String className;
    /**
     * 代理对象
     */
    private Object proxy;
    /**
     * class类型
     */
    private Class<?> clazz;
    /**
     * server信息
     */
    private String serverUri;
    /**
     * 注解信息
     */
    private Annotation serverType;
}
