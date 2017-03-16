package other.thread;

import sun.awt.Mutex;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nick on 2017/3/15.
 */
public class ThreadSync {

    /**
     * 型号量
     */
    private Semaphore semaphore;

    /**
     * 锁机制
     */
    private Lock lock;

    /**
     * 阻塞队列,用于生产者-消费者
     */
    private BlockingQueue<Integer> blockingQueue;

    public void initAll() {
        initSemaphore();
        initLock();
        initBlockingQueue();
    }

    private void initBlockingQueue() {
        blockingQueue = new LinkedBlockingQueue<>(20);
    }

    private void initSemaphore() {
        semaphore = new Semaphore(10);
    }

    private void initLock() {
        lock = new ReentrantLock();
    }

    public void operaLock() {
        new Thread(lockRun()).start();
    }

    public void operaQueue() {
        new Thread(queueRun(1)).start();
        new Thread(queueRun(2)).start();
    }

    private Runnable queueRun(final int i) {
        return new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                if (i == 1) {
                    for (int j = 0; j < 100; j ++) {
                        try {
                            int value = random.nextInt();
                            blockingQueue.put(value);
                            System.out.println("产生商品:" + value);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    while (true) {
                        try {
                            int value = blockingQueue.take();
                            Thread.sleep(1000);
                            System.out.println("消费商品:" + value);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    private Runnable lockRun() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("start task...");
                lock.lock();
                try {
                    System.out.println("lock and start......");
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                } finally {
                    lock.unlock();
                    System.out.println("release lock and end...");
                }
            }
        };
    }


    public void operaSemaphore(int i) throws InterruptedException {
        new Thread(run()).start();

    }

    private Runnable run() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(1);
                    System.out.println(semaphore);

                } catch (InterruptedException e) {

                } finally {
                    semaphore.release();
                }

            }
        };
    }

    public static void main(String[] args) throws Exception {
        ThreadSync threadSync = new ThreadSync();
        threadSync.initAll();

//        for (int i = 0; i < 100; i ++) {
//            threadSync.operaLock();
//        }

        threadSync.operaQueue();
    }

}
