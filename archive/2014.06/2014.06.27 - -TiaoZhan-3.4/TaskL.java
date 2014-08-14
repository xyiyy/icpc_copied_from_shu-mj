package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
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
        int e = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            int f = in.nextInt();
            int t = in.nextInt();
            int c = in.nextInt();
            is[i] = new Item(f, t, c);
        }
        Arrays.sort(is);
        Intervals<Integer, Integer> Int = new Intervals<Integer, Integer>(-10, 12341234, Integer.MAX_VALUE);
        Int.paint(-5, m, 0);
        for (Item i : is) {
            int c = Int.get(i.f - 1);
            if (c == Integer.MAX_VALUE) {
                out.println(-1);
                return ;
            }
            int b = upperBound(Int, i.f, i.t + 1, c + i.c);
            if (b != i.t + 1) Int.paint(b, i.t + 1, c + i.c);
        }
        out.println(Int.get(e));
    }

    private int upperBound(Intervals<Integer, Integer> Int, int b, int e, int x) {
        while (b < e) {
            int m = (b + e) / 2;
            if (Int.get(m) > x) e = m;
            else b = m + 1;
        }
        return b;
    }

    class Item implements Comparable<Item> {
        int f;
        int t;
        int c;

        @Override
        public int compareTo(Item o) {
            return f - o.f;
        }

        Item(int f, int t, int c) {
            this.f = f;
            this.t = t;
            this.c = c;
        }
    }
}
