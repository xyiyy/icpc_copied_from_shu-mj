package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task1009TestCase {
    @TestCase
    public Collection<Test> createTests() {
        Set<Test> set = new HashSet<Test>();
        for (int i = 0; i < 0; i++) {
            set.add(run());
        }
        return set;
    }

    private Test run() {
        StringBuilder sb = new StringBuilder();
        sb.append("1" + System.lineSeparator());
        int n = 3;
        sb.append(n + "" + System.lineSeparator());
        int[] is = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) is[i] = r.nextInt(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) sb.append(is[i] + " " );
        sb.append(System.lineSeparator());
        long[][] dp = new long[n][n];
        long[][] gs = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) gs[i][j] = is[i];
                else gs[i][j] = Num.gcd(gs[i][j - 1], is[j]);
            }
        }
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l - 1;
                if (j == n) break;
                if (l == 1) dp[i][j] = 0;
                else {
                    for (int k = i; k + 1 <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + gs[i][j]);
                    }
                }
            }
        }
        return new Test(sb.toString(), "" + (dp[0][n - 1] + Algo.sum(is)));
    }
}
