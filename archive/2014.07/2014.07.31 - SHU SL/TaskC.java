package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        Num.primeTable(100000, new ArrayList<Integer>());
        while (t-- != 0)
            solve();
    }
    private void solve() {
        BigInteger n = BigInteger.valueOf(in.nextLong());
        if (Num.isPrime(n)) out.println("Prime");
        else {
            TreeMap<BigInteger, Integer> factors = new TreeMap<BigInteger, Integer>();
            Num.factorize(n, factors);
            out.println(factors.firstKey());
        }
    }
}
