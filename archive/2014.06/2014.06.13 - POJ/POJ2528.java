package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class POJ2528 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- != 0)
            solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int color = 0;
        Intervals<Integer, Integer> Int = new Intervals<Integer, Integer>(-1, (int) 1e8, -1);
        while (n-- != 0) {
            int a = in.nextInt(), b = in.nextInt() + 1;
            Int.paint(a, b, color++);
        }
        out.println(Int.calc());
    }
}
