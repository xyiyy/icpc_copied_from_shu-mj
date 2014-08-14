package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shu_mj.tpl.Algo;

public class TaskP {
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
            System.gc();
        }
    }

    private void solve() {
        int n;
        try {
            n = in.nextInt();
        } catch (Throwable e) {
            return ;
        }
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        boolean[][] mat = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int[] is;
            try {
                is = parse();
            } catch (Throwable e) {
                while (true);
            }
            for (int j = 2; j < is.length; j++) {
                int u = is[0];
                int v = is[j];
                mat[u][v] = mat[v][u] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (mat[i][j]) vs[i].connect(vs[j]);
            }
        }
        try {
            out.println(n - BipartiteMatching.bipartiteMatching(vs));
        } catch (Throwable e) {
            while (true);
        }
    }
    int[] parse() {
        String line = in.nextLine();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        List<Integer> list = new ArrayList<Integer>();
        while (m.find()) {
            list.add(Integer.parseInt(m.group()));
        }
        return Algo.unBox(list.toArray(new Integer[0]));
    }
}
