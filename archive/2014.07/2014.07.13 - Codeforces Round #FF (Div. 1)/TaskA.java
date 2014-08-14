package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) left[i] = 0;
            else {
                if (is[i] > is[i - 1]) left[i] = left[i - 1];
                else left[i] = i;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) right[i] = n - 1;
            else {
                if (is[i] < is[i + 1]) right[i] = right[i + 1];
                else right[i] = i;
            }
        }
        int ans = 1;
        if (n == 1) {
            out.println(1);
            return ;
        }
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans = Math.max(ans, right[i + 1] + 1);
            } else if (i == n - 1) {
                ans = Math.max(ans, n - left[i - 1]);
            } else {
                if (is[i - 1] + 1 < is[i + 1]) {
                    ans = Math.max(ans, right[i + 1] - left[i - 1] + 1);
                } else {
                    ans = Math.max(ans, Math.max(i - left[i - 1] + 1, right[i + 1] - i + 1));
                }
            }
        }
        out.println(ans);
    }
}
