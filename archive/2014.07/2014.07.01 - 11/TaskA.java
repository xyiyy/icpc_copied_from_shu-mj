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
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int crt = (k - 1 + m - 1) % n;
        for (int i = 0; i < n - 1; i++) {
            list.remove(crt);
            crt = (crt + m - 1) % list.size();
        }
        out.println(list.get(0) + 1);
    }
}
