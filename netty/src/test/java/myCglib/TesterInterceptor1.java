package myCglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by nick on 16/11/10.
 */
public class TesterInterceptor1 implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before invoke1 " + method.getName() + objects.toString());
        methodProxy.invokeSuper(o, objects);
        System.out.println("after invoke1 " + method.getName() + objects.toString());
        return null;
    }
}
