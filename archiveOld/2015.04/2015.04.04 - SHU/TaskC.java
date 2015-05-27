package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
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
        int k = in.nextInt();
        int mod = (int) (1e9 + 9);
        int N = 1000000;
        if (n - m < (m + k - 2) / (k - 1) - 1) {
            int ini = m - (n - m) * (k - 1);
            int times = ini / k;
            long res = k * Num.pow(2, times + 1, mod) - 2 * k;
            res %= mod;
            res += m - times * k;
            res %= mod;
            res += mod;
            out.println(res % mod);
        } else {
            out.println(m);
        }
    }
}
