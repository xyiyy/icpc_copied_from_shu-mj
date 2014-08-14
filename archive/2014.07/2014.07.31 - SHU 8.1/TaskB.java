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
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i : is) pq.add(i);
        long res = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            res += a + b;
            pq.add(a + b);
        }
        out.println(res);
    }
}
