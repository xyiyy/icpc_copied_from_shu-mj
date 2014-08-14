package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        BigInteger a = new BigInteger(in.next(), 8);
        BigInteger b = new BigInteger(in.next(), 8);
        out.println(a.toString(8) + "+" + b.toString(8) + "=" + (a.add(b).toString(8)));
    }
}
