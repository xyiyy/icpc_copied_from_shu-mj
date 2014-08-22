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
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            y[i] = in.nextInt() - 1;
            x[y[i]] = i;
        }
        int[] del = new int[m];
        boolean[] removed = new boolean[n];
        for (int i = 0; i < m; i++) {
            del[i] = in.nextInt() - 1;
            removed[del[i]] = true;
        }
//        Mat mat = new Mat(n, n);
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
//                mat.add(x[i], i);
//                res += mat.query(x[i], i);
                insert(x[i], i);
                res += query(x[i], i);
            }
        }
        long[] ans = new long[m];
        for (int i = m - 1; i >= 0; i--) {
//            mat.add(x[del[i]], del[i]);
//            res += mat.query(x[del[i]], del[i]);
            insert(x[del[i]], del[i]);
            res += query(x[del[i]], del[i]);
            ans[i] = res;
        }
        for (int i = 0; i < m; i++) {
            out.println(ans[i]);
        }
    }
    int query(int x, int y) {
        return count(0, y, x, maxy - 1) + count(x, 0, maxx - 1, y);
    }
    static class Node {
        int count;
        Node topLeft, topRight, bottomLeft, bottomRight;
    }

    Node root;

    static final int maxx = (1 << 30);
    static final int maxy = (1 << 30);

    // insert point (x,y)
    public void insert(int x, int y) {
        root = insert(root, 0, 0, maxx - 1, maxy - 1, x, y);
    }

    Node insert(Node node, int ax, int ay, int bx, int by, int x, int y) {
        if (ax > x || x > bx || ay > y || y > by)
            return node;
        if (node == null)
            node = new Node();
        ++node.count;
        if (ax == bx && ay == by)
            return node;

        int mx = (ax + bx) >> 1;
        int my = (ay + by) >> 1;

        node.bottomLeft = insert(node.bottomLeft, ax, ay, mx, my, x, y);
        node.topLeft = insert(node.topLeft, ax, my + 1, mx, by, x, y);
        node.bottomRight = insert(node.bottomRight, mx + 1, ay, bx, my, x, y);
        node.topRight = insert(node.topRight, mx + 1, my + 1, bx, by, x, y);

        return node;
    }

    // number of points in [x1,x2] x [y1,y2]
    public int count(int x1, int y1, int x2, int y2) {
        return count(root, 0, 0, maxx - 1, maxy - 1, x1, y1, x2, y2);
    }

    int count(Node node, int ax, int ay, int bx, int by, int x1, int y1, int x2, int y2) {
        if (node == null || ax > x2 || x1 > bx || ay > y2 || y1 > by)
            return 0;
        if (x1 <= ax && bx <= x2 && y1 <= ay && by <= y2)
            return node.count;

        int mx = (ax + bx) >> 1;
        int my = (ay + by) >> 1;

        int res = 0;
        res += count(node.bottomLeft, ax, ay, mx, my, x1, y1, x2, y2);
        res += count(node.topLeft, ax, my + 1, mx, by, x1, y1, x2, y2);
        res += count(node.bottomRight, mx + 1, ay, bx, my, x1, y1, x2, y2);
        res += count(node.topRight, mx + 1, my + 1, bx, by, x1, y1, x2, y2);
        return res;
    }
    static class T {
        int count;
        T topLeft;
        T topRight;
        T bottomLeft;
        T bottomRight;

        T(int count, T topLeft, T topRight, T bottomLeft, T bottomRight) {
            this.count = count;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
        T(int count) {
            this(count, NULL, NULL, NULL, NULL);
        }
    }
    static final T NULL = new T(0, null, null, null, null);

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

