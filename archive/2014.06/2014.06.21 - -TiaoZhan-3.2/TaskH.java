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
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        long n = in.nextLong();
        long sum = 1;
        int i = 1, j = 1;
        List<Item> ans = new ArrayList<Item>();
        while ((long) j * j <= n) {
            if (sum == n) {
                ans.add(new Item(i, j));
                if (i == j) {
                    j++;
                    sum += (long) j * j;
                } else {
                    sum -= (long) i * i;
                    i++;
                }
            } else if (sum < n) {
                j++;
                sum += (long) j * j;
            } else {
                sum -= (long) i * i;
                i++;
            }
        }
        Collections.sort(ans, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (o2.e - o2.b) - (o1.e - o1.b);
            }
        });
        out.println(ans.size());
        for (Item it : ans) {
            out.print((it.e - it.b + 1));
            for (int k = it.b; k <= it.e; k++) {
                out.print(" " + k);
            }
            out.println();
        }
    }
    class Item {
        int b;
        int e;

        Item(int b, int e) {
            this.b = b;
            this.e = e;
        }
    }
}
