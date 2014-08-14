package main;

import com.shu_mj.ds.RMQ;
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
        int n = in.nextInt();
        int q = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[] mis = new int[n];
        for (int i = 0; i < n; i++) mis[i] = -is[i];
        RMQ min = new RMQ(is);
        RMQ max = new RMQ(mis);
        while (q-- != 0) {
            int a = in.nextInt() - 1;
            int b = in.nextInt();
            int maxI = max.query(a, b);
            int minI = min.query(a, b);
            out.println(is[maxI] - is[minI]);
        }
    }
}
