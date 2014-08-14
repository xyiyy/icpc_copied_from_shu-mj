package test.on2014_08.on2014_08_08_SHU_Link_cut_tree.TaskD;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) {
            ts[i] = new T(i);
        }
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            if (k + i < n) ts[i].pre = ts[i + k];
        }
        int m = in.nextInt();
        while (m-- != 0) {
            if (in.nextInt() == 1) {
                int j = in.nextInt();
                out.println(access(ts[j]).size);
            } else {
                int j = in.nextInt();
                int k = in.nextInt();
                T t = access(ts[j]);
                T pre = t.pre;
                T[] ss = splitSize(t, t.size - 1);
                ss[0].pre = pre;
                if (j + k < n) ss[1].pre = ts[j + k];
                else ss[1].pre = NULL;
            }
        }
    }

    final T NULL = new T(0, 0, 0, null, null, null);
    class T {
        int id;
        int size;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int size, double p, T pre, T left, T right) {
            this.id = id;
            this.size = size;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id) {
            this(id, 1, Math.random(), NULL, NULL, NULL);
        }
        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            size = left.size + right.size + 1;
            return this;
        }
    }
    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }
    T[] split(T t) {
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
    T[] splitSize(T t, int size) {
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
    void print(T t, String sp) {
        if (t == NULL) return ;
        if (t.right != NULL && t.right.pre != t) throw new RuntimeException();
        print(t.right, sp + "   ");
        out.printf("%s%3d%n", sp, t.id);
        if (t.left != NULL && t.left.pre != t) throw new RuntimeException();
        print(t.left, sp + "    ");
        if (sp.length() == 0) out.println("------------------");
    }
}
