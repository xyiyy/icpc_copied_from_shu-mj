package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {
    @TestCase
    public Collection<Test> createTests() {
        List<Test> res = new ArrayList<Test>();
        for (int i = 0; i < 0; i++) {
            res.add(gen());
        }
        return res;
    }

    Random r = new Random();
    String line = System.lineSeparator();
    private Test gen() {
//        int n = r.nextInt(100000) + 1;
//        int q = r.nextInt(100000) + 1;
        int n = 100000, q = 100000;
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        input.append(n + " " + q + line);
        T t = NULL;
        for (int i = 1; i <= n; i++) t = merge(t, new T(i, 1));
        V[] vs = new V[n + 1];
        for (int i = 1; i <= n; i++) vs[i] = new V();
        dfs(t, vs, input);
        while (q-- != 0) {
            char c = "+*/".charAt(r.nextInt(3));
            int u = r.nextInt(n) + 1;
            int v = r.nextInt(n) + 1;
            int d = r.nextInt(10001);
            input.append(c + " " + u + " " + v + (c != '/' ? (" " + d) : "") + line);
//            output.append(solve(n, vs, c, u, v, d));
//            if (c == '/') output.append(line);
        }
        return new Test(input.toString());
    }

    private String solve(int n, V[] vs, char c, int u, int v, int d) {
        if (c == '+') {
            dfs(vs, u, 0, v, 1, d);
            return "";
        } else if (c == '*') {
            dfs(vs, u, 0, v, d, 0);
            return "";
        } else {
            sum = 0;
            dfs(vs, u, 0, v, 1, 0);
            return "" + sum;
        }
    }
    long sum;
    final long M = 51061;
    boolean dfs(V[] vs, int v, int fa, int des, int m, int a) {
        if (v == des) {
            vs[v].val = vs[v].val * m % M + a;
            vs[v].val %= M;
            sum += vs[v].val;
            sum %= M;
            return true;
        }
        for (int u : vs[v]) if (u != fa) {
            if (dfs(vs, u, v, des, m, a)) {
                vs[v].val = vs[v].val * m % M + a;
                vs[v].val %= M;
                sum += vs[v].val;
                sum %= M;
                return true;
            }
        }
        return false;
    }

    private void dfs(T t, V[] vs, StringBuilder s) {
        int v = t.id;
        if (t.left != NULL) {
            s.append(v + " " + t.left.id + line);
            vs[v].add(t.left.id);
            vs[t.left.id].add(v);
            dfs(t.left, vs, s);
        }
        if (t.right != NULL) {
            s.append(v + " " + t.right.id + line);
            vs[v].add(t.right.id);
            vs[t.right.id].add(v);
            dfs(t.right, vs, s);
        }
    }

    class V extends ArrayList<Integer> {
        long val = 1;
    }

    class T {
        int id;
        int val;
        double p;
        T left;
        T right;

        T(int id, int val, double p, T left, T right) {
            this.id = id;
            this.val = val;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(int id, int val) {
            this(id, val, Math.random(), NULL, NULL);
        }
        T change(T left, T right) {
            this.left = left;
            this.right = right;
            return this;
        }
    }
    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }
    T NULL = new T(0, 0, 0, null, null);
}
