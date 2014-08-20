package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1008 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case #%d:%n", cs++);
            solve(n);
        }
    }

    private void solve(int n) {
        T t = NULL;
        while (n-- != 0) {
            String s = in.next();
            if (s.equals("push")) {
                int val = in.nextInt();
                int time = in.nextInt();
                T[] ss = splitKey(t, time);
                t = merge(ss[0], merge(new T(time, val, 1), ss[1]));
            } else if (s.equals("pop")) {
                int time = in.nextInt();
                T[] ss = splitKey(t, time);
                t = merge(ss[0], merge(new T(time, 0, -1), ss[1]));
            } else { // peak
                int time = in.nextInt();
                T[] ss = splitKey(t, time);
                if (ss[0].size == 0) out.println(-1);
                else out.println(get(ss[0], 0));
                t = merge(ss[0], ss[1]);
            }
        }
    }

    private int get(T s, int x) {
        if (s.right.max > x) return get(s.right, x);
        if (s.right.size == x && s.pop > 0) return s.val;
        return get(s.left, x - s.pop - s.right.size);
    }

    static class T {
        public int key, val, size, pop, max;
        public double p;
        public T left, right;

        T(int key, int val, int size, int pop, int max, double p, T left, T right) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.pop = pop;
            this.max = max;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(int key, int val, int pop) {
            this(key, val, pop, pop, pop, Math.random(), NULL, NULL);
        }

        public T change(T left, T right) {
            size = left.size + right.size + pop;
            max = Math.max(right.max, Math.max(right.size + pop, right.size + pop + left.max));
            this.left = left;
            this.right = right;
            return this;
        }

    }
    public static final T NULL = new T(0, 0, 0, 0, 0, 0, null, null);

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
