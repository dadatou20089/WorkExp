package other.thread.test;

import other.thread.MyThreadPool;

public class TestMain {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(4);

        myThreadPool.interrupt();



    }

}
