package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskH {

    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        E[] es = new E[m];
        for (int i = 0; i < m; i++) {
            es[i] = new E(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        Arrays.sort(es);
        int ans = 0;
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        for (E e : es) {
            if (find(e.from) == find(e.to)) {

            } else {
                ans += e.cost;
                pre[find(e.from)] = find(e.to);
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (pre[i] == i) cnt++;
        }
        if (cnt == 1) out.println(ans);
        else out.println(-1);
    }

    private int find(int x) {
        if (pre[x] != x) pre[x] = find(pre[x]);
        return pre[x];
    }

    class E implements Comparable<E> {
        int from;
        int to;
        int cost;

        E(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return o.cost - cost;
        }
    }
}
