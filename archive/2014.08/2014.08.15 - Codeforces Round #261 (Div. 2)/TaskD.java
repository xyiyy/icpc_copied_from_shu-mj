package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(is[i])) map.put(is[i], max++);
            is[i] = map.get(is[i]);
        }
        int[] left = new int[n];
        int[] cnt = new int[max];
        for (int i = 0; i < n; i++) {
            cnt[is[i]]++;
            left[i] = cnt[is[i]];
        }
        Arrays.fill(cnt, 0);
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            cnt[is[i]]++;
            right[i] = cnt[is[i]];
        }
        BIT bit = new BIT(n);
        long res = 0;
        for (int i = n - 1; i > 0; i--) {
            bit.add(right[i], 1);
            res += bit.sum(0, left[i - 1]);
        }
        out.println(res);
    }
}
