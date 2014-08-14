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
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int ansH = -1;
        int ansV = -1;
        for (int i = 0; i < n; i++) {
            int l = in.nextInt();
            int w = in.nextInt();
            int h = in.nextInt();
            int v = l * w * h;
            if (ansH == -1 || ansH < h || ansH == h && ansV < v) {
                ansH = h;
                ansV = v;
            }
        }
        out.println(ansV);
    }
}
