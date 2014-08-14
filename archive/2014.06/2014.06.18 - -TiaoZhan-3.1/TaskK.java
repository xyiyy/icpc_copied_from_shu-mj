package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int c = in.nextInt();
        int n = in.nextInt();
        int f = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.nextInt());
        }
        Arrays.sort(is, byF);
        int sum = 0;
        for (int i = 0; i < c; i++) {
            sum += is[i].f;
        }
        if (sum > f) {out.println(-1); return ;}
        int[] ss = new int[n];
        Arrays.sort(is, byS);
        for (int i = 0; i < n; i++) {
            ss[i] = is[i].s;
        }
        Arrays.sort(is, byF);
        int l = c / 2;
        int r = n - c / 2;
        while (l < r) {
            int m = (l + r) / 2;
            int ms = ss[m];
            sum = 0;
            for (int i = 0, low = 0, up = 0; i < n; i++) {
                if (is[i].s < ms && low < c / 2) {
                    sum += is[i].f;
                    low++;
                }
                if (is[i].s >= ms && up <= c / 2) {
                    sum += is[i].f;
                    up++;
                }
            }
            if (sum <= f) l = m + 1;
            else r = m;
        }
        l--;
        out.println(ss[l]);
    }
    class Item {
        int s;
        int f;

        Item(int s, int f) {
            this.s = s;
            this.f = f;
        }
    }
    Comparator<Item> byF = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.f - o2.f;
        }
    };
    Comparator<Item> byS = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.s - o2.s;
        }
    };
}
