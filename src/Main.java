import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
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
		Task3 solver = new Task3();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task3 {
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
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n * 2];
        Map<BipartiteMatching.V, Integer> index = new HashMap<BipartiteMatching.V, Integer>();
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
            index.put(vs[i], i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].connect(vs[v + n]);
        }
        int ans = BipartiteMatching.bipartiteMatching(vs);
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) if (!vis[i] && vs[i + n].pair == null) {
            int u = i;
            for (;;) {
                out.print((u + 1) + " ");
                vis[u] = true;
                if (vs[u].pair == null) break;
                u = index.get(vs[u].pair) - n;
            }
            out.println();
        }
        out.println(n - ans);
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

class BipartiteMatching {
    public static int bipartiteMatching(V[] vs) {
        int match = 0;
        for (V v : vs)
            if (v.pair == null) {
                for (V u : vs) u.used = false;
                if (dfs(v)) match++;
            }
        return match;
    }

    public static boolean dfs(V v) {
        v.used = true;
        for (V u : v.vs) {
            u.used = true;
            V w = u.pair;
            if (w == null || !w.used && dfs(w)) {
                v.pair = u;
                u.pair = v;
                return true;
            }
        }
        return false;
    }

    public static class V {
        public List<V> vs = new ArrayList<V>();
        public V pair;
        public boolean used;

        public void connect(V v) {
            vs.add(v);
            v.vs.add(this);
        }

    }
}

