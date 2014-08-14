package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class G {

    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (in.hasNext())
            solve(in, out);
    }
    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] maps = in.nextIntMatrix(n, n);
        E[] es = new E[n * (n - 1) / 2];
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                es[k++] = new E(i, j, Math.min(maps[j][i], maps[i][j]));
            }
        }
        Arrays.sort(es);
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        int ans = 0;
        for (E e : es) {
            if (find(e.from) == find(e.to)) {

            } else {
                ans += e.cost;
                join(e.from, e.to);
            }
        }
        out.println(ans);
    }

    private void join(int a, int b) {
        pre[find(a)] = find(b);
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
            return cost - o.cost;
        }
    }
}
