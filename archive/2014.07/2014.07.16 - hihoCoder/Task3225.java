package main;

import com.shu_mj.ds.LongSegSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3225 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        final int MAX = 65536;
        LongSegSum seg = new LongSegSum(MAX * 3 + 10 * 3);
        while (in.hasNext()) {
            char c = in.next().charAt(0);
            String[] ss = in.next().split(",");
//            System.out.println(c + "" + ss[0] + "," +  ss[1]);
            int b = ss[0].charAt(0) == '[' ? 0 : 1;
            int e = ss[1].charAt(ss[1].length() - 1) == ']' ? 0 : 1;
            int s = Integer.parseInt(ss[0].substring(1));
            int t = Integer.parseInt(ss[1].substring(0, ss[1].length() - 1));
            if (c == 'U') {
                seg.update(s * 3 + 1 + b, t * 3 + 1 - e + 1, 0, 1);
            } else if (c == 'I') {
                b ^= 1;
                e ^= 1;
                seg.update(0, s * 3 + 1 - b + 1, 0, 0);
                seg.update(t * 3 + 1 + e, MAX * 3 + 1 + 1, 0, 0);
            } else if (c == 'D') {
                seg.update(s * 3 + 1 + b, t * 3 + 1 - e + 1, 0, 0);
            } else if (c == 'C') {
                seg.update(1, s * 3 + 1 - (b ^ 1) + 1, 0, 0);
                seg.update(t * 3 + 1 + (e ^ 1), MAX * 3 + 1 + 1, 0, 0);
                seg.update(s * 3 + 1 + b, t * 3 + 1 - e + 1, 1, 1);
            } else { // S
                seg.update(s * 3 + 1 + b, t * 3 + 1 - e + 1, 1, 1);
            }
//            seg.pushDownMark();
//
//            for (int i = 0; i <= MAX * 3; i++) if (seg.ls[i + seg.N] % 2 == 1) System.out.print(i + " " );
//            System.out.println();
        }
        boolean empty = true;
        int crt = 0;
        seg.pushDownMark();
        for (;;) {
            while (crt <= MAX * 3 && seg.ls[crt + seg.N] % 2 == 0) crt++;
            if (crt > MAX * 3) break;
            if (!empty) out.print(' ');
            empty = false;
            int l = crt;
            while (crt <= MAX * 3 && seg.ls[crt + seg.N] % 2 == 1) crt++;
            int r = crt - 1;
            out.print((l % 3 <= 1 ? "[" : "(") + (l / 3) + "," + (r / 3) + (r % 3 >= 1 ? "]" : ")"));
        }
        if (empty) out.println("empty set");
    }
}
