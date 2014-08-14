package test.on2014_08.on2014_08_03_POJ.Task2142;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task2142 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            long a = in.nextInt();
            long b = in.nextInt();
            long d = in.nextInt();
            if (a == 0 && b == 0 && d == 0) break;
            solve(a, b, d);
        }
    }

    private void solve(long a, long b, long d) {
//        long g = Num.gcd(a, Num.gcd(b, d));
//        a /= g;
//        b /= g;
//        d /= g;
        long[] gs = Num.exGcd(a, b);
        long x = gs[0], y = gs[1], m = gs[2];
        x *= d / m;
        y *= d / m;
        x = (x % (b / m) + (b / m)) % (b / m);
        y = (d - x * a) / b;
        long y2 = (y % (a / m) + (a / m)) % (a / m);
        long x2 = (d - y2 * b) / a;
        x = Math.abs(x);
        y = Math.abs(y);
        x2 = Math.abs(x2);
        y2 = Math.abs(y2);
//        Algo.debug(x, y, x2, y2);
        if (x + y < x2 + y2 || x + y == x2 + y2 && a * x + b * y <= a * x2 + b * y2) {
            out.println(x + " " + y);
        } else {
            out.println(x2 + " " + y2);
        }
    }
}
