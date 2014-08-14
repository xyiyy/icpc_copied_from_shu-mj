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
        List<Integer> primes = new ArrayList<Integer>();
        Num.primeTable(n, primes);
        boolean[] vis = new boolean[n + 1];

        List<Ans> ans = new ArrayList<Ans>();
        for (int p : primes) {
            if (p == 2) continue;
            int cnt = 0;
            for (int i = p; i <= n; i += p) {
                if (!vis[i]) cnt++;
            }
            int ta = 1, tb = 1;
            if (cnt % 2 == 0) {
                for (int i = p; i <= n; i += p)
                    if (!vis[i]) {
                        if (cnt % 2 == 0) ta = i;
                        else {
                            tb = i;
                            vis[ta] = true;
                            vis[tb] = true;
                            ans.add(new Ans(ta, tb));
                        }
                        cnt--;
                    }
            }else {
                ta = p;
                for (int i = p * 3; i <= n; i += p) if (!vis[i]) {
                    if (cnt % 2 == 0) ta = i;
                    else {
                        tb = i;
                        vis[ta] = true;
                        vis[tb] = true;
                        ans.add(new Ans(ta, tb));
                    }
                    cnt--;
                }
            }
        }
        int ta = 2, tb = -1;
        for (int i = 4; i <= n; i += 2) if (!vis[i]) {
            if (ta == -1) {
                ta = i;
            } else {
                tb = i;
                ans.add(new Ans(ta, tb));
                ta = -1;
            }
        }
        out.println(ans.size());
        for (Ans i : ans) {
            out.println(i.a + " " + i.b);
        }
    }
    class Ans {
        int a;
        int b;

        Ans(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
