package container.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by nick on 16/12/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerObject {
    private String beanName;
    private String className;
    private Object proxy;
    private Class<?> clazz;
}
