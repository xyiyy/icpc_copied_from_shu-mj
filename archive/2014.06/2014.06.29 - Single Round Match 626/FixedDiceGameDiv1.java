package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class FixedDiceGameDiv1 {
    public double getExpectation(int a, int b, int c, int d) {
        if (a * b <= c) return -1.0;
        double[] ca = gen(a, b);
        double[] cb = gen(c, d);
        for (int i = 1; i < cb.length; i++) {
            cb[i] += cb[i - 1];
        }
        double p = 0;
        double sum = 0;
        for (int i = a; i <= a * b; i++) {
            p += i * ca[i] * cb[Math.min(i - 1, c * d)];
            sum += ca[i] * cb[Math.min(i - 1, c * d)];
        }
        return p / sum;
    }
    double[] gen(int a, int b) {
        double[][] is = new double[a + 1][a * b + 1];

        for (int i = 1; i <= a; i++) {
            if (i == 1) for (int j = 1; j <= b; j++) is[1][j] = 1;
            else for (int j = i; j <= i * b; j++) {
                for (int k = 1; k <= b; k++) {
                    if (j - k >= i - 1 && j - k <= (i - 1) * b) is[i][j] += is[i - 1][j - k];
                }
            }
        }
        return is[a];
    }
}
