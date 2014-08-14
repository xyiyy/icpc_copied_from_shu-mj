package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            int x = in.nextInt();
            is[i] = new Item(x, v);
        }
        Arrays.sort(is);
        long res = 0;
        BIT cnt = new BIT(20000 + 1);
        BIT xSum = new BIT(20000 + 1);
        for (Item i : is) {
            int c = cnt.sum(0, i.v);
            int s = xSum.sum(0, i.v);
            res += (long) i.v * (i.x * c - s);
            cnt.add(i.v, 1);
            xSum.add(i.v, i.x);
        }
        cnt = new BIT(20000 + 1);
        xSum = new BIT(20000 + 1);
        Algo.reverse(is);
        for (Item i : is) {
            int c = cnt.sum(0, i.v + 1);
            int s = xSum.sum(0, i.v + 1);
            res += (long) i.v * (s - i.x * c);
            cnt.add(i.v, 1);
            xSum.add(i.v, i.x);
        }
        out.println(res);
    }
    class Item implements Comparable<Item> {
        int x;
        int v;

        Item(int x, int v) {
            this.x = x;
            this.v = v;
        }

        @Override
        public int compareTo(Item o) {
            return x - o.x;
        }
    }
}
