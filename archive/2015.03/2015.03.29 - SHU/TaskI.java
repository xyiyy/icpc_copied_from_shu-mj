package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;
    private Stack<PII> ss;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = new int[n + 2];
        for (int i = 0; i < n; i++) {
            is[i + 1] = in.nextInt();
        }
        is[n + 1] = n + 1;
        List<Integer> ls = new ArrayList<>();
        ls.add(0);
        for (int i = 1; i <= n + 1; i++) {

        }
    }
}
