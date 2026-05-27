package MultiThreading;

import java.util.concurrent.*;

/**
 * Flow:
 * Step1: First define the size of the pool i.e. how many threads will be there in your pool
 * Step2: Define Core pool size and max pool size and the size of the block queue as well
 * Step3: Lets say core pool size is 2, and max size is 4
 * Step4: Now lets say 4 tasks come.
 * Step 5: Task1 gets thread 1, Task 2 gets thread 2 means we used our core pool size completely
 * Step 6: Task 3 and Task 4 will wait in Blocked queue for their turn
 * Step 7: Thread 1 or Thread 2 completes their work, task 3 and 4 can be picked
 * Step 8: Lets say we had 6 tasks instead of 4
 * Step 9: In that case first tasks will check core pool, if threads in core pool are busy
 * then it will check blocking queue and if that is also full, then our pool size will be increased
 * as per the max pool size we set in step 2. Now pool size will be 4. And tasks can be fulfilled
 * Step 10: If we had 10 tasks instead of 6, in that case there are several ways to reject the tasks
 * First is AbortPolicy: Throws exception when the task is rejected
 * Second CallerRunsPolicy: Try to execute rejected task on parent/ caller thread.
 * DiscardPolicy: Reject the task silently
 * DiscardOldestPolicy: Discard the oldest task i.e. q.front() from the queue
 */

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,4, TimeUnit.HOURS,
//                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardOldestPolicy());
//        new ThreadPoolExecutor.AbortPolicy();
//        new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(2,4,4, TimeUnit.HOURS,
                new ArrayBlockingQueue<>(2), new CustomThreadFactory(),
                new CustomRejectHandler());
        executor2.allowCoreThreadTimeOut(true);
        for(int i=0;i<8;i++){
            executor2.submit(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // some exceptions
                }
                System.out.println("Task processed by: " + Thread.currentThread().getName());
            });
        }
        executor2.shutdown();
    }
}
class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(false);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}

class CustomRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected: " + r.toString());
    }
}
