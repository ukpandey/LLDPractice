package MultiThreading;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentInstaller {

    private static final Random random = new Random();

    static class Dependency {
        String name;
        List<Dependency> dependencies = new ArrayList<>();

        Dependency(String name) {
            this.name = name;
        }

        void dependsOn(Dependency dependency) {
            dependencies.add(dependency);
        }
    }

    public static void main(String[] args) {

        // Create dependencies
        Dependency A = new Dependency("A");
        Dependency B = new Dependency("B");
        Dependency C = new Dependency("C");
        Dependency D = new Dependency("D");
        Dependency E = new Dependency("E");

        // Define dependency graph
        B.dependsOn(A);
        C.dependsOn(A);
        D.dependsOn(A);
        E.dependsOn(D);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Map<String, CompletableFuture<Void>> installedMap = new ConcurrentHashMap<>();

        // Start installation
        install(A, executor, installedMap);
        install(B, executor, installedMap);
        install(C, executor, installedMap);
        install(D, executor, installedMap);
        install(E, executor, installedMap);

        // Wait for all installations
        CompletableFuture.allOf(
                installedMap.values().toArray(new CompletableFuture[0])
        ).join();

        executor.shutdown();

        System.out.println("\nAll dependencies installed successfully.");
    }

    private static CompletableFuture<Void> install(
            Dependency dependency,
            ExecutorService executor,
            Map<String, CompletableFuture<Void>> installedMap) {

        // Avoid duplicate installation
        if (installedMap.containsKey(dependency.name)) {
            return installedMap.get(dependency.name);
        }

        List<CompletableFuture<Void>> dependencyFutures = new ArrayList<>();

        // Install parent dependencies first
        for (Dependency parent : dependency.dependencies) {
            dependencyFutures.add(
                    install(parent, executor, installedMap)
            );
        }

        CompletableFuture<Void> future =
                CompletableFuture.allOf(
                        dependencyFutures.toArray(new CompletableFuture[0])
                ).thenRunAsync(() -> {

                    int installTime = random.nextInt(5) + 1;

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " Installing " + dependency.name
                    );

                    try {
                        Thread.sleep(installTime * 1000L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " Installed " + dependency.name
                                    + " in " + installTime + " seconds"
                    );

                }, executor);

        installedMap.put(dependency.name, future);

        return future;
    }
}