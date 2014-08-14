package main;

import com.shu_mj.collections.map.IncHashMap;
import com.shu_mj.collections.map.IncMap;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < is.length; i++) {
            int j = is[i];
            set.add(j);
        }
        int ans = Integer.MAX_VALUE;
        IncMap<Integer> map = new IncHashMap<Integer>();
        map.inc(is[0]);
        int f = 0;
        if (set.size() == 1) ans = 1;
        for (int i = 1; i < is.length; i++) {
            map.inc(is[i]);
            while (map.get(is[f]) > 1) {
                map.dec(is[f]);
                if (map.get(is[f]) == 0) map.remove(is[f]);
                f++;
            }
            if (map.size() == set.size()) ans = Math.min(ans, i - f + 1);
        }
        out.println(ans);
    }
}
