package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: cowxor
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            is[i] = in.nextInt() ^ is[i - 1];
        }
        int res = -1;
        int left = 0, right = 0;
        int[] last = new int[1 << 22];
        Arrays.fill(last, -1);
        for (int i = 0; i <= 20; i++) {
            last[1 << i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            int x = is[i] ^ ((1 << 21) - 1);
            int crt = 0;
            for (int b = 20; b >= 0; b--) {
                crt ^= x & (1 << b);
                if (last[(crt << 1) ^ (1 << b)] == -1) {
                    crt ^= 1 << b;
                }
            }
            if (last[(crt << 1) ^ 1] != -1) {
                if (res < (is[i] ^ crt)) {
                    res = is[i] ^ crt;
                    right = i;
                    left = last[(crt << 1) ^ 1];
                }
            }
            for (int b = 0, c = is[i]; b <= 20; b++) {
                last[(c << 1) ^ (1 << b)] = i;
                c ^= c & (1 << b);
            }
        }
        out.println(res + " " + (left + 1) + " " + right);
    }
}
