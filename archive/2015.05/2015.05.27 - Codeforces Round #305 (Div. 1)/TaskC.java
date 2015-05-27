package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] is = in.nextIntArray(n);
        List<Integer>[] ls = new List[n];
        Num.primeTable(500000, new ArrayList<Integer>());
        for (int i = 0; i < n; i++) {
            ls[i] = Num.primeFactors(is[i]);
        }
        long res = 0;
        int cnt = 0;
        int[] pc = new int[500001];
        boolean[] added = new boolean[n];
        while (q-- > 0) {
            int i = in.nextInt() - 1;
            if (added[i]) {
                cnt--;
                for (int s = 1; s < (1 << ls[i].size()); s++) {
                    int v = 1;
                    for (int j = 0; j < ls[i].size(); j++) {
                        if ((s & (1 << j)) != 0) {
                            v *= ls[i].get(j);
                        }
                    }
                    pc[v]--;
                }
            }
            long de = 0;
            for (int s = 1; s < (1 << ls[i].size()); s++) {
                int c = 0;
                int v = 1;
                for (int j = 0; j < ls[i].size(); j++) {
                    if ((s & (1 << j)) != 0) {
                        c++;
                        v *= ls[i].get(j);
                    }
                }
                if (c % 2 == 1) {
                    de += pc[v];
                } else {
                    de -= pc[v];
                }
//                Algo.debug(i, de, v, pc[v]);
            }
            if (added[i]) {
                res -= de;
            } else {
                res += de;
                cnt++;
                for (int s = 1; s < (1 << ls[i].size()); s++) {
                    int v = 1;
                    for (int j = 0; j < ls[i].size(); j++) {
                        if ((s & (1 << j)) != 0) {
                            v *= ls[i].get(j);
                        }
                    }
                    pc[v]++;
                }
            }
            added[i] ^= true;
            out.println((long) cnt * (cnt - 1) / 2 - res);
        }
    }
}
