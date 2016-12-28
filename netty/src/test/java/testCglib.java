import cglib.Tester;
import cglib.TesterCallback;
import cglib.TesterInterceptor;
import cglib.TesterInterceptor1;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class testCglib {

    public static void main(String[] args) {
        testCallBack();
    }

    public static void testEnhancer() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tester.class);
        enhancer.setCallback(new TesterInterceptor());

        Tester tester = (Tester) enhancer.create();
        tester.tester();
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
        tester.tester();
        tester.tester1();
    }

}
