package MultiThreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Multiple threads can acquire read locks
 * but write lock is acquired by one thread at a time
 * Works like a reentrant lock for write lock
 * Read lock is acquired by thread: Thread-1
 * Read lock is acquired by thread: Thread-0
 * X: 10
 * X: 10
 * Read lock released by thread: Thread-1
 * Read lock released by thread: Thread-0
 * Write lock acquired by thread: Thread-2
 * After 4 seconds
 * Write lock acquired by thread: Thread-3
 * Write lock released by: Thread-2
 * Write lock released by: Thread-3
 */
class Example{
    int x = 10;
    public void method1(ReadWriteLock lock){
        lock.readLock().lock();
        System.out.println("Read lock is acquired by thread: " + Thread.currentThread().getName());
        System.out.println("X: "+x);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
            System.out.println("Read lock released by thread: " + Thread.currentThread().getName());
        }
    }

    public void method2(ReadWriteLock lock){
        lock.writeLock().lock();
        System.out.println("Write lock acquired by thread: " + Thread.currentThread().getName());
        try {
            x += 10;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
        }
    }
}
public class ReadWriteLocking {
    public static void main(String[] args) {
        Example example = new Example();
        Example example2 = new Example();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Thread t1 = new Thread(()->{
            example.method1(lock);
        });

        Thread t2 = new Thread(()->{
            example.method1(lock);
        });

        Thread t3 = new Thread(()->{
            example.method2(lock);
        });

        Thread t4 = new Thread(()->{
            example.method2(lock);
        });

        Thread t5 = new Thread(()->{
            example2.method2(lock);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
