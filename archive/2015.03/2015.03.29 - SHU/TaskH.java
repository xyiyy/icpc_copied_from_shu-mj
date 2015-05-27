package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(1 << n);
        Seg seg = new Seg(is);
        while (m-- != 0) {
            int p = in.nextInt() - 1;
            int b = in.nextInt();
            seg.update(p, b);
            out.println(seg.is[1]);
        }
    }
    class Seg {
        int N;
        int[] is;
        int nl;
        Seg(int[] js) {
            N = js.length;
            is = new int[N * 2];
            for (int i = 0; i < N; i++) {
                is[i + N] = js[i];
            }
            nl = Integer.numberOfLeadingZeros(N);
            for (int i = N - 1; i > 0; i--) {
                if ((Integer.numberOfLeadingZeros(i) - nl) % 2 == 1) {
                    is[i] = is[i * 2] | is[i * 2 + 1];
                } else {
                    is[i] = is[i * 2] ^ is[i * 2 + 1];
                }
            }
        }
        void update(int k, int a) {
            is[k + N] = a;
            boolean b = true;
            for (int i = (k + N) / 2; i > 0; i >>= 1, b ^= true) {
                if (b) is[i] = is[i * 2] | is[i * 2 + 1];
                else is[i] = is[i * 2] ^ is[i * 2 + 1];
            }
        }
    }
}
