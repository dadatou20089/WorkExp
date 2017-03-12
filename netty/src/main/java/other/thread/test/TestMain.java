package other.thread.test;

import other.thread.MyThreadPool;

public class TestMain {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(4);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("执行操作!");
            }
        };

        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);
        myThreadPool.execute(runnable);

        myThreadPool.waitFinish();
        myThreadPool.interrupt();
    }

}
