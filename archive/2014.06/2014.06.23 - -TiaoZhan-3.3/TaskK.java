package main;

import com.shu_mj.ds.BIT;
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
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(is);
        BIT bit = new BIT(50000 + 2);
        for (Item i : is) {
            int cnt = bit.sum(i.b, i.e + 1);
            if (cnt >= i.c) continue;
            cnt = i.c - cnt;
            for (int j = i.e; cnt > 0; j--) {
                if (bit.sum(j, j + 1) == 0) {
                    cnt--;
                    bit.add(j, 1);
                }
            }
        }
        out.println(bit.sum(0, 50000 + 2));
    }
    class Item implements Comparable<Item> {
        int b;
        int e;
        int c;

        Item(int b, int e, int c) {
            this.b = b;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Item o) {
            if (e != o.e) return e - o.e;
            return o.b - b;
        }
    }
}
