package main;

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
        int n = in.nextInt();
        for (int i = 0; i < n; i++) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] dis = new int[n + 10];
        int[] us = new int[n];
        int[] vs = new int[n];
        for (int i = 0; i < n; i++) {
            us[i] = in.nextInt();
            vs[i] = in.nextInt();
        }
        dis[0] = 1;
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < n; j++) {
                dis[vs[j]] = dis[us[j]] + 1;
            }
        }
        out.println(Algo.max(dis) + " " + (n + 1));
    }
}
