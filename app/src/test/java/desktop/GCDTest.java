package desktop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import root.gcd.GCD;

public class GCDTest {
    // resource:
    // https://www.geeksforgeeks.org/program-to-find-gcd-or-hcf-of-two-numbers/
    // Recursive function to return gcd of a and b
    private GCD gcdTest;

    int gcd(long a, long b) {
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
            return (int) b;
        }
        return (int) a;
    }

    @BeforeEach
    void setUp() {
        gcdTest = new GCD();
    }

    // 1 положительные значения аргументов
    @ParameterizedTest
    @CsvSource({
            "100, 9",
            "4, 6",
            "3, 5",
            "1, 2",
    })
    @DisplayName("testGCDPositiveValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDPositiveValues(int x, int y) {

        assertEquals(gcd(x, y), gcdTest.gcd(x, y));

    }

    // 2 отрицательное значение первого, второго, обоих аргументов
    @ParameterizedTest
    @CsvSource({
            "-1, 9",
            "-4, 6",
            "3, -5",
            "-1, -2",
    })
    @DisplayName("testGCDNegativeValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDNegativeValues(int x, int y) {
        assertEquals(gcd(x, y), gcdTest.gcd(x, y));
    }

    // 3 нулевое значение первого, второго, обоих аргументов
    @ParameterizedTest
    @CsvSource({
            "0, 9",
            "0, -5",
            "-3, 0",
            "1, 0",
            "0, 0",
    })
    @DisplayName("testGCDZeroValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDZeroValues(int x, int y) {
        assertEquals(gcd(x, y), gcdTest.gcd(x, y));

    }

    // 4 неединичные взаимно простые аргументы (наибольший общий делитель равен 1);
    @ParameterizedTest
    @CsvSource({
            "61, 67",
            "211, 163",
            "173, 241",
            "257, 127",
            "199, 223",
    })
    @DisplayName("testGCDCoprimeValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDCoprimeValues(int x, int y) {

        assertEquals(gcd(x, y), gcdTest.gcd(x, y));

    }

    // 5 равные значения аргументов
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "-100, -100",
            "999, 999",
            "0, 0",
            "-111, -111",
    })
    @DisplayName("testGCDEqualValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDEqualValues(int x, int y) {

        assertEquals(gcd(x, x), gcdTest.gcd(x, x));

    }

    // 6 неравные значения аргументов, при которых первый делит второй, второй делит
    // первый
    @ParameterizedTest
    @CsvSource({
            "33, 561",
            "17, -748",
            "-55, 880",
            "-44, -1452",
            "702, 117",
            "345, -69",
            "-348, 58",
            "-549, -61",

    })
    @DisplayName("testGCDOneDividesOther")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDOneDividesOther(int x, int y) {

        assertEquals(gcd(x, y), gcdTest.gcd(x, y));

    }

    // 7 неравные значения аргументов, дающие неединичный наибольший общий делитель
    @ParameterizedTest
    @CsvSource({
            "41, 246",
            "101, 2828",
            "148, -333",
            "-355, 568",
            "-3817, -2082",
            "3688, 3277",

    })

    @DisplayName("testGCDOneGeneralDivider")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDNonUnitGCD(int x, int y) {
        assertEquals(gcd(x, y), gcdTest.gcd(x, y));
    }

    // 8 граничные значения аргументов
    @ParameterizedTest
    @CsvSource({
            "33, " + Integer.MAX_VALUE,
            "-17," + Integer.MAX_VALUE,
            "55, " + Integer.MIN_VALUE,
            "44, " + Integer.MIN_VALUE,
            "-702, " + Integer.MIN_VALUE,
            "345," + Integer.MAX_VALUE,
            "-348, " + Integer.MIN_VALUE,

    })
    @DisplayName("testGCDLimitValues")
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    void testGCDLimitValues(int x, int y) {
        assertEquals(gcd(x, y), gcdTest.gcd(x, y));
    }

}
