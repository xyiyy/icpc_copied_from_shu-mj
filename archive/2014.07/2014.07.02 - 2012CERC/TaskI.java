package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = in.nextInt();
            B[i] = in.nextInt();
            C[i] = in.nextInt();
        }
        int edge = 0, point = 0;
        for (int i = 0; i < n; i++) {
            int p = 0;
            for (int j = 0; j < n; j++) {
                if ((long) A[i] * B[j] != (long) A[j] * B[i]) p++;
            }
            point += p;
            edge += p + 1;
        }
        point /= 2;
        Set<BitSet> set = new HashSet<BitSet>();
        for (int i = 0; i < m; i++) {
            BitSet b = new BitSet(n);
            long x = in.nextInt();
            long y = in.nextInt();
            for (int j = 0; j < n; j++) {
                if (A[j] * x + B[j] * y + C[j] < 0) b.set(j);
            }
            set.add(b);
        }
        if (set.size() == edge - point + 1) out.println("PROTECTED");
        else out.println("VULNERABLE");
    }
    class BitSet {
        long a;
        long b;
        BitSet(int n) {

        }
        void set(int id) {
            if (id < 50) a |= 1L << id;
            else b |= 1L << (id - 50);
        }

        @Override
        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;

            BitSet bitSet = (BitSet) o;

            if (a != bitSet.a) return false;
            if (b != bitSet.b) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            return result;
        }
    }
}
