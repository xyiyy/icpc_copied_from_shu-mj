package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        List<Integer> primes = new ArrayList<Integer>();
        Num.primeTable(n, primes);
        int[] phi = Num.phiTable(n);
        long[] preSum = new long[n + 1];
        for (int i = 1; i < phi.length; i++) preSum[i] = preSum[i - 1] + phi[i];
        long ans = 0;
        for (int p : primes) {
            ans += preSum[n / p] * 2 - 1;
        }
        out.println(ans);
    }
}
