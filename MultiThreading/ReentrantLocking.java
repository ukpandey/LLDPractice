package MultiThreading;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Here T3 can start immediately because there is no synchronized
 * T1 will start and acquire the lock on method 1
 * T2 will start and will wait for T1 to release the lock
 * So Reentrant Lock doesn't depend on object unlike monitor locking
 * So if you want to acquire a lock, and you don't want other objects
 * to access it then go for reentrant lock
 * It is recommended practice to always immediately follow a call to lock with a try block,
 * most typically in a before/ after construction such as
 * {@snippet :
 *     public void m() {
 *         lock.lock(); // block until condition holds
 *         try {
 *             // ... method body
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 * }
 */
class SharedResources3{

    public void method1(ReentrantLock lock)  {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": method1") ;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }
    public void method2(){
        System.out.println(Thread.currentThread().getName() + ": method2");
    }
}
public class ReentrantLocking {
    public static void main(String[] args) {
        SharedResources3 resources3 = new SharedResources3();
        SharedResources3 resources4 = new SharedResources3();
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(()->{
            resources3.method1(lock);
        });
        Thread t3 = new Thread(resources3::method2);
        Thread t2 = new Thread(()->{
            resources4.method1(lock);
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
