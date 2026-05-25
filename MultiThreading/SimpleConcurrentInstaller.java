package MultiThreading;

class InstallTask implements Runnable {

    private final String dependencyName;
    private final int installTime;

    public InstallTask(String dependencyName, int installTime) {
        this.dependencyName = dependencyName;
        this.installTime = installTime;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()
                + " Installing " + dependencyName);

        try {
            Thread.sleep(installTime * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()
                + " Finished " + dependencyName);
    }
}

public class SimpleConcurrentInstaller {

    public static void main(String[] args) throws Exception {

        // Install A first
        Thread A = new Thread(new InstallTask("A", 3));

        A.start();

        // Wait until A completes
        A.join();

        // Now B,C,D can run in parallel
        Thread B = new Thread(new InstallTask("B", 2));
        Thread C = new Thread(new InstallTask("C", 5));
        Thread D = new Thread(new InstallTask("D", 4));

        B.start();
        C.start();
        D.start();

        // Wait for D because E depends on D
        D.join();

        // Install E after D
        Thread E = new Thread(new InstallTask("E", 2));

        E.start();

        // Wait for all remaining threads
        B.join();
        C.join();
        E.join();

        System.out.println("\nAll dependencies installed.");
    }
}