package other.guava.test;

import other.guava.FlowController;

/**
 * Created by nick on 2017/3/12.
 */
public class TestMain {

    public static void main(String[] args) {
        FlowController flowController = new FlowController();

        for (int i = 0; i < 10; i ++) {
            flowController.accquire(i);
        }

        flowController.setRate(10);

        for (int j = 0; j < 100; j ++) {
            flowController.accquire(j);
        }

        flowController.setRate(20);

        for (int j = 0; j < 100; j ++) {
            flowController.accquire(j);
        }
    }

}
