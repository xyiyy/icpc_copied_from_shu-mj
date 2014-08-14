package test.on2014_08.on2014_08_08_Codeforces_Round__206__Div__2_.B___Vasya_and_Public_Transport;



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
        int[] cs = in.nextIntArray(4);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ns = in.nextIntArray(n);
        int[] ms = in.nextIntArray(m);
        for (int i = 0; i < n; i++) {
            ns[i] = Math.min(ns[i] * cs[0], cs[1]);
        }
        for (int i = 0; i < m; i++) {
            ms[i] = Math.min(ms[i] * cs[0], cs[1]);
        }
        int s1 = Math.min((int) Algo.sum(ns), cs[2]);
        int s2 = Math.min((int) Algo.sum(ms), cs[2]);
        out.println(Math.min(cs[3], s1 + s2));
    }
}
