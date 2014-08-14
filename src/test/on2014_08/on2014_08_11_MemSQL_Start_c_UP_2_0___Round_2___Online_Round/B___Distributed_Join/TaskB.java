package test.on2014_08.on2014_08_11_MemSQL_Start_c_UP_2_0___Round_2___Online_Round.B___Distributed_Join;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(m);
        Algo.sort(a);
        Algo.sort(b);
        long[] as = new long[n];
        long[] bs = new long[m];
        for (int i = 0; i < n; i++) as[i] = (i > 0 ? as[i - 1] : 0) + a[i];
        for (int i = 0; i < m; i++) bs[i] = (i > 0 ? bs[i - 1] : 0) + b[i];
        out.println(Math.min(work(a, b, as, bs, n, m), work(b, a, bs, as, m, n)));
    }

    private long work(int[] a, int[] b, long[] as, long[] bs, int n, int m) {
        long res = bs[m - 1];

        for (int i = 0; i < n - 1; i++) {
            res += Math.min(a[i], bs[m - 1]);
        }
        return res;
    }
}
