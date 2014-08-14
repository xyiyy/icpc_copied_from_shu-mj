package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- != 0)
            solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int p = in.nextInt(), n = in.nextInt();
        int[] is = in.nextIntArray(n);
        boolean[] used = new boolean[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int crt = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && (crt == -1 || is[j] <= is[crt])) {
                    crt = j;
                }
            }
            int want = p / (n - i);
            used[crt] = true;
            int act = Math.min(is[crt], want);
            ans[crt] = act;
            p -= act;
        }
        if (p != 0) out.println("IMPOSSIBLE");
        else {
            for (int i = 0; i < n - 1; i++) {
                out.print(ans[i] + " ");
            }
            out.println(ans[n - 1]);
        }
    }
}
