package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] X = new int[n];
        int[] Y = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = in.nextInt();
            Y[i] = in.nextInt();
        }
        int xMax = hash(X);
        int yMax = hash(Y);

        BIT bit = new BIT(xMax);
        List<Integer>[] line = new List[yMax];
        for (int i = 0; i < yMax; i++) line[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            line[Y[i]].add(X[i]);
        }

        boolean[] vis = new boolean[xMax];
        long res = n;
        for (int i = 0; i < yMax; i++) {
            Collections.sort(line[i]);
            for (int j = 0; j < line[i].size(); j++) {
                int x = line[i].get(j);
                int s = bit.sum(0, x + 1);
                if (vis[x]) res += s;
                else vis[x] = true;
                bit.add(x, -s);
                bit.add(x + 1, s);

                if (j + 1 < line[i].size()) {
                    bit.add(line[i].get(j + 1), -1);
                    bit.add(x + 1, 1);
                }
            }
        }
        out.println(res);
    }

    private int hash(int[] x) {
        int[] xc = x.clone();
        Arrays.sort(xc);
        xc = Algo.unique(xc);
        for (int i = 0; i < x.length; i++) {
            x[i] = Algo.lowerBound(xc, x[i]);
        }
        return xc.length;
    }
}
