package clientserver;

/**
 * Provides getter for fibonacci number
 */
public class Fibonacci {
    /**
     * Maximum serial number of fibonacci
     */
    private static final int MAX_N = 89;
    /**
     * Containing array of fibonacci numbers
     */
    private static final long[] fibonacci = new long[MAX_N + 1];

    static {
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i <= MAX_N ; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
    }


    /**
     * Supplier for fibonacci number, returns nth fibonacci number. {@code n} must be between 1 and {@link Fibonacci#MAX_N}
     * @param n serial number
     * @return nth clientserver.Fibonacci number
     * @throws IllegalArgumentException if {@code n} out of valid range
     */
    static public long getNth(final int n) throws IllegalArgumentException {
        if (n - 1 > MAX_N || n < 1) {
            throw new IllegalArgumentException(String.format("Number must be between 1 and %d, inserted number is %d.", MAX_N, n));
        }
        return fibonacci[n - 1];
    }


}