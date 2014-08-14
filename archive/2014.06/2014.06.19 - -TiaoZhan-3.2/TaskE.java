package main;

import com.shu_mj.collections.map.IncHashMap;
import com.shu_mj.collections.map.IncMap;
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
        int[][] iss = in.nextIntMatrix(n, 4);
        IncMap<Integer> set = new IncHashMap<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                set.inc(iss[i][0] + iss[j][1]);
            }
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = iss[i][2] + iss[j][3];
                if (set.containsKey(-sum)) res += set.get(-sum);
            }
        }
        out.println(res);
    }
}
