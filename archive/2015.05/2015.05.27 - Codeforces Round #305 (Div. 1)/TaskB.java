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
        int[] st = new int[n];
        int[] left = new int[n];
        for (int i = 0, top = 0; i < n; i++) {
            while (top > 0 && is[st[top - 1]] >= is[i]) top--;
            left[i] = (top == 0 ? -1 : st[top - 1]);
            st[top++] = i;
        }
        int[] right = new int[n];
        for (int i = n - 1, top = 0; i >= 0; i--) {
            while (top > 0 && is[st[top - 1]] >= is[i]) top--;
            right[i] = (top == 0 ? n : st[top - 1]);
            st[top++] = i;
        }
        int[] max = new int[n];
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 2;
            max[len] = Math.max(max[len], is[i]);
        }
        for (int i = n - 1, m = 0; i >= 0; i--) {
            m = Math.max(m, max[i]);
            max[i] = m;
        }
        for (int i = 0; i < n; i++) {
            out.print(max[i] + " ");
        }
        out.println();
    }
}
