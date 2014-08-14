package test.on2014_08.on2014_08_03_SHU_8_6.TaskD;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int h = in.nextInt();
            int w = in.nextInt();
            if (h == 0 && w == 0) break;
            solve(h, w);
        }
    }

    private void solve(int h, int w) {
        int[][] dp = new int[2][1 << w];
        dp[1][(1 << w) - 1] = 1;
        for (int i = 0; i <= h; i++) {
            Arrays.fill(dp[i & 1], 0);
            for (int j = 0; j < (1 << w); j++) {
                for (int k = 0; k < (1 << w); k++) {
                    if (fit(j, k, w)) dp[i & 1][j] += dp[(i - 1) & 1][k];
                }
            }
        }
        out.println(dp[h & 1][0]);
    }

    private boolean fit(int a, int b, int w) {
        if ((a & b) != 0) return false;
        int ab = a | b;
        for (int i = 0; i < w; ) {
            if ((ab & (1 << i)) != 0) i++;
            else if (i < w - 1 && (ab & (1 << (i + 1))) == 0) i += 2;
            else return false;
        }
        return true;
    }
}
