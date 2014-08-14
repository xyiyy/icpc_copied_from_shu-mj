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
        int[] ss = is.clone();
        Algo.sort(ss);
        int i = 0;
        while (i < n && is[i] == ss[i]) i++;
        if (i == n) {
            out.println("yes");
            out.println("1 1");
            return ;
        }
        int j = n - 1;
        while (j >= 0 && is[j] == ss[j]) j--;
        Algo.reverse(is, i, j + 1);
        for (int k = i; k <= j; k++) {
            if (is[k] != ss[k]) {
                out.println("no");
                return ;
            }
        }
        out.println("yes");
        out.println((i + 1) + " " + (j + 1));
    }
}
