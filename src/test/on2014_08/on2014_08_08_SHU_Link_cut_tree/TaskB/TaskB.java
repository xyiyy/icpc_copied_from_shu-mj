package test.on2014_08.on2014_08_08_SHU_Link_cut_tree.TaskB;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    class V extends ArrayList<Integer> {

    }
    void run() {
        int n = in.nextInt();
        int q = in.nextInt();
        T[] ts = new T[n + 1];
        for (int i = 1; i <= n; i++) ts[i] = new T(i, 1);
        V[] vs = new V[n + 1];
        for (int i = 1; i <= n; i++) vs[i] = new V();

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(v);
            vs[v].add(u);
        }
        dfs(vs, ts);
        while (q-- != 0) {
            char c = in.next().charAt(0);
            int u = in.nextInt();
            int v = in.nextInt();
            if (c == '+') {
                int d = in.nextInt();
                makeRoot(ts[u]);
                access(ts[v]).setDelta(1, d);
            } else if (c == '-') {
                int u2 = in.nextInt();
                int v2 = in.nextInt();
                cut(ts[u], ts[v]);
                link(ts[u2], ts[v2]);
            } else if (c == '*') {
                int d = in.nextInt();
                makeRoot(ts[u]);
                access(ts[v]).setDelta(d, 0);
            } else {
                makeRoot(ts[u]);
                out.println(access(ts[v]).sum);
            }
        }
    }

    private void dfs(V[] vs, T[] ts) {
        int n = vs.length;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        vis[1] = true;
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int u : vs[crt]) if (!vis[u]) {
                vis[u] = true;
                ts[u].pre = ts[crt];
                que.add(u);
            }
        }
    }

    T NULL = new T(0, 0, 0, 0, 1, 0, false, 0, null, null, null);

    final int M = 51061;
    class T {
        int id;
        int val;
        int size;
        int sum;
        int mul;
        int add;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int val, int size, int sum, int mul, int add, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.val = val;
            this.size = size;
            this.sum = sum;
            this.mul = mul;
            this.add = add;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, int val) {
            this(id, val, 1, val, 1, 0, false, Math.random(), NULL, NULL, NULL);
        }
        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            this.size = left.size + right.size + 1;
            this.sum = left.sum + right.sum + val;
            this.sum %= M;
            return this;
        }
        T setDelta(int m, int a) {
            if (this == NULL) return NULL;
            val = (int) ((long) val * m % M + a) % M;
            sum = (int) ((long) sum * m % M + (long) a * size % M) % M;
            mul = (int) ((long) mul * m % M) % M;
            add = (int) ((long) add * m % M + a) % M;
            return this;
        }
        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            T t = left; left = right; right = t;
            return this;
        }
        T pushDown() {
            if (rev) {
                left.setRev();
                right.setRev();
                rev ^= true;
            }
            if (mul != 1 || add != 0) {
                left.setDelta(mul, add);
                right.setDelta(mul, add);
                mul = 1;
                add = 0;
            }
            return this;
        }
    }

    T[] split(T t) {
        pushDownAll(t);
        T[] res = new T[2];
        res[1] = t.right;
        res[0] = t.change(t.left, NULL);
        T tcp = t;
        for (;;) {
            if (t.pre.left == t) {
                t = t.pre;
                res[1] = t.change(res[1], t.right);
            } else if (t.pre.right == t) {
                t = t.pre;
                res[0] = t.change(t.left, res[0]);
            } else {
                res[0].pre = t.pre;
                res[1].pre = tcp;
                return res;
            }
        }
    }
    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.pushDown().change(t1.left, merge(t1.right, t2));
        return t2.pushDown().change(merge(t1, t2.left), t2.right);
    }

    T access(T t) {
        T last = NULL;
        while (t != NULL) {
            T[] ss = split(t);
            t = ss[0].pre;
            last = merge(ss[0], last);
        }
        last.pre = NULL;
        return last;
    }
    T makeRoot(T t) {
        return access(t).setRev();
    }
    T getRoot(T t) {
        t = access(t);
        while (t.pushDown().left != NULL) t = t.left;
        return t;
    }
    void link(T x, T y) {
        makeRoot(x).pre = y;
    }

    void cut(T x, T y) {
        makeRoot(y);
        access(y);
        while (x.pre.left == x || x.pre.right == x) x = x.pre;
        x.pre = NULL;
    }

    private void pushDownAll(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAll(t.pre);
        t.pushDown();
    }

    void print(T t, String sp) {
        if (t == NULL) return ;
        t.pushDown();
        if (t.right != NULL && t.right.pre != t) throw new RuntimeException();
        print(t.right, sp + "      ");
        out.printf("%s%3d%3d%n", sp, t.id, t.val);
        if (t.left != NULL && t.left.pre != t) throw new RuntimeException();
        print(t.left, sp + "       ");
        if (sp.length() == 0) out.println("------------------");
    }


}
