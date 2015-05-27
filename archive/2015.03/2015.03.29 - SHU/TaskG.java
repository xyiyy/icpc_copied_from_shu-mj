package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int n = in.nextInt();
        boolean[][][] dp = new boolean[n + 1][11][11];
        int[][][] pre = new int[n + 1][11][11];
        dp[0][0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    if (dp[i][j][k]) {
                        for (int u = 1; u <= 10; u++) if (cs[u - 1] == '1' && u != k && u > j) {
                            dp[i + 1][u - j][u] = true;
                            pre[i + 1][u - j][u] = j * 11 * 11 + k * 11 + u;
                        }
                    }
                }
            }
        }
        boolean res = false;
        int last = 0;
        for (int j = 0; j < 11; j++) {
            for (int k = 0; k < 11; k++) {
                if (dp[n][j][k]) {
                    res = true;
                    last = j * 11 * 11 + k * 11;
                }
            }
        }
        out.println(res ? "YES" : "NO");
        if (res) {
            Stack<Integer> st = new Stack<>();
            for (int i = 0; i < n; i++) {
                int u = last % 11; last /= 11;
                int k = last % 11; last /= 11;
                int j = last % 11;
                last = pre[n - i][j][k];
                st.add(last % 11);
            }
            boolean f = true;
            while (!st.isEmpty()) {
                if (f) f = false; else out.print(" ");
                out.print(st.pop());
            }
            out.println();
        }
    }
}
