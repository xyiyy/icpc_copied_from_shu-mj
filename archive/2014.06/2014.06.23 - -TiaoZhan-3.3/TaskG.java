package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;
    private int[] p;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int MAX_N = 500000;
        p = new int[MAX_N + 1];
        for (int i = 1; i <= MAX_N; i++) {
            for (int j = i; j <= MAX_N; j += i) {
                p[j]++;
            }
        }
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        String[] names = new String[n];
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = in.next();
            as[i] = in.nextInt();
        }
        int[] jumps = new int[n];
        BIT bit = new BIT(n);
        for (int i = 0; i < n; i++) bit.add(i, 1);
        int realK = k;
        for (int i = 1; i <= n; i++) {
            jumps[k] = i;
            if (i != n) {
                bit.add(k, -1);
                int nextRealK = realK + as[k];
                if (as[k] > 0) nextRealK--;
                nextRealK = (nextRealK % (n - i) + n - i) % (n - i);
                k = bit.get(nextRealK);
                realK = nextRealK;
            }
        }
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (index == -1 || p[jumps[index]] < p[jumps[i]] || p[jumps[index]] == p[jumps[i]] && jumps[i] < jumps[index]) {
                index = i;
            }
        }
        out.println(names[index] + " " + p[jumps[index]]);
    }
}
