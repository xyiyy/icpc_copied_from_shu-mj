package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            for (int j = 10; j > 0; j--) {
                if (x * x + y * y <= (11 - j) * 20 * (11 - j) * 20) {
                    sum += j;
                    break;
                }
            }
        }
        out.println(sum);
    }
}
