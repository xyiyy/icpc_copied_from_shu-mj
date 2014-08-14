package com.shu_mj.ds;

/**
 * Created by Jun on 6/6/2014.
 */
public class T {
    public int key, size;
    public double p;
    public T left, right;

    public T(int key, int size, double p, T left, T right) {
        this.key = key;
        this.size = size;
        this.p = p;
        this.left = left;
        this.right = right;
    }

    public T(int key) {
        this(key, 1, Math.random(), NULL, NULL);
    }

    public static final T NULL = new T(0, 0, 0, null, null);

    public T change(T left, T right) {
        size = left.size + right.size + 1;
        this.left = left;
        this.right = right;
        return this;
    }

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
