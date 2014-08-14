package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;
    private int[] ps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        List<Integer> primes = new ArrayList<Integer>();
        Num.primeTable(100000, primes);
        ps = Algo.unBox(primes.toArray(new Integer[0]));
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int cnt = 0;
        int i = 0, j = 0, sum = ps[0];
        while (j < ps.length && ps[j] <= n) {
            if (sum == n) {
                cnt++;
                if (i == j) {
                    sum += ps[++j];
                } else {
                    sum -= ps[i++];
                }
            } else if (sum > n) {
                sum -= ps[i++];
            } else if (sum < n) {
                sum += ps[++j];
            }
        }
        out.println(cnt);
    }
}
