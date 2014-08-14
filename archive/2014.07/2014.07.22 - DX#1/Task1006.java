package main;





import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1006 {
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
        int x = in.nextInt();
        int p = in.nextInt();
        P[] ls = new P[n];
        for (int i = 0; i < n; i++) {
            ls[i] = new P(in.nextInt(), in.nextInt(), in.nextInt());
        }
        P[] rs = ls.clone();
        Arrays.sort(ls, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.l - o2.l;
            }
        });
        Arrays.sort(rs, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.r - o2.r;
            }
        });

        T[] ts = new T[x + 1];
        T t = NULL;
        for (int i = 1, j = 0, k = 0; i <= x; i++) {
            while (j < n && ls[j].l == i) {
                t = insert(t, ls[j].d);
                j++;
            }
            ts[i] = t;
//            print(t, "");
            while (k < n && rs[k].r == i) {
                t = remove(t, rs[k].d);
                k++;
            }
        }
        long pre = 1;
        for (int i = 0; i < m; i++) {
            int xi = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int k = (int) ((a * pre + b) % c);
            long ans = get(ts[xi], k);
            if (pre > p) ans *= 2;
            out.println(ans);
            pre = ans;
        }
    }
    long get(T t, int d) {
        if (t.size <= d) return t.sum;
        return getSum(t, d);
    }
    T rem(T t, int key) {
        if (t == NULL) return NULL;
        if (key < t.key) return t.change(rem(t.left, key), t.right);
        if (key > t.key) return t.change(t.left, rem(t.right, key));
        if (t.val == 1) return merge(t.left, t.right);
        return new T(t.key, t.val - 1, t.size - 1, t.sum - key, t.p, t.left, t.right);
    }
    T put(T t, int key) {
        if (t == NULL) return new T(key);
        if (key < t.key) return t.change(put(t.left, key), t.right).normal();
        if (key > t.key) return t.change(t.left, put(t.right, key)).normal();
        return new T(key, t.val + 1, t.size + 1, t.sum + key, t.p, t.left, t.right);
    }
    long getSum(T t, int size) {
        if (size <= 0) return 0;
        if (size < t.left.size) return getSum(t.left, size);
        if (size <= t.size - t.right.size) return t.left.sum + (long) t.key * (size - t.left.size);
        return t.left.sum + (long) t.key * t.val + getSum(t.right, size - t.left.size - t.val);
    }
    T insert(T t, int key) {
        T[] ts = splitKey(t, key);
        return merge(ts[0], merge(new T(key), ts[1]));
    }
    T remove(T t, int key) {
        T[] ts = splitKey(t, key);
        T[] ts0 = splitSize(ts[0], ts[0].size - 1);
        if (ts0[1].val == 1) return merge(ts0[0], ts[1]);
        int val = ts0[1].val - 1;
        return merge(ts0[0], merge(new T(key, val, val, (long) key * val, ts0[1].p, ts0[1].left, ts0[1].right), ts[1]));
    }
    class P {
        int l;
        int r;
        int d;

        P(int l, int r, int d) {
            this.l = l;
            this.r = r;
            this.d = d;
        }
    }

    static class T {
        public final int key, val, size;
        public final long sum;
        public final double p;
        public final T left, right;

        public T(int key, int val, int size, long sum, double p, T left, T right) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.sum = sum;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        public T(int key) {
            this(key, 1, 1, key, Math.random(), NULL, NULL);
        }

        public T change(T left, T right) {
            return new T(key, val, left.size + right.size + val, left.sum + right.sum + (long) key * val, p, left, right);
        }
        T normal() {
            if (left != NULL && left.p < p && (right == NULL || left.p < right.p)) {
                return left.change(left.left, change(left.right, right));
            } else if (right != NULL && right.p < p) {
                return right.change(change(left, right.left), right.right);
            }
            return this;
        }
    }

    public static final T NULL = new T(0, 0, 0, 0, 0, null, null);

    public static T[] splitSize(T t, int size) {
        T[] res;
        if (size <= 0) {
            res = new T[] { NULL, t };
        } else if (size <= t.left.size) {
            res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitSize(t.right, size - t.left.size - 1);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    public static T[] splitKey(T t, int key) {
        T[] res;
        if (t == NULL) {
            res = new T[] { NULL, NULL };
        } else if (key < t.key) {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    public void print(T t, String indent) {
        if (t != NULL) {
            print(t.right, indent + "      ");
            out.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            out.println("----------------------------------");
    }

    public static T merge(T t1, T t2) {
        if (t1 == NULL)
            return t2;
        if (t2 == NULL)
            return t1;
        if (t1.p < t2.p)
            return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }


}
