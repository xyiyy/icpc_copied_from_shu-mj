package test.on2014_08.on2014_08_06_SHU_Link_cut_tree.TaskA;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        T[] ts = new T[n + 1];
        for (int i = 1; i <= n; i++) ts[i] = new T(i);
        while (m-- != 0) {
            char c = in.next().charAt(0);
            int u = in.nextInt();
            int v = in.nextInt();
            if (c == 'C') {
                link(ts[u], ts[v]);
            } else if (c == 'D') {
                cut(ts[u], ts[v]);
            } else {
                if (getRoot(ts[u]) == getRoot(ts[v])) out.println("Yes");
                else out.println("No");
            }
        }
    }

    class T {
        int id;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }
        T(int id) {
            this(id, false, Math.random(), NULL, NULL, NULL);
        }
        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
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
            return this;
        }
    }
    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.pushDown().change(t1.left, merge(t1.right, t2));
        return t2.pushDown().change(merge(t1, t2.left), t2.right);
    }
    T[] split(T t) {
        pushDownAllMark(t);
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

    private void pushDownAllMark(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAllMark(t.pre);
        t.pushDown();
    }

    void print(T t, String sp) {
        if (t == NULL) return ;
        t.pushDown();
        if (t.right != NULL && t.right.pre != t) throw new RuntimeException();
        print(t.right, sp + "   ");
        out.printf("%s%3d%n", sp, t.id);
        if (t.left != NULL && t.left.pre != t) throw new RuntimeException();
        print(t.left, sp + "    ");
        if (sp.length() == 0) out.println("------------------");
    }
    T NULL = new T(0);

}
