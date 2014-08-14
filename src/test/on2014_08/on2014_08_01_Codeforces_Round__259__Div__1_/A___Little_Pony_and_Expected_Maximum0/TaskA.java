package test.on2014_08.on2014_08_01_Codeforces_Round__259__Div__1_.A___Little_Pony_and_Expected_Maximum0;





import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;
import java.math.*;

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
        BigDecimal res = BigDecimal.ZERO;
//        double res = 0;
        MathContext mc = new MathContext(10);
        for (int i = 1; i <= m; i++) {
            BigDecimal I = BigDecimal.valueOf(i);
            Algo.debug(i);
            res = res.add(I.multiply(pow(I, n, mc).subtract(pow(I.subtract(BigDecimal.ONE), n, mc))));
        }
        out.printf("%.12f%n", res.divide(pow(BigDecimal.valueOf(m), n, mc)));
    }

    private BigDecimal pow(BigDecimal p, int e, MathContext mc) {
        BigDecimal res = BigDecimal.ONE;
        while (e != 0) {
            if ((e & 1) != 0) res = res.multiply(p, mc);
            e = e >> 1;
            p = p.multiply(p, mc);
        }
        return res;
    }
}
