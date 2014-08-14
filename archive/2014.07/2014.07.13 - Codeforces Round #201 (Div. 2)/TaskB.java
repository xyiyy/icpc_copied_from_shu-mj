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
        int[] cs = is.clone();
        Algo.sort(cs);
        if (Arrays.equals(is, cs)) {
            out.println(n);
            return ;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) if (is[i] == i) cnt++;
        for (int i = 0; i < n; i++) {
            if (is[i] != i) {
                if (is[is[i]] == i) {
                    out.println(cnt + 2);
                    return ;
                }
            }
        }
        out.println(cnt + 1);
    }
}
