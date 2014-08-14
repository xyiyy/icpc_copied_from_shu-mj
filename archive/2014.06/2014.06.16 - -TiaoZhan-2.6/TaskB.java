package main;

import com.shu_mj.collections.map.IncHashMap;
import com.shu_mj.collections.map.IncMap;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private List<Integer> primes;
    private boolean[] isPrime;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        primes = new LinkedList<Integer>();
        isPrime = Num.primeTable(1000000, primes);
        while (in.hasNext()) {
            solve2();
        }
    }

    private void solve2() {
        long a = in.nextLong();
        long b = in.nextLong();
        long c = b / a;
        for (long i = (long) Math.sqrt(c); i >= 1; i--) {
            if (c % i == 0 && Num.gcd(i, c / i) == 1) {
                out.println((a * i) + " " + (b / i));
                return ;
            }
        }
    }

    private void solve() {
        long a = in.nextLong();
        long b = in.nextLong();
        if (a == b) {out.println(a + " " + b); return ;}
        long c = b / a;
        IncMap<Long> factors = new IncHashMap<Long>();
        Num.factorize(factors, c, isPrime, primes);
        int n = factors.size();
        long[] fs = new long[n];
        int tmp = 0;
        for (Map.Entry<Long, Integer> e : factors.entrySet()) {
            fs[tmp++] = Num.pow(e.getKey(), e.getValue());
        }
        long x = 1, y = c;
        for (int mask = 0; mask < (1 << n); mask++) {
            long crtX = 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) crtX *= fs[i];
            }
            long crtY = c / crtX;
            if (crtX + crtY < x + y) {
                x = crtX;
                y = crtY;
            }
        }
        if (x > y) {long t = x; x = y; y = t;}
        out.println((x * a) + " " + (y * a));
    }
}
