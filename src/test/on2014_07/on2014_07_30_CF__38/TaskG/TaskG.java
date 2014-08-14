package test.on2014_07.on2014_07_30_CF__38.TaskG;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        T t = NULL;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int c = in.nextInt();
            if (i == 0) t = new T(0, a);
            else {
                T[] ts = splitKey(t, a);
                T[] ts1 = splitSize(ts[1], ts[1].size - c);
                t = merge(ts[0], merge(ts1[0], merge(new T(i, a), ts1[1])));
            }
        }
        print(t);
        out.println();
    }

    private void print(T t) {
        if (t == NULL) return ;
        print(t.left);
        out.print((t.id + 1) + " ");
        print(t.right);
    }


    static class T {
        public int id, key, val, size;
        public double p;
        public T left, right;

        T(int id, int key, int val, int size, double p, T left, T right) {
            this.id = id;
            this.key = key;
            this.val = val;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        public T(int id, int key) {
            this(id, key, key, 1, Math.random(), NULL, NULL);
        }

        public T change(T left, T right) {
            size = left.size + right.size + 1;
            key = Math.max(val, Math.max(left.key, right.key));
            this.left = left;
            this.right = right;
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
        } else if (key > t.key) {
            res = new T[] { NULL, t };
        } else if (key < t.val || t.left == NULL || (t.right != NULL && t.right.key > key)) {
            res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
        } else {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        }
        return res;
    }

    public static void print(T t, String indent) {
        if (t != NULL) {
            print(t.right, indent + "      ");
            System.err.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            System.err.println("----------------------------------");
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
