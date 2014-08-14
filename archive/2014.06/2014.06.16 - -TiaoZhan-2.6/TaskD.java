package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TaskD {

    private boolean[] pri;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        pri = Num.primeTableBoolean(10000);
        while (t-- != 0)
            solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int s = in.nextInt(), t = in.nextInt();
        int[] step = new int[10000];
        Arrays.fill(step, -1);
        step[s] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(s);
        while (!que.isEmpty()) {
            int crt = que.poll();
            if (crt == t) {out.println(step[t]); return ;}
            for (int i = 1; i < 10000; i *= 10) {
                int c = crt / (i * 10) * (i * 10) + crt % i;
                for (int j = 0; j < 10; j++) {
                    int n = c + j * i;
//                    Algo.debug(crt, n, n / 1000, pri[n], step[n]);
                    if (n == crt || n / 1000 == 0 || !pri[n]) continue;
                    if (step[n] != -1) continue;
                    step[n] = step[crt] + 1;
//                    Algo.debug(i, j, c, crt, n);
                    que.add(n);
                }
            }
        }
        out.println("Impossible");
    }
}
