import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.Map;
import java.io.PrintStream;
import java.util.Comparator;
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
		TaskM solver = new TaskM();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskM {
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
            if (n == 0) break;
            solve(n);
        }
    }
    private void solve(int n) {
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        P[] qs = P.convexHullByAngle(ps);
        Map<P, Integer> index = new HashMap<P, Integer>();
        for (int i = 0; i < n; i++) {
            index.put(ps[i], i);
        }
        double allArea = P.area(qs);
        double res = P.area(P.convexHull(Arrays.copyOfRange(ps, 1, n)));
        P[] st = new P[n];
        for (int i = 1; i < qs.length; i++) {
            res = Math.min(res, allArea - work(ps, qs, index, st, i));
        }
        out.printf("%.2f%n", res);
    }

    private double work(P[] ps, P[] qs, Map<P, Integer> index, P[] st, int del) {
        int n = ps.length;
        int b = index.get(qs[del - 1]);
        int e = del + 1 >= qs.length ? n : index.get(qs[del + 1]);
        del = index.get(qs[del]);
        int k = 0;
        for (int i = b;  i <= e; ) {
            if (i == del) {
                i++;
                continue;
            }
            while (k > 1 && st[k - 1].sub(st[k - 2]).det(ps[i % n].sub(st[k - 1])) < P.EPS) k--;
            st[k++] = ps[(i++) % n];
        }
        st[k++] = ps[del];
        return P.area(Arrays.copyOf(st, k));
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

    public double dot(P p) {
        return add(x * p.x, y * p.y);
    }

    public double abs2() {
        return dot(this);
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
    
    // 按相对于 p0 的极角逆时针排序
    // 角度相同，则离 p0 距离更近的放在前面
    public static class CmpByAngle implements Comparator<P> {
        P p0;

        CmpByAngle(P p0) {
            this.p0 = p0;
        }

        public int compare(P o1, P o2) {
            double det = o1.sub(p0).det(o2.sub(p0));
            if (det != 0) return det > 0 ? -1 : 1;
            double dis = add(o1.sub(p0).abs2(), -o2.sub(p0).abs2());
            if (dis != 0) return dis > 0 ? 1 : -1;
            return 0;
        }
    }

    public static P[] convexHullByAngle(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        for (int i = 1; i < n; i++) {
            if (ps[i].y < ps[0].y || ps[i].y == ps[0].y && ps[i].x < ps[0].x) {
                Algo.swap(ps, 0, i);
            }
        }
        Arrays.sort(ps, 1, n, new CmpByAngle(ps[0]));
        P[] qs = new P[n];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        return Arrays.copyOf(qs, k);
    }

}

class Algo {


    public static<T> void swap(T[] ts, int i, int j) {
        T t = ts[i]; ts[i] = ts[j]; ts[j] = t;
    }


}

