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
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.nextInt());
        }
        Arrays.sort(is, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.sum - o2.sum;
            }
        });

        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, sum - is[i].s);
            sum += is[i].w;
        }
        out.println(ans);
    }
    class Item {
        int w;
        int s;
        int sum;

        Item(int w, int s) {
            this.w = w;
            this.s = s;
            sum = s + w;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "w=" + w +
                    ", s=" + s +
                    '}';
        }
    }
}
