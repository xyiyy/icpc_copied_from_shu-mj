package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        for (;;) {
            int n = in.nextInt();
            if (n == 0) return ;
            work(n, in, out);
        }
    }

    private void work(int n, Scanner in, PrintWriter out) {
        if (!Num.isPrime(n) && isCarmichael(n))
            out.println("The number " + n + " is a Carmichael number.");
        else
            out.println(n + " is normal.");
    }

    private boolean isCarmichael(int n) {
        Algo.M = n;
        for (int i = 2; i < n; i++) {
            if (Algo.powMod(i, n) != i) return false;
        }
        return true;
    }

}
