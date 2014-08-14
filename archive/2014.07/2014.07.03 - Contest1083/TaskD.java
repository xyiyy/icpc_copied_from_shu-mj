package main;

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
        while (in.hasNext())
            solve();
    }

    void solve() {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) is[i] = in.nextInt() - 1;
        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int crt = i;
                while (!vis[crt]) {
                    vis[crt] = true;
                    crt = is[crt];
                }
                ans++;
            }
        }
        out.println(ans);
    }
}
