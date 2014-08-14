package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            if (cs != 1) out.println();
            out.println("Case " + cs++ + ":");
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        BIT bit = new BIT(n + 11);
        int ans = 0;
        for (int j = 0; j < n; j++) {
            int i = is[j];
            ans += bit.sum(i + 1, n + 10);
            bit.add(i, 1);
        }
        out.println(ans);
    }
}
