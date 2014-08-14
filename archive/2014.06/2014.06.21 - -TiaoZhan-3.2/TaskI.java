package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int[] is = in.nextIntArray(20);
        int ans = work(is.clone());
        Algo.reverse(is);
        ans = Math.min(ans, work(is.clone()));
        out.println(ans);
    }

    private int work(int[] is) {
        int cnt = 0;
        for (int i = 0; i < 19; i++) {
            if (is[i] == 1) {
                fill(is, i + 1);
                cnt++;
            }
        }
        if (is[19] == 0) return cnt;
        return 20;
    }

    private void fill(int[] is, int i) {
        if (i > 0) is[i - 1] ^= 1;
        if (i < 19) is[i + 1] ^= 1;
        is[i] ^= 1;
    }
}
