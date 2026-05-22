package MultiThreading;

import java.util.Random;

class Dependency {
    private final String name;
    private final int installTime;

    public Dependency(String name, int installTime) {
        this.name = name;
        this.installTime = installTime;
    }

    public void install() {
        System.out.println("Installing dependency: " + name);

        try {
            Thread.sleep(installTime * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(name + " installed successfully in "
                + installTime + " seconds\n");
    }
}

public class DependencyInstaller {

    public static void main(String[] args) {

        Random random = new Random();

        Dependency[] dependencies = {
                new Dependency("MultiThreading.Dependency-1", random.nextInt(5) + 1),
                new Dependency("MultiThreading.Dependency-2", random.nextInt(5) + 1),
                new Dependency("MultiThreading.Dependency-3", random.nextInt(5) + 1),
                new Dependency("MultiThreading.Dependency-4", random.nextInt(5) + 1),
                new Dependency("MultiThreading.Dependency-5", random.nextInt(5) + 1)
        };

        System.out.println("Starting dependency installation...\n");

        for (Dependency dependency : dependencies) {
            dependency.install();
        }

        System.out.println("All dependencies installed successfully.");
    }
}