package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1003 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ip = new int[n];
        for (int i = 0; i < n; i++) {
            String[] s = in.next().split("\\.");
            for (int j = 0; j < 4; j++) {
                ip[i] = (ip[i] << 8) | Integer.parseInt(s[j]);
            }
        }
        while (m-- > 0) {
            String[] s = in.next().split("\\.");
            int mask = 0;
            for (int j = 0; j < 4; j++) {
                mask = (mask << 8) | Integer.parseInt(s[j]);
            }
            Set<Integer> set = new HashSet<>();
            for (int i : ip) {
                set.add(mask & i);
            }
//            Algo.debug(mask, ip, set);
            out.println(set.size());
        }
    }
}
