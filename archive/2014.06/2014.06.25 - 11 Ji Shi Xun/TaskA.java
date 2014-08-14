package main;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int[] res = Num.exgcd(a, m);
        int g = res[2];
        if (b % g != 0) {
            out.println("no solutions!");
            return ;
        }
        int x = res[0], y = res[1];
        Algo.debug(x, y, a, b, m);
        while (x < 0) x += m / g;
        while (x >= m) x -= m / g;
        x *= b / g;
        out.println(x);
    }
}
