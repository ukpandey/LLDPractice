package MultiThreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Demonstrates CAS (Compare And Swap) operations
 * using Atomic classes in Java.
 *
 * Atomic classes provide lock-free thread-safe operations.
 *
 * Classes covered:
 * 1. AtomicInteger
 * 2. AtomicBoolean
 * 3. AtomicLong
 * 4. AtomicReference
 */
public class CASOperationsExample {
    public static void main(String[] args) {
        atomicIntegerExample();
        atomicBooleanExample();
        atomicLongExample();
        atomicReferenceExample();
    }

    // ============================================================
    // ATOMIC INTEGER
    // ============================================================

    /**
     * Demonstrates CAS using AtomicInteger.
     *
     * compareAndSet(expectedValue, newValue)
     *
     * If current value equals expectedValue,
     * update it atomically to newValue.
     */
    public static void atomicIntegerExample() {

        System.out.println("\n===== AtomicInteger Example =====");
        AtomicInteger counter = new AtomicInteger(10);
        System.out.println("Initial Value: " + counter.get());
        boolean success = counter.compareAndSet(10, 20);
        System.out.println("CAS Success: " + success);
        System.out.println("Updated Value: " + counter.get());
        // This will fail because current value is now 20
        boolean failed = counter.compareAndSet(10, 30);
        System.out.println("Second CAS Success: " + failed);
        System.out.println("Final Value: " + counter.get());
    }

    // ============================================================
    // ATOMIC BOOLEAN
    // ============================================================

    /**
     * Demonstrates CAS using AtomicBoolean.
     * Useful for:
     * - service started flag
     * - one-time initialization
     * - feature toggles
     */
    public static void atomicBooleanExample() {
        System.out.println("\n===== AtomicBoolean Example =====");
        AtomicBoolean isStarted = new AtomicBoolean(false);
        boolean success = isStarted.compareAndSet(false, true);
        System.out.println("Service Started: " + success);
        System.out.println("Current State: " + isStarted.get());
        // This will fail because value is already true
        boolean failed = isStarted.compareAndSet(false, true);
        System.out.println("Second Start Attempt: " + failed);
    }

    // ============================================================
    // ATOMIC LONG
    // ============================================================

    /**
     * Demonstrates AtomicLong operations.
     * Useful for:
     * - balances
     * - counters
     * - metrics
     * - sequence generators
     */
    public static void atomicLongExample() {
        System.out.println("\n===== AtomicLong Example =====");
        AtomicLong balance = new AtomicLong(1000);
        System.out.println("Initial Balance: " + balance.get());
        balance.addAndGet(500);
        System.out.println("Updated Balance: " + balance.get());
        boolean success = balance.compareAndSet(1500, 2000);
        System.out.println("CAS Success: " + success);
        System.out.println("Final Balance: " + balance.get());
    }

    // ============================================================
    // ATOMIC REFERENCE
    // ============================================================

    /**
     * Simple User class.
     */
    static class User {
        String name;
        User(String name) {
            this.name = name;
        }
    }

    /**
     * Demonstrates CAS using AtomicReference.
     * AtomicReference is used when we want to atomically
     * update object references.
     */
    public static void atomicReferenceExample() {
        System.out.println("\n===== AtomicReference Example =====");
        User oldUser = new User("Alice");
        AtomicReference<User> userReference = new AtomicReference<>(oldUser);
        System.out.println("Current User: " + userReference.get().name);
        User newUser = new User("Bob");
        boolean success = userReference.compareAndSet(oldUser, newUser);
        System.out.println("CAS Success: " + success);
        System.out.println("Updated User: " + userReference.get().name);
        // This will fail because oldUser reference changed
        boolean failed = userReference.compareAndSet(oldUser, new User("Charlie"));
        System.out.println("Second CAS Success: " + failed);
        System.out.println("Final User: " + userReference.get().name);
    }
}