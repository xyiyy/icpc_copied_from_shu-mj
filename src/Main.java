import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.math.BigInteger;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
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
		TaskK solver = new TaskK();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskK {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] c = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            c[u][v] += w;
            c[v][u] += w;
        }
        out.println(MinCut.minCut(c));
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

class MinCut {
    public static final int INF = Integer.MAX_VALUE / 4;

    public static int minCut(int[][] c) {
        int n = c.length, cut = INF;
        int[] id = new int[n], b = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
        for (; n > 1; n--) {
            Arrays.fill(b, 0);
            for (int i = 0; i + 1 < n; i++) {
                int p = i + 1;
                for (int j = i + 1; j < n; j++) {
                    b[id[j]] += c[id[i]][id[j]];
                    if (b[id[p]] < b[id[j]]) p = j;
                }
                Algo.swap(id, i + 1, p);
            }
            cut = Math.min(cut, b[id[n - 1]]);
            for (int i = 0; i < n - 2; i++) {
                c[id[i]][id[n - 2]] += c[id[i]][id[n - 1]];
                c[id[n - 2]][id[i]] += c[id[n - 1]][id[i]];
            }
        }
        return cut;
    }
}

class Algo {


    public static void swap(int[] is, int i, int j) {
        int t = is[i]; is[i] = is[j]; is[j] = t;
    }


}

