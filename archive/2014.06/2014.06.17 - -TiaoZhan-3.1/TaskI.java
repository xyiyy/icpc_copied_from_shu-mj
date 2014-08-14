package main;

        import com.shu_mj.tpl.Scanner;
        import java.io.PrintWriter;
        import java.util.*;
        import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext())
            run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        int l = 0, r = is[n - 1] - is[0] + 1;
        long index = ((long) n * (n - 1) / 2 - 1) / 2 + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (calc(is, n, m) < index) l = m + 1;
            else r = m;
        }
//        l--;
        out.println(l);
    }

    private long calc(int[] is, int n, int m) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += i - Algo.lowerBound(is, is[i] - m);
        }
        return res;
    }
}
