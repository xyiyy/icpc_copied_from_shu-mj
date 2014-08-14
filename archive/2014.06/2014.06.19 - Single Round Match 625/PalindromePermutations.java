package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class PalindromePermutations {
    public double palindromeProbability(String word) {
        if (word.length() == 1) return 1;

        int[] cnt = new int[128];
        for (char c : word.toCharArray()) {
            cnt[c]++;
        }
        int o = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            if (cnt[i] % 2 == 1) o++;
        }
        if (o >= 2) return 0.0;
        int len = word.length();
        double[] A = new double[len + 1];
        A[0] = A[1] = 1;
        for (int i = 2; i <= len; i++) A[i] = i * A[i - 1];
        double res = A[len / 2];
        for (int i = 'a'; i <= 'z'; i++) if (cnt[i] > 1) {
            res /= A[cnt[i] / 2];
        }
        res /= A[len];
        for (int i = 'a'; i <= 'z'; i++) if (cnt[i] > 0) {
            res *= A[cnt[i]];
        }
        return res;
    }
}
