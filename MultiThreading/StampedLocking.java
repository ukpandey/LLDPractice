package MultiThreading;

import java.util.concurrent.locks.StampedLock;

class SharedResource {
    private int counter = 10;
    private final StampedLock lock = new StampedLock();

    public void increment() {
        long stamp = lock.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired WRITE lock");
            counter++;
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " updated counter to " + counter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + " released WRITE lock");
        }
    }

    // OPTIMISTIC READ
    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " got optimistic read");
        int value = counter;
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // validate whether write happened meanwhile
        if (lock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + " successfully read value = " + value);
        } else {
            System.out.println(Thread.currentThread().getName() + " optimistic read failed");
            // fallback to real read lock
            stamp = lock.readLock();
            try {
                value = counter;
                System.out.println(Thread.currentThread().getName() + " read value using READ lock = " + value);
            } finally {
                lock.unlockRead(stamp);
            }
        }
    }
}

public class StampedLocking {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread reader = new Thread(resource::optimisticRead);
        Thread writer = new Thread(resource::increment);
        reader.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        writer.start();
    }
}