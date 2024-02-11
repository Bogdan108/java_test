package desktop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
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

    // 1 положительные значения аргументов
    @Test
    @DisplayName("testGCDPositiveValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDPositiveValues() {
        Random randomizer = new Random();

        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) + 1;
            int y = randomizer.nextInt(Integer.MAX_VALUE) + 1;
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    // 2 отрицательное значение первого, второго, обоих аргументов
    @Test
    @DisplayName("testGCDNegativeValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDNegativeValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 10000000; ++i) {
            int x = (randomizer.nextInt(Integer.MAX_VALUE) + 1) * (randomizer.nextInt(2) == 1 ? 1 : -1);
            int y = (randomizer.nextInt(Integer.MAX_VALUE) + 1) * (x < 0 ? 1 : -1);
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }

        for (int i = 0; i < 10000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * -1;
            int y = randomizer.nextInt(Integer.MAX_VALUE) * -1;
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    // 3 нулевое значение первого, второго, обоих аргументов
    @Test
    @DisplayName("testGCDZeroValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDZeroValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * (randomizer.nextInt(2) == 1 ? 1 : 0);
            int y = randomizer.nextInt(Integer.MAX_VALUE) * (x == 0 ? 1 : 0);
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }

        assertEquals(gcd(0, 0), gcdTest.gcd(0, 0));

    }

    // 4 неединичные взаимно простые аргументы (наибольший общий делитель равен 1);
    @Test
    @DisplayName("testGCDCoprimeValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDCoprimeValues() {
        int[] simpleNumbers = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127,
                131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
                193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
                263, 269, 271 };

        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int x = simpleNumbers[randomizer.nextInt(simpleNumbers.length)];
            int y = simpleNumbers[randomizer.nextInt(simpleNumbers.length)];
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    // 5 равные значения аргументов
    @Test
    @DisplayName("testGCDEqualValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDEqualValues() {
        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int x = randomizer.nextInt(Integer.MAX_VALUE) * (randomizer.nextInt(2) == 1 ? 1 : -1);
            assertEquals(gcd(x, x), gcdTest.gcd(x, x));
        }
    }

    // 6 неравные значения аргументов, при которых первый делит второй, второй делит
    // первый
    @Test
    @DisplayName("testGCDOneDividesOther")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDOneDividesOther() {
        Random randomizer = new Random();
        for (int i = 0; i < 10000; ++i) {
            int divider = randomizer.nextInt(Integer.MAX_VALUE);
            int x = randomizer.nextInt(Integer.MAX_VALUE) * randomizer.nextInt(Integer.MAX_VALUE);
            if (x == divider) {
                continue;
            }
            assertEquals(gcd(divider, x), gcdTest.gcd(divider, x));
        }

        for (int i = 0; i < 10000; ++i) {
            int divider = randomizer.nextInt(Integer.MAX_VALUE);
            int x = randomizer.nextInt(Integer.MAX_VALUE) * randomizer.nextInt(Integer.MAX_VALUE);
            if (x == divider) {
                continue;
            }
            assertEquals(gcd(x, divider), gcdTest.gcd(x, divider));
        }
    }

    // 7 неравные значения аргументов, дающие неединичный наибольший общий делитель
    @Test
    @DisplayName("testGCDOneGeneralDivider")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDNonUnitGCD() {
        int[] simpleNumbers = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127,
                131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
                193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
                263, 269, 271 };

        Random randomizer = new Random();
        for (int i = 0; i < 20000000; ++i) {
            int general = simpleNumbers[randomizer.nextInt(simpleNumbers.length)];
            int x = general * randomizer.nextInt(Integer.MAX_VALUE) > Integer.MAX_VALUE ? Integer.MAX_VALUE
                    : general * randomizer.nextInt(Integer.MAX_VALUE);
            int y = general * randomizer.nextInt(Integer.MAX_VALUE) > Integer.MAX_VALUE ? Integer.MAX_VALUE
                    : general * randomizer.nextInt(Integer.MAX_VALUE);
            assertEquals(gcd(x, y), gcdTest.gcd(x, y));
        }
    }

    // 8 граничные значения аргументов
    @Test
    @DisplayName("testGCDLimitValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDLimitValues() {
        Random randomizer = new Random();
        int x, y;
        x = randomizer.nextInt() * (randomizer.nextInt(2) == 1 ? 1 : -1);
        y = randomizer.nextInt(2) == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        assertEquals(gcd(x, y), gcdTest.gcd(x, y));
    }

}
