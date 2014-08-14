package main;



import com.shu_mj.collections.map.IncTreeMap;
import com.shu_mj.ds.BIT;
import com.shu_mj.ds.LongBIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private final int maxTime = (int) (1e5 + 100);

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] ss = new int[n];
        int[] ms = new int[n];
        int[] rs = new int[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.nextInt();
            ms[i] = in.nextInt();
            rs[i] = in.nextInt();
        }
        int m = in.nextInt();
        Item[] left = new Item[m];
        for (int i = 0; i < m; i++) {
            left[i] = new Item(in.nextInt(), in.nextInt() - 1, in.nextInt() - 1);
        }
        Item[] right = left.clone();
        Arrays.sort(left, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.l - o2.l;
            }
        });
        Arrays.sort(right, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.r - o2.r;
            }
        });
        long ans = 0;
        IncTreeMap<Integer> map = new IncTreeMap<Integer>();
        BIT cnt = new BIT(maxTime);
        LongBIT sum = new LongBIT(maxTime);

//        T t = null;
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            while (j < m && left[j].l == i) {
                Item it = left[j++];
                map.inc(it.t);
                if (map.get(it.t) > 1) continue;
                Integer r = map.higherKey(it.t);
                Integer l = map.lowerKey(it.t);
                if (l != null && r != null) {
//                    t = remove(t, r - l);
//                    t = insert(t, it.t - l);
//                    t = insert(t, r - it.t);
                    cnt.add(low(r - l), -1);
                    cnt.add(low(it.t - l), 1);
                    cnt.add(low(r - it.t), 1);
                    sum.add(low(r - l), -(low(r - l)));
                    sum.add(low(it.t - l), low(it.t - l));
                    sum.add(low(r - it.t), low(r - it.t));
                } else if (l != null) {
//                    t = insert(t, it.t - l);
                    cnt.add(low(it.t - l), 1);
                    sum.add(low(it.t - l), low(it.t - l));
                } else if (r != null) {
//                    t = insert(t, r - it.t);
                    cnt.add(low(r - it.t), 1);
                    sum.add(low(r - it.t), low(r - it.t));
                }
            }
            if (!map.isEmpty()) {
                int firstTime = map.firstKey();
                ans += Math.min(ms[i], firstTime * 1L * rs[i] + ss[i]);
                if (rs[i] > 0) {
                    int time = (ms[i] + rs[i] - 1) / rs[i];
//                    T[] ts = T.splitKey(t, time - 1);
//                    ans += T.sum(ts[0]) * rs[i];
//                    ans += T.size(ts[1]) * ms[i];
//                    t = T.merge(ts[0], ts[1]);
                    ans += sum.sum(0, time) * 1L * rs[i];
                    ans += cnt.sum(time, maxTime) * 1L * ms[i];
                }
            }
            while (k < m && right[k].r == i) {
                Item e = right[k++];
                map.dec(e.t);
                if (map.get(e.t) == 0) map.remove(e.t);
                else continue;
                Integer l = map.lowerKey(e.t);
                Integer r = map.higherKey(e.t);
                if (l != null && r != null) {
//                    t = insert(t, r - l);
//                    t = remove(t, r - e.t);
//                    t = remove(t, e.t - l);
                    cnt.add(low(r - l), 1);
                    cnt.add(low(e.t - l), -1);
                    cnt.add(low(r - e.t), -1);
                    sum.add(low(r - l), (low(r - l)));
                    sum.add(low(e.t - l), -(low(e.t - l)));
                    sum.add(low(r - e.t), -(low(r - e.t)));
                } else if (l != null) {
                    cnt.add(low(e.t - l), -1);
                    sum.add(low(e.t - l), -(low(e.t - l)));
//                    t = remove(t, e.t - l);
                } else if (r != null) {
                    cnt.add(low(r - e.t), -1);
                    sum.add(low(r - e.t), -(low(r - e.t)));
//                    t = remove(t, r - e.t);
                }

            }
        }
        out.println(ans);

    }

    T insert(T t, int key) {
        T[] ts = T.splitKey(t, key);
        return T.merge(T.merge(ts[0], new T(key)), ts[1]);
    }
    T remove(T t, int key) {
        T[] ts = T.splitKey(t, key);
        T[] ts0 = T.splitSize(ts[0], ts[0].size - 1);
        return T.merge(ts0[0], ts[1]);
    }
    int low(int t) {
        return Math.min(maxTime - 1, t);
    }
    class Item {
        int t;
        int l;
        int r;

        Item(int t, int l, int r) {
            this.t = t;
            this.l = l;
            this.r = r;
        }
    }
}
class T {
    public int key, size;
    public long sum;
    public double p;
    public T left, right;

    public T(int key, int size, long sum, double p, T left, T right) {
        this.key = key;
        this.size = size;
        this.sum = sum;
        this.p = p;
        this.left = left;
        this.right = right;
    }

    public T(int key) {
        this(key, 1, key, Math.random(), null, null);
    }

    static int size(T t) {
        if (t == null) return 0;
        return t.size;
    }

    static long sum(T t) {
        if (t == null) return 0;
        return t.sum;
    }
    T change(T left, T right) {
        size = 1 + size(left) + size(right);
        this.left = left;
        this.right = right;
        sum = key + sum(left) + sum(right);
        return this;
    }

    public static T[] splitSize(T t, int size) {
        if (size >= size(t)) return new T[]{t, null};
        if (size <= 0) return new T[]{null, t};
        if (t.left != null && size <= t.left.size) {
            T[] res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
            return res;
        } else {
            T[] res = splitSize(t.right, size - (t.size - t.right.size));
            res[0] = t.change(t.left, res[0]);
            return res;
        }
    }

    public static T[] splitKey(T t, int key) {
        if (t == null || key < t.key && t.left == null) return new T[]{null, t};
        if (key >= t.key && t.right == null) return new T[]{t, null};
        if (key < t.key) {
            T[] res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
            return res;
        } else {
            T[] res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
            return res;
        }
    }

    public void print(String s) {
        if (right != null) right.print(s + "      ");
        System.err.printf("%s%3d%3d%n", s, key, size);
        if (left != null) left.print(s + "      ");
        if (s.length() == 0)
            System.err.println("----------------------------------");
    }

    public static T merge(T t1, T t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        if (t1.p < t2.p)
            return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }

}
