package test.on2014_08.on2014_08_01_Codeforces_Round__259__Div__1_.A___Little_Pony_and_Expected_Maximum;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
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
        int m = in.nextInt();
        int n = in.nextInt();
        double res = 0;
        for (int i = 1; i <= m; i++) {
            res += i * (Math.pow(i * 1.0 / m, n) - Math.pow((i - 1) * 1.0 / m, n));
        }
        out.printf("%.12f%n", res);
    }
}
