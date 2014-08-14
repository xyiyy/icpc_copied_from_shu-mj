package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), x = in.nextInt();
        int[] ts = new int[n];
        int[] hs = new int[n];
        int[] ms = new int[n];
        for (int i = 0; i < n; i++) {
            ts[i] = in.nextInt();
            hs[i] = in.nextInt();
            ms[i] = in.nextInt();
        }
        boolean[] used = new boolean[n];
        int hi = x;
        for (int i = 0; i < n; i++) {
            int crt = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && ts[j] == i % 2 && hs[j] <= hi && (crt == -1 || ms[j] > ms[crt])) {
                    crt = j;
                }
            }
            if (crt == -1) break;
            used[crt] = true;
            hi += ms[crt];
        }
        int ans = 0;
        for (boolean b : used) if (b) ans++;
        used = new boolean[n];
        hi = x;
        for (int i = 0; i < n; i++) {
            int crt = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && ts[j] != i % 2 && hs[j] <= hi && (crt == -1 || ms[j] > ms[crt])) {
                    crt = j;
                }
            }
            if (crt == -1) break;
            used[crt] = true;
            hi += ms[crt];
        }
        int ans2 = 0;
        for (boolean b : used) if (b) ans2++;
        out.println(Math.max(ans, ans2));
    }
}
