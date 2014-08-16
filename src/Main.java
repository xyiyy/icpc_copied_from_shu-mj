import java.util.Arrays;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
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
		TaskL solver = new TaskL();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskL {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == -1) break;
            solve(n);
        }
    }

    private void solve(int n) {
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        ps = P.convexHull(ps);
        n = ps.length;
        if (n <= 2) {
            out.println("0.00");
            return ;
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            int k = (j + 1) % n;
            while (j != i) {
                while (P.area(ps[i], ps[j], ps[(k + 1) % n]) > P.area(ps[i], ps[j], ps[k])) k = (k + 1) % n;
                ans = Math.max(ans, P.area(ps[i], ps[j], ps[k]));
                j = (j + 1) % n;
            }
        }
        out.printf("%.2f%n", ans);
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

class P implements Comparable<P> {
    public static final double EPS = 1e-8;
    public static double add(double a, double b) {
        if (Math.abs(a + b) < EPS * (Math.abs(a) + Math.abs(b))) return 0;
        return a + b;
    }

    public final double x, y;

    public P(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public P sub(P p) {
        return new P(add(x, -p.x), add(y, -p.y));
    }

    public double det(P p) {
        return add(x * p.y, -y * p.x);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return compareTo((P) obj) == 0;
    }

    public int compareTo(P p) {
        int b = sig(x - p.x);
        if (b != 0) return b;
        return sig(y - p.y);
    }

    public static int sig(double x) {
        if (Math.abs(x) < EPS) return 0;
        return x < 0 ? -1 : 1;
    }

    //计算多边形的有向面积
    //点不需要有顺序
    public static double directedArea(P... ps) {
        double res = 0;
        for (int i = 0; i < ps.length; i++) {
            res += ps[i].det(ps[(i + 1) % ps.length]);
        }
        return res / 2;
    }
    //计算多边形的面积
    //点不需要有顺序
    public static double area(P... ps) {
        return Math.abs(directedArea(ps));
    }

    //凸包
    //逆时针 不包含线上的点
    //如果需要包含线上的点 将 <= 0 改成 < 0
    //但是需要注意此时不能有重点
    public static P[] convexHull(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        Arrays.sort(ps);
        P[] qs = new P[n * 2];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
            while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        P[] res = new P[k - 1];
        System.arraycopy(qs, 0, res, 0, k - 1);
        return res;
    }

}

