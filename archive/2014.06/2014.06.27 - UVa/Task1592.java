package main;

import com.shu_mj.collections.map.IncHashMap;
import com.shu_mj.collections.map.IncMap;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1592 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] maps = new int[n][m];
        Map<String, Integer> map = new HashMap<String, Integer>();
        int c = 0;
        for (int i = 0; i < n; i++) {
            String[] ss = in.nextLine().split(",");
            for (int j = 0; j < m; j++) {
                if (!map.containsKey(ss[j])) map.put(ss[j], c++);
                maps[i][j] = map.get(ss[j]);
            }
        }
        long M = 100000000;
        for (int u = 0; u < m; u++) {
            for (int v = u + 1; v < m; v++) {
                Map<Long, Integer> set = new HashMap<Long, Integer>();
                for (int i = 0; i < n; i++) {
                    long val = M * maps[i][u] + maps[i][v];
                    if (set.containsKey(val)) {
                        out.println("NO");
                        out.println(set.get(val) + " " + (i + 1));
                        out.println((u + 1) + " " + (v + 1));
                        return ;
                    }
                    set.put(val, i + 1);
                }
            }
        }
        out.println("YES");
    }

}
