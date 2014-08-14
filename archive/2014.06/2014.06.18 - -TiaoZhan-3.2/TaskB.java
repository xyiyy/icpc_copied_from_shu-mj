package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.ds.BooleanBIT;
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
        boolean[] bs = new boolean[n];
        for (int i = 0; i < n; i++) {
            bs[i] = in.next().charAt(0) == 'F';
        }
        int minM = 12341234;
        int minK = 0;
        for (int i = 1; i <= n; i++) {
            int step = calc(bs, i);
            if (step < minM) {
                minM = step;
                minK = i;
            }
        }
        out.println(minK + " " + minM);
    }

    private int calc(boolean[] bs, int s) {

        int n = bs.length;
        int res = 0;
        BooleanBIT bit = new BooleanBIT(n + 1);
        for (int i = 0; i < n; i++) {
            if (!(bit.sum(0, i + 1) ^ bs[i])) {
                res++;
                bit.add(i, true);
                if (i + s > n) return 12341234;
                bit.add(i + s, true);
            }
        }
        return res;
    }
}
