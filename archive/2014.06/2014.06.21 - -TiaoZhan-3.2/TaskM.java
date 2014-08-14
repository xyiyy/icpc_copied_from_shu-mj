package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskM {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    class Item implements Comparable<Item> {
        int sum;
        int a;
        int b;

        Item(int a, int b) {
            this.a = a;
            this.b = b;
            sum = a + b;
        }

        @Override
        public int compareTo(Item o) {
            return sum - o.sum;
        }
    }
    private void solve(int n) {
        int[] is = in.nextIntArray(n);
        Item[] its = new Item[n * (n - 1) / 2];
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                its[k++] = new Item(is[i], is[j]);
            }
        }
        Arrays.sort(is);
        Arrays.sort(its);
        Algo.reverse(is);
        int ans = Integer.MIN_VALUE;
        outer: for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = is[i], c = is[j];
                if (d <= ans) break outer;
                int s = Algo.lowerBound(its, new Item(d - c, 0));
                int t = Algo.upperBound(its, new Item(d - c, 0));
                for (int k = s; k < t; k++) {
                    int a = its[k].a, b = its[k].b;
                    if (a != c && a != d && b != c && b != d) {
                        ans = d;
                    }
                }
            }
        }
        if (ans == Integer.MIN_VALUE) out.println("no solution");
        else out.println(ans);
    }
}
