package main;

import com.shu_mj.ds.Rational;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        ten[1] = 10;
        nine[1] = 9;
        for (int i = 2; i < 20 && ten[i - 1] < Long.MAX_VALUE / 10; i++) {
            ten[i] = ten[i - 1] * 10;
            nine[i] = nine[i - 1] * 10 + 9;
        }
        while (in.hasNext()) {
            String s = in.next();
            if (s.length() == 1) return ;
            gao(s, in, out);
        }
    }

    long[] ten = new long[20];
    long[] nine = new long[20];
    private void gao(String s, Scanner in, PrintWriter out) {
        s = s.substring(2, s.length() - 3);
//        Rational r = null;
        int l = s.length();
//        if (s.charAt(l - 1) == '0') {
//            r = new Rational(new BigInteger(s), BigInteger.valueOf(ten[l]));
//        }
//        Algo.debug(s);
        Rational r = new Rational(new BigInteger(s), BigInteger.valueOf(nine[l]));
        for (int i = 1; i < l; i++) {
            r = min(r, new Rational(new BigInteger(s.substring(0, i)), BigInteger.valueOf(ten[i])).add(
                    new Rational(new BigInteger(s.substring(i)), BigInteger.valueOf(nine[l - i] * ten[i]))));
        }
        out.println(r.num + "/" + r.den);
    }

    private Rational min(Rational a, Rational b) {
        if (a.den.compareTo(b.den) < 0) return a;
        return b;
    }
}
