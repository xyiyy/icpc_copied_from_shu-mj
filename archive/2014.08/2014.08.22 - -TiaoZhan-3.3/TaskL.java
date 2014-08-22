package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskL {
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
        T change(T topLeft, T topRight, T bottomLeft, T bottomRight) {
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
            this.count = topLeft.count + topRight.count + bottomLeft.count + bottomRight.count;
            return this;
        }
    }
    static final T NULL = new T(0, null, null, null, null);

    static class A {
        final int ax;
        final int ay;
        final int bx;
        final int by;

        A(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }
        boolean haveSameArea(A a) {
            return ax < a.bx && ay < a.by && bx > a.ax && by > a.ay;
        }
        boolean contains(int x, int y) {
            return x >= ax && y >= ay && x < bx && y < by;
        }
        boolean inside(A a) {
            return ax >= a.ax && ay >= a.ay && bx <= a.bx && by <= a.by;
        }
        A topLeft() {
            return new A(ax, (ay + by) / 2, (ax + bx) / 2, by);
        }
        A topRight() {
            return new A((ax + bx) / 2, (ay + by) / 2, bx, by);
        }
        A bottomLeft() {
            return new A(ax, ay, (ax + bx) / 2, (ay + by) / 2);
        }
        A bottomRight() {
            return new A((ax + bx) / 2, ay, bx, (ay + by) / 2);
        }

        @Override
        public String toString() {
            return "A{" +
                    "ax=" + ax +
                    ", ay=" + ay +
                    ", bx=" + bx +
                    ", by=" + by +
                    '}';
        }

        public long area() {
            return (long) (bx - ax) * (by - ay);
        }
    }
    static class Mat {
        int N;
        T root;
        Mat(int n, int m) {
            N = Integer.highestOneBit(Math.max(n, m)) << 1;
            root = new T(0);
        }
        void add(int x, int y) {
            root = add(root, new A(0, 0, N, N), x, y);
        }
        T add(T t, A a, int x, int y) {
            for (;;) {
                t.count++;
                if (a.area() == 1) break;
                if (a.topLeft().contains(x, y)) {
                    a = a.topLeft();
                    if (t.topLeft == NULL) t.topLeft = new T(0);
                    t = t.topLeft;
                } else if (a.topRight().contains(x, y)) {
                    a = a.topRight();
                    if (t.topRight == NULL) t.topRight = new T(0);
                    t = t.topRight;
                } else if (a.bottomLeft().contains(x, y)) {
                    a = a.bottomLeft();
                    if (t.bottomLeft == NULL) t.bottomLeft = new T(0);
                    t = t.bottomLeft;
                } else if (a.bottomRight().contains(x, y)) {
                    a = a.bottomRight();
                    if (t.bottomRight == NULL) t.bottomRight = new T(0);
                    t = t.bottomRight;
                } else {
                    break;
                }
            }
            return root;
        }

        int query(int x, int y) {
            return query(0, y, x, N) + query(x, 0, N, y);
        }
        int query(int ax, int ay, int bx, int by) {
            return query(root, new A(0, 0, N, N), new A(ax, ay, bx, by));
        }
        int query(T t, A ta, A qa) {
            if (t == NULL || !ta.haveSameArea(qa)) return 0;
            if (ta.inside(qa)) return t.count;
            int res = 0;
            res += query(t.topLeft, ta.topLeft(), qa);
            res += query(t.topRight, ta.topRight(), qa);
            res += query(t.bottomLeft, ta.bottomLeft(), qa);
            res += query(t.bottomRight, ta.bottomRight(), qa);
            return res;
        }
    }

}
