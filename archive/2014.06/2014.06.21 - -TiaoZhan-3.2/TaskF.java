package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
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
            int k = in.nextInt();
            if (n == 0 && k == 0) break;
            solve(n, k);
        }
    }

    private void solve(int n, int k) {
        int[] is = in.nextIntArray(n);
        Item[] ss = new Item[n + 1];
        ss[0] = new Item(is[0], 1);
        for (int i = 1; i < n; i++) {
            ss[i] = new Item(ss[i - 1].sum + is[i], i + 1);
        }
        ss[n] = new Item(0, 0);
        Arrays.sort(ss, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.sum - o2.sum;
            }
        });
        while (k-- != 0) {
            int x = in.nextInt();
            if (n == 1) {
                out.println(Math.abs(is[0]) + " 1 1");
                continue;
            }
            int i = 0, j = 1;
            int delta = Integer.MAX_VALUE;
            int ans = is[0], l = 1, r = 1;
            while (j <= n) {
                if (i == j) {
                    j++;
                    continue;
                }

                int sum = ss[j].sum - ss[i].sum;
                int crtDelta = Math.abs(x - sum);
                if (crtDelta < delta) {
                    delta = crtDelta;
                    ans = sum;
                    l = ss[i].id;
                    r = ss[j].id;
                }
                if (sum > x) {
                    i++;
                } else if (sum < x) {
                    j++;
                } else {
                    break;
                }
            }
            if (l > r) {int t = l; l = r; r = t;}
            out.println(ans + " " + (l + 1) + " " + r);
        }
    }
    class Item {
        int sum;
        int id;

        Item(int sum, int id) {
            this.sum = sum;
            this.id = id;
        }


    }
}
