package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        long[] ls = in.nextLongArray(n);
        TreeMap<Long, Integer> map = new TreeMap<Long, Integer>();
        int m = n / 2;
        long ans = Math.abs(ls[0]);
        int cnt = 1;
        for (int i = 1; i < (1 << m); i++) {
            long sum = 0;
            for (int j = 0; j < m; j++) if ((i & (1 << j)) != 0) sum += ls[j];
            int bit = Integer.bitCount(i);
            if (!map.containsKey(sum)) map.put(sum, bit);
            else map.put(sum, Math.min(bit, map.get(sum)));
        }
        Map.Entry<Long, Integer> left = map.floorEntry(0L);
        Map.Entry<Long, Integer> right = map.ceilingEntry(0L);
        if (left != null) {
            long crt = Math.abs(left.getKey());
            int bit = left.getValue();
            if (crt < ans || crt == ans && bit < cnt) {
                ans = crt;
                cnt = bit;
            }
        }
        if (right != null) {
            long crt = Math.abs(right.getKey());
            int bit = right.getValue();
            if (crt < ans || crt == ans && bit < cnt) {
                ans = crt;
                cnt = bit;
            }
        }
        map.put(0L, 0);
        for (int i = 1; i < (1 << (n - m)); i++) {
            long sum = 0;
            for (int j = 0; j < (n - m); j++) if ((i & (1 << j)) != 0) sum += ls[j + m];
            left = map.floorEntry(-sum);
            right = map.ceilingEntry(-sum);
            if (left != null) {
                long crt = Math.abs(sum + left.getKey());
                int bit = left.getValue() + Integer.bitCount(i);
                if (crt < ans || crt == ans && bit < cnt) {
                    ans = crt;
                    cnt = bit;
                }
            }
            if (right != null) {
                long crt = Math.abs(sum + right.getKey());
                int bit = right.getValue() + Integer.bitCount(i);
                if (crt < ans || crt == ans && bit < cnt) {
                    ans = crt;
                    cnt = bit;
                }
            }
        }
        out.println(ans + " " + cnt);
    }
}
