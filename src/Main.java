import java.util.LinkedList;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collection;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Queue;
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
		Task2 solver = new Task2();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task2 {
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
        int[] mv = new int[m];
        int[] nv = new int[n];
        Dinic.V[] vs = new Dinic.V[n + m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n + m];
        Dinic.V t = vs[n + m + 1];
        int total = 0;
        for (int i = 0; i < m; i++) {
            String[] ss = in.nextLine().split(" ");
            int sn = ss.length;
            int[] is = new int[sn];
            for (int j = 0; j < sn; j++) is[j] = Integer.parseInt(ss[j]);
            int val = is[0];
            total += val;
            s.add(vs[i], val);
            for (int j = 1; j < sn; j++) {
                vs[i].add(vs[m + is[j] - 1], Dinic.INF);
            }
        }
        for (int i = 0; i < n; i++) {
            vs[m + i].add(t, in.nextInt());
        }
        int ans = total - Dinic.dinic(s, t);
        for (int i = 0; i < m; i++) {
            if (vs[i].p == s.p) out.print((i + 1) + " ");
        }
        out.println();
        for (int i = 0; i < n; i++) {
            if (vs[i + m].p == s.p) out.print((i + 1) + " ");
        }
        out.println();
        out.println(ans);
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

}

class Dinic {
    public static int INF = Integer.MAX_VALUE / 4;

    public static int dinic(V s, V t) {
        int flow = 0;
        for (int p = 1; ; p++) {
            Queue<V> que = new LinkedList<V>();
            s.level = 0;
            s.p = p;
            que.offer(s);
            while (!que.isEmpty()) {
                V v = que.poll();
                v.iter = v.es.size() - 1;
                for (E e : v.es)
                    if (e.to.p < p && e.cap > 0) {
                        e.to.level = v.level + 1;
                        e.to.p = p;
                        que.offer(e.to);
                    }
            }
            if (t.p < p) return flow;
            for (int f; (f = dfs(s, t, INF)) > 0; ) flow += f;
        }
    }

    public static int dfs(V v, V t, int f) {
        if (v == t) return f;
        for (; v.iter >= 0; v.iter--) {
            E e = v.es.get(v.iter);
            if (v.level < e.to.level && e.cap > 0) {
                int d = dfs(e.to, t, Math.min(f, e.cap));
                if (d > 0) {
                    e.cap -= d;
                    e.rev.cap += d;
                    return d;
                }
            }
        }
        return 0;
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public int level;
        public int p;
        public int iter;
        public void add(V to, int cap) {
            E e = new E(to, cap), rev = new E(this, 0);
            e.rev = rev;
            rev.rev = e;
            es.add(e);
            to.es.add(rev);
        }
    }

    public static class E {
        public V to;
        public E rev;
        public int cap;

        public E(V to, int cap) {
            this.to = to;
            this.cap = cap;
        }
    }
}

