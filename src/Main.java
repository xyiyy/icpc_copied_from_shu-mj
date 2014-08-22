import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author shu_mj @ http://shu-mj.com
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		Scanner in = new Scanner(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task4973 solver = new Task4973();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task4973 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Seg seg = new Seg(n);
        while (m-- != 0) {
            char c = in.next().charAt(0);
            long s = in.nextLong() - 1;
            long t = in.nextLong();
            if (c == 'D') {
                seg.update(s, t, 2);
            } else { // Q
                out.println(seg.query(s, t));
            }
        }
    }

    static class Seg {
        public int N;
        public long[] infos;
        public long[] sizes;
        public long[] deltas;
        public static final long DEFAULT_INFO = 1;
        public static final long DEFAULT_SIZE = 1;
        public static final long DEFAULT_DELTA = 1;

        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            infos = new long[N * 2];
            sizes = new long[N * 2];
            deltas = new long[N * 2];
            Arrays.fill(deltas, DEFAULT_DELTA);
            for (int i = N; i < N * 2; i++) {
                infos[i] = DEFAULT_INFO;
                sizes[i] = DEFAULT_SIZE;
            }
            for (int i = N - 1; i > 0; i--) {
                pushUp(i);
            }
        }

        public long mergeSize(long a, long b) {
            return a + b;
        }
        public long mergeInfo(long a, long b) {
            return Math.max(a, b);
        }

        public void pushUp(int o) {
            infos[o] = mergeInfo(infos[o * 2], infos[o * 2 + 1]);
            sizes[o] = mergeSize(sizes[o * 2], sizes[o * 2 + 1]);
        }

        public void push(int o, long l, long r, long a) {
            sizes[o] *= a;
            infos[o] *= a;
            deltas[o] *= a;
        }

        public long query(long s, long t) {
            return query(1, 0, sizes[1], s, t);
        }

        long query(int o, long l, long r, long s, long t) {
            if (o >= N) {
                return t - s;
            } else if (s <= l && r <= t) {
                // 如果 [l, r) 和 [s, t) 不同，需要修改
                return infos[o];
            } else {
                pushDown(o, l, r);
                long m = l + sizes[o * 2];
                if (t <= m) return query(o * 2, l, m, s, t);
                if (s >= m) return query(o * 2 + 1, m, r, s, t);
                return mergeInfo(query(o * 2, l, m, s, m), query(o * 2 + 1, m, r, m, t));
            }
        }

        public void update(long s, long t, long a) {
            update(1, 0, sizes[1], s, t, a);
        }

        void update(int o, long l, long r, long s, long t, long a) {
            if (o >= N) {
                sizes[o] += t - s;
                infos[o] += t - s;
            } else if (s <= l && r <= t) {
                // 如果 [l, r) 和 [s, t) 不同，需要修改
                push(o, l, r, a);
            } else {
                pushDown(o, l, r);
                long m = l + sizes[o * 2];
                if (s < m) update(o * 2, l, m, s, Math.min(m, t), a);
                if (t > m) update(o * 2 + 1, m, r, Math.max(s, m), t, a);
                pushUp(o);
            }
        }

        public void pushDown(int o, long l, long r) {
            if (deltas[o] != DEFAULT_DELTA) {
                long m = (l + r) / 2;
                push(o * 2, l, m, deltas[o]);
                push(o * 2 + 1, m, r, deltas[o]);
                deltas[o] = DEFAULT_DELTA;
            }
        }

    }

}

class Scanner {
    BufferedReader br;
    StringTokenizer st;

    public Scanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
        eat("");
    }

    private void eat(String s) {
        st = new StringTokenizer(s);
    }

    public String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!st.hasMoreTokens()) {
            String s = nextLine();
            if (s == null)
                return false;
            eat(s);
        }
        return true;
    }

    public String next() {
        hasNext();
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

}

