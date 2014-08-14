package main;

import com.shu_mj.ds.Seg;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class Task3468 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int q = in.nextInt();
        SumSeg seg = new SumSeg(n);
        for (int i = 0; i < n; i++) {
            seg.update(i, i + 1, in.nextInt());
        }
        while (q-- != 0) {
            if (in.next().charAt(0) == 'Q') {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                out.println(seg.query(l, r));
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                int a = in.nextInt();
                seg.update(l, r, a);
            }
        }
    }

    class SumSeg extends Seg {

        public SumSeg(int n) {
            super(n);
        }

        @Override
        public long merge(long a, long b) {
            return a + b;
        }

        @Override
        public void pushIs(int o, int l, int r, long a) {
            is[o] += (r - l) * a;
        }

        @Override
        public void overPushLz(int o, int l, int r, long a) {
            lz[o] += a;
        }
    }
}
