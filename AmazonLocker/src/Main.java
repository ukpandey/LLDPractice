public class Main {
    public static void main(String[] args) {

        // Create compartments of different sizes
        Compartment[] compartments = new Compartment[] {
                new Compartment(Size.SMALL),
                new Compartment(Size.SMALL),
                new Compartment(Size.MEDIUM),
                new Compartment(Size.LARGE)
        };

        // Create locker system
        Locker locker = new Locker(compartments);

        System.out.println("=== Depositing Packages ===");

        // Deposit a SMALL package
        String smallToken = locker.depositPackage(Size.SMALL);
        System.out.println("Small package deposited.");
        System.out.println("Access Token: " + smallToken);

        // Deposit a MEDIUM package
        String mediumToken = locker.depositPackage(Size.MEDIUM);
        System.out.println("Medium package deposited.");
        System.out.println("Access Token: " + mediumToken);

        // Deposit another SMALL package
        String secondSmallToken = locker.depositPackage(Size.SMALL);
        System.out.println("Second small package deposited.");
        System.out.println("Access Token: " + secondSmallToken);

        System.out.println();

        // Try depositing one more SMALL package (should fail because both SMALL compartments are occupied)
        try {
            locker.depositPackage(Size.SMALL);
        } catch (RuntimeException e) {
            System.out.println("Expected Error: " + e.getMessage());
        }

        System.out.println("\n=== Picking Up Package ===");

        // Pickup using valid token
        locker.pickup(smallToken);
        System.out.println("Package picked up successfully using token: " + smallToken);

        System.out.println();

        // Now one SMALL compartment is free, so deposit should succeed again
        String newSmallToken = locker.depositPackage(Size.SMALL);
        System.out.println("New small package deposited after pickup.");
        System.out.println("Access Token: " + newSmallToken);

        System.out.println("\n=== Invalid Token Test ===");

        // Try invalid token
        try {
            locker.pickup("999999");
        } catch (RuntimeException e) {
            System.out.println("Expected Error: " + e.getMessage());
        }

        System.out.println("\n=== Open Expired Compartments ===");
        // In this implementation, tokens expire after 7 days,
        // so none will be expired during this test.
        locker.openExpiredCompartments();
        System.out.println("Checked for expired compartments.");

        System.out.println("\n=== Test Completed Successfully ===");
    }
}