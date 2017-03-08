package other.thread;

import java.util.LinkedList;

/**
 * 自定义线程池
 */
public class MyThreadPool extends ThreadGroup {
    private LinkedList<Runnable> tasks;

    public MyThreadPool(int size) {
        super("threadId" + "");
        setDaemon(true);
        tasks = new LinkedList<>();
        for (int i = 0; i < size; i ++) {
            new Worker(i).start();
        }
    }

    public synchronized Runnable getTask(int id) {

        System.out.println("Work Thread-" + id + " start work;");
        if (tasks.size() == 0) {
            System.out.println("Work Thread-" + id + " wait for work;");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Work Thread-" + id + " exit work");
                return null;
            }
        }

        return tasks.removeFirst();
    }

    public synchronized void execute(Runnable task) {
        tasks.add(task);
        notify();
    }

    class Worker extends Thread {
        private int id;

        public Worker(int id) {
            super(MyThreadPool.this, id + "");
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("start work thread-" + id);
            while (!isInterrupted()) {
                Runnable task = getTask(id);

                if (task != null) {
                    task.run();
                } else {
                    System.out.println("work thread-" + id + " exit!");
                    return;
                }
            }
            System.out.println("work thread-" + id + " exit!");
        }
    }


}
