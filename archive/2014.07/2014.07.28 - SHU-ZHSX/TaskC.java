package main;

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
        long[] ls = new long[5842];
        int u = 0;
        long MAX = 2000000000;
        for (long i = 1; i <= MAX; i *= 2) {
            for (long j = 1; i * j <= MAX; j *= 3) {
                for (long k = 1; i * j * k <= MAX; k *= 5) {
                    for (long l = 1; i * j * k * l <= MAX; l *= 7) {
                        ls[u++] = i * j * k * l;
                    }
                }
            }
        }
        Arrays.sort(ls);
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("The %d%s humble number is %d.%n",
                    n,
                    !(n % 100 >= 11 && n % 100 <= 13) && n % 10 >= 1 && n % 10 <= 3
                            ? new String[] {"th", "st", "nd", "rd"}[n % 10]
                            : "th",
                    ls[n - 1]);
        }
    }
}
