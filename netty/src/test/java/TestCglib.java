import cglib.Tester;
import cglib.TesterCallback;
import cglib.TesterInterceptor;
import cglib.TesterInterceptor1;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class TestCglib {

    public static void main(String[] args) throws Exception {
        testReflect();
    }

    public static void testEnhancer() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tester.class);
        enhancer.setCallback(new TesterInterceptor());

        Tester tester = (Tester) enhancer.create();
        tester.tester("aa");

    }

    public static void testCallBack() {
        Enhancer enhancer = new Enhancer();

        Callback[] callBacks = new Callback[] {
                new TesterInterceptor(),
                new TesterInterceptor1()
        };
        enhancer.setSuperclass(Tester.class);
        enhancer.setCallbacks(callBacks);
        enhancer.setCallbackFilter(new TesterCallback());

        Tester tester = (Tester) enhancer.create();
        tester.tester("aa");
        tester.tester1("aa", "bb");
    }

    public static void testReflect() throws Exception{
        String method = "tester";
        String clazz = "cglib.Tester";
        Class c = Class.forName(clazz);
        System.out.println(c.getName());
        Method[] methods = c.getDeclaredMethods();
        for (Method method1 : methods) {
            System.out.print(method1.getName() + ", ");
            for (Parameter parameter : method1.getParameters()) {
                System.out.print(parameter.getName() + ", 11" + parameter.getType() + " ");
            }
            System.out.println();
        }
    }



}
