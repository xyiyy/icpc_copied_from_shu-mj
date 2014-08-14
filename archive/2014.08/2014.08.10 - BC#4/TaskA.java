package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int[] is = in.nextIntArray(6);
        Arrays.sort(is);
        if (is[4] + is[5] > is[3] + is[2] + is[1])
            out.println("Grandpa Shawn is the Winner!");
        else out.println("What a sad story!");
    }
}
