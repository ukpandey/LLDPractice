package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NumberPrinter implements Runnable {

    private static int number = 1;
    private static final int MAX = 10;

    private final int remainder;

    public NumberPrinter(int remainder) {
        this.remainder = remainder;
    }

    @Override
    public void run() {

        while (true) {

            synchronized (NumberPrinter.class) {

                if (number > MAX) {
                    break;
                }

                if (number % 3 == remainder) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " -> " + number
                    );

                    number++;

                    NumberPrinter.class.notifyAll();
                } else {

                    try {
                        NumberPrinter.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class PrintNumbers {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new NumberPrinter(1)); // prints 1,4,7,10
        executor.submit(new NumberPrinter(2)); // prints 2,5,8
        executor.submit(new NumberPrinter(0)); // prints 3,6,9

        executor.shutdown();
    }
}