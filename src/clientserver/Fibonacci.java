package clientserver;

import java.math.BigInteger;

/**
 * Provides getter for fibonacci number
 */
public class Fibonacci {
    /**
     * Maximum serial number of fibonacci
     */
    private static final int MAX_N = 100_000;
    /**
     * Containing array of fibonacci numbers
     */
    private static final BigInteger[] fibonacci = new BigInteger[MAX_N + 1];

    static {
        fibonacci[0] = BigInteger.valueOf(1);
        fibonacci[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= MAX_N ; i++) {
            fibonacci[i] = fibonacci[i - 1].add(fibonacci[i - 2]);
        }
    }


    /**
     * Supplier for fibonacci number, returns nth fibonacci number. {@code n} must be between 1 and {@link Fibonacci#MAX_N}
     * @param n serial number
     * @return nth clientserver.Fibonacci number
     * @throws IllegalArgumentException if {@code n} out of valid range
     */
    static public BigInteger getNth(final int n) throws IllegalArgumentException {
        if (n - 1 > MAX_N || n < 1) {
            throw new IllegalArgumentException(String.format("Number must be between 1 and %d, inserted number is %d.", MAX_N, n));
        }
        return fibonacci[n - 1];
    }


}