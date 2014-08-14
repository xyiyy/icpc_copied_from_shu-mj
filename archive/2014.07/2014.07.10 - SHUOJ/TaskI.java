package main;



import com.shu_mj.ds.BIT;
import com.shu_mj.ds.LongBIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        int[] zs = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
            zs[i] = in.nextInt();
        }
        int m = in.nextInt();
        Event[] es = new Event[m];
        for (int i = 0; i < m; i++) {
            es[i] = new Event(in.nextInt(), in.nextInt() - 1, in.nextInt() - 1);
        }
        Event[] esr = es.clone();
        Arrays.sort(es, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.l - o2.l;
            }
        });
        Arrays.sort(esr, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.r - o2.r;
            }
        });
        long ans = 0;
        int maxTime = 1;
        for (Event e : es) maxTime = Math.max(maxTime, e.t);
        maxTime++;
        TreeSet<Integer> set = new TreeSet<Integer>();
        BIT cnt = new BIT(maxTime);
        LongBIT sum = new LongBIT(maxTime);

        for (int i = 0, j = 0, k = 0; i < n; i++) {
            while (j < m && es[j].l == i) {
                Event e = es[j];
                Integer right = set.higher(e.t);
                Integer left = set.lower(e.t);
                if (left != null && right != null) {
                    cnt.add(right - left, -1);
                    cnt.add(e.t - left, 1);
                    cnt.add(right - e.t, 1);
                    sum.add(right - left, -(right - left));
                    sum.add(e.t - left, e.t - left);
                    sum.add(right - e.t, right - e.t);
                } else if (left != null) {
                    cnt.add(e.t - left, 1);
                    sum.add(e.t - left, e.t - left);
                } else if (right != null) {
                    cnt.add(right - e.t, 1);
                    sum.add(right - e.t, right - e.t);
                }
                set.add(e.t);
                j++;
            }
            if (!set.isEmpty()) {
                int firstTime = set.first();
                xs[i] = xs[i] - (int) Math.min(xs[i], firstTime * 1L * ys[i]);
                ans += zs[i] - xs[i];
                if (ys[i] > 0) {
                    int time = Math.min(maxTime, (zs[i] + ys[i] - 1) / ys[i]);
                    ans += cnt.sum(time, maxTime) * 1L * zs[i];
                    ans += sum.sum(0, time) * ys[i];
                }
            }
            while (k < m && esr[k].r == i) {
                Event e = esr[k];
                Integer left = set.lower(e.t);
                Integer right = set.higher(e.t);
                if (left != null && right != null) {
                    cnt.add(right - left, 1);
                    cnt.add(e.t - left, -1);
                    cnt.add(right - e.t, -1);
                    sum.add(right - left, (right - left));
                    sum.add(e.t - left, -(e.t - left));
                    sum.add(right - e.t, -(right - e.t));
                } else if (left != null) {
                    cnt.add(e.t - left, -1);
                    sum.add(e.t - left, -(e.t - left));
                } else if (right != null) {
                    cnt.add(right - e.t, -1);
                    sum.add(right - e.t, -(right - e.t));
                }
                set.remove(e.t);
                k++;
            }
        }
        out.println(ans);
    }
    class Event {
        int t;
        int l;
        int r;

        Event(int t, int l, int r) {
            this.t = t;
            this.l = l;
            this.r = r;
        }
    }
}
