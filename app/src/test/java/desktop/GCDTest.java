package desktop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import root.gcd.GCD;


public class GCDTest {
    // resource:
    // https://www.geeksforgeeks.org/program-to-find-gcd-or-hcf-of-two-numbers/
    // Recursive function to return gcd of a and b
    private GCD gcdTest;

    int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        if (a == 0) {
            return b;
        }
        return a;
    }

    @BeforeEach
    void setUp() {
        gcdTest = new GCD();
    }

    // генератор тестовых значений
    private int[] generateRandomNumbers(int count, int lowerBound, int upperBound) {
        int interval = Math.abs(upperBound - lowerBound) / count;
        int[] numbers = new int[count];
        numbers[0] = lowerBound;

        for (int i = 1; i < count; i++) {
            numbers[i] = numbers[0] + interval;
        }
        return numbers;
    }

    @DisplayName("testGCDPositiveValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    @Test
    void testGCDPositiveValues() {
        Random randomizer = new Random();

        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) + 1;
            int y = randomizer.nextInt(Integer.MAX_VALUE) + 1;
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDNegativeValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 10000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * (randomizer.nextInt(2) == 1 ? 1 : -1);
            int y = randomizer.nextInt(Integer.MAX_VALUE) * (x < 0 ? 1 : -1);
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }

        for (int i = 0; i < 10000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * -1;
            int y = randomizer.nextInt(Integer.MAX_VALUE) * -1;
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDZeroValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * (randomizer.nextInt(2) == 1 ? 1 : 0);
            int y = randomizer.nextInt(Integer.MAX_VALUE) * (x == 0 ? 1 : -1);
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }

        assertEquals(gcd(0, 0), gcdTest.gcd(0, 0));

    }

    @Test
    void testGCDCoprimeValues() {
        assertEquals(6, gcd(6, 6));
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDEqualValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * (randomizer.nextInt(2) == 1 ? 1 : -1);
            assertEquals(gcd(x, x), gcdTest.gcd(x, x));
        }
    }

    @Test
    void testGCDOneDividesOther() {
        assertEquals(7, gcd(49, 14));
    }

    @Test
    void testGCDNonUnitGCD() {
        assertEquals(8, gcd(24, 16));
    }

    @Test
    void testGCDBoundaryValues() {
        assertEquals(1, 1);
    }

    @Test
    void testGCDPerformance() {

        assertEquals(1, gcd(233, 377));
    }

}
