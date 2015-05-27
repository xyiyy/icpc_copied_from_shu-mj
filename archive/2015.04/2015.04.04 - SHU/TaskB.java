package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        int max = 0;
        for (int i = 1; i < 31; i++) {
            int sum = (1 << i) - 1;
            for (int j = 0; j < n; j++) {
                if ((is[j] & (1 << i)) != 0) {
                    sum &= is[j];
                }
            }
            if (sum == 0) {
                max = i;
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if ((is[i] & (1 << max)) != 0) {
                cnt++;
            }
        }
        out.println(cnt);
        for (int i = 0; i < n; i++) {
            if ((is[i] & (1 << max)) != 0) {
                out.print(is[i] + " ");
            }
        }
        out.println();
    }
}
