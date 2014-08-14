package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Map;

public class TaskE {

    private BigInteger[] f;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        final int MAX_N = 1000;
        f = new BigInteger[MAX_N];
        f[0] = BigInteger.ONE;
        for (int i = 1; i < MAX_N; i++) {
            f[i] = f[i - 1].multiply(BigInteger.valueOf(i));
        }
        while (in.hasNext())
            solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int x = in.nextInt();
        Map<Integer, Integer> map = Num.factors(x);
        int sum = 0;
        for (int i : map.values()) {
            sum += i;
        }
        BigInteger way = f[sum];
        for (int i : map.values()) {
            way = way.divide(f[i]);
        }
        out.println(sum + " " + way);
    }
}
