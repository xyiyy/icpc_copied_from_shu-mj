package main;

import com.shu_mj.collections.map.IncMap;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;
    private List<Long> ans;
    private Map<Long, List<Long>> dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        long x = in.nextLong();
        long k = in.nextLong();
        ans = new ArrayList<Long>();
        dp = new HashMap<Long, List<Long>>();
        dfs(x, (int) Math.min(k, 100000));
        for (long l : ans) {
            out.print(l + " ");
        }
        out.println();
    }
    void dfs(long x, int k) {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Algo.debug(x, k);
        if (k == 0 || x == 1) {
            if (ans.size() < 100000) {
                ans.add(x);
            }
            return ;
        }
        if (!dp.containsKey(x)) {
            List<Long> list = new ArrayList<Long>();
            long i = 1;
            for (; i * i <= x; i++) {
                if (x % i == 0) {
                    list.add(i);
                }
            }
            i--;
            if (i * i == x) i--;
            for (; i >= 1; i--) {
                if (x % i == 0) {
                    list.add(x / i);
                }
            }
            dp.put(x, list);
        }
        for (long i : dp.get(x)) {
            if (ans.size() < 100000) dfs(i, k - 1);
        }
    }
}
