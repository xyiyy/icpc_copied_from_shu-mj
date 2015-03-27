package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        int[] ts = new int[n];
        for (int i = 0; i < n; i++) {
            String[] ss = in.next().split(":");
            int hh = Integer.parseInt(ss[0]);
            int mm = Integer.parseInt(ss[1]);
            int se = Integer.parseInt(ss[2]);
            ts[i] = hh * 3600 + mm * 60 + se;
        }
        BIT bit = new BIT(25 * 3600);
        int crt = 0;
        int[] id = new int[n];
        boolean f = false;
        for (int i = 0; i < n; i++) {
            int c = bit.sum(0, ts[i] + 1);
            if (c == m - 1) f = true;
            if (c < m) {
                id[i] = crt++;
                bit.add(ts[i], 1);
                bit.add(ts[i] + t, -1);
            } else {
                id[i] = crt - 1;
                bit.add(ts[i - 1] + t, 1);
                bit.add(ts[i] + t, -1);
            }
        }
        if (f) {
            out.println(crt);
            for (int i : id) out.println(i + 1);
        } else {
            out.println("No solution");
        }
    }
}
