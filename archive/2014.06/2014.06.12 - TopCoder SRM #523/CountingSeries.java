package main;

public class CountingSeries {
    public long countThem(long a, long b, long c, long d, long upperBound) {
        long sum1 = upperBound >= a ? (upperBound - a) / b + 1 : 0;
        if (c > upperBound) return sum1;
        if (d == 1) {
            if (c >= a && c <= upperBound && (c - a) % b == 0 || c > upperBound)
                return sum1;
            return sum1 + 1;
        }
        for (long i = c; i <= upperBound; i *= d) {
            if (i >= a && (i - a) % b == 0) {

            } else {
                sum1++;
            }
        }
        return sum1;
    }
}
