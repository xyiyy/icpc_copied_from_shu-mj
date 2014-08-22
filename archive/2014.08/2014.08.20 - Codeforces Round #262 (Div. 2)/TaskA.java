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
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 1; ; i++) {
            if (i % m == 0) n++;
            n--;
            if (n == 0) {
                out.println(i);
                break;
            }
        }
    }
}
