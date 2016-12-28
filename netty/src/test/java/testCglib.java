import net.sf.cglib.proxy.Enhancer;

public class testCglib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tester.class);
        enhancer.setCallback(new TesterInterceptor());

        Tester tester = (Tester) enhancer.create();
        tester.tester();
    }

}
