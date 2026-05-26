package MultiThreading;

import java.util.concurrent.Semaphore;

class DatabaseConnection {
    private final Semaphore semaphore = new Semaphore(3);
    public void accessDatabase() {
        try {
            System.out.println(Thread.currentThread().getName() + " waiting for DB connection");
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " acquired DB connection");
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName() + " releasing DB connection");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        for (int i = 1; i <= 6; i++) {
            Thread t = new Thread(db::accessDatabase);
            t.start();
        }
    }
}