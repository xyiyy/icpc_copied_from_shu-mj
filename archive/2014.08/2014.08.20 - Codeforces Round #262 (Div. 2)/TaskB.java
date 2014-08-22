package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 1; i <= 81; i++) {
            BigInteger r = B(b).multiply(B(i).pow(a)).add(B(c));
            if (r.compareTo(B(0)) > 0 && r.compareTo(B((int) 1e9)) < 0 && sum(r.intValue()) == i) ans.add(r.intValue());
        }
        Collections.sort(ans);
        out.println(ans.size());
        for (int i : ans) {
            out.print(i + " ");
        }
        out.println();
    }

    private int sum(int x) {
        int r = 0;
        for (char c : Integer.toString(x).toCharArray()) r += c - '0';
        return r;
    }

    BigInteger B(long a) {
        return BigInteger.valueOf(a);
    }
}
