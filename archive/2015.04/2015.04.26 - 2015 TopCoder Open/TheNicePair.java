package main;
import java.util.*;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;

public class TheNicePair {

    private ArrayList<Integer> primes;

    public int solve(int[] A) {
        primes = new ArrayList<>();
        Num.primeTable(1000, primes);
        int res = -1;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (fit(Arrays.copyOfRange(A, i, j + 1))) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    private boolean fit(int[] is) {
        for (int i : primes) {
            int c = 0;
            for (int j : is) {
                if (j % i == 0) {
                    c++;
                }
            }
            if (c * 2 >= is.length) return true;
        }
        return false;
    }
}
