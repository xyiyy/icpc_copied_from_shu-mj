package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.nextInt());
        }
        double l = 0, r = 1e8;
        for (int i = 0; i < 75; i++) {
            double m = (l + r) / 2;
            if (fit(is, n, k, m)) l = m;
            else r = m;
        }
        Jtem[] js = new Jtem[n];
        for (int i = 0; i < n; i++) {
            js[i] = new Jtem(is[i].x - is[i].y * l, i);
        }
        Arrays.sort(js, new Comparator<Jtem>() {
            @Override
            public int compare(Jtem o1, Jtem o2) {
                return Double.compare(o2.d, o1.d);
            }
        });
        for (int i = 0; i < k - 1; i++) {
            out.print((js[i].id + 1) + " ");
        }
        out.println(js[k - 1].id + 1);
    }

    private boolean fit(Item[] is, int n, int k, double m) {
        double[] ds = new double[n];
        for (int i = 0; i < n; i++) {
            ds[i] = is[i].x - is[i].y * m;
        }
        Arrays.sort(ds);
        double sum = 0;
        while (k > 0) {
            sum += ds[n - k];
            k--;
        }
        return sum >= 0;
    }

    class Jtem {
        double d;
        int id;

        Jtem(double d, int id) {
            this.d = d;
            this.id = id;
        }

    }
    class Item {
        int x;
        int y;

        Item(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
