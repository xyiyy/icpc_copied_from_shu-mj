package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: nuggets
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        is = prune(is);
        if (is[0] == 1) {
            out.println(0);
            return ;
        }
        int N = (int) (2e6);
        boolean[] vis = new boolean[N];
        vis[0] = true;
        for (int i : is) {
            for (int j = 0; j < N; j++) {
                if (vis[j] && j + i < N) vis[j + i] = true;
            }
        }
        if (inf(vis)) out.println(0);
        else {
            int r = 0;
            for (int i = 0; i < N; i++) if (!vis[i]) r = i;
            out.println(r);
        }
    }

    private boolean inf(boolean[] vis) {
        int M = 1000;
        for (int i = vis.length - M; i < vis.length; i++) {
            if (!vis[i]) return true;
        }
        return false;
    }

    private int[] prune(int[] is) {
        Arrays.sort(is);
        List<Integer> ls = new ArrayList<Integer>();
        for (int i : is) {
            if (!divAble(ls, i)) ls.add(i);
        }
        return Algo.unBox(ls.toArray(new Integer[0]));
    }

    private boolean divAble(List<Integer> ls, int i) {
        for (int l : ls) if (i % l == 0) return true;
        return false;
    }

}
