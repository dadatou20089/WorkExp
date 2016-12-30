package myCglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by nick on 16/12/28.
 */
public class TesterCallback implements CallbackFilter{
    @Override
    public int accept(Method method) {
        if (method.getName().equals("tester")) {
            System.out.println("Tester Call back teser!");
            return 0;
        } else {
            System.out.println("Tester Call Back tester1!");
            return 1;
        }
    }
}
