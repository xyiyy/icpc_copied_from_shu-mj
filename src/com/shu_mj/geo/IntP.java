package com.shu_mj.geo;

/**
 * Created by Jun on 6/7/2014.
 */

import java.util.Arrays;

public class IntP implements Comparable<IntP> {
    final int x, y;

    public IntP(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntP sub(IntP p) {
        return new IntP(x - p.x, y - p.y);
    }

    public IntP add(IntP p) {
        return new IntP(x + p.x, y + p.y);
    }

    public IntP mul(int k) {
        return new IntP(x * k, y * k);
    }

    public long det(IntP p) {
        return (long) x * p.y - (long) y * p.x;
    }

    public long dot(IntP p) {
        return (long) x * p.x + (long) y * p.y;
    }

    public long abs2() {
        return dot(this);
    }

    public IntP rot90() {
        return new IntP(-y, x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return compareTo((IntP) obj) == 0;
    }

    @Override
    public int compareTo(IntP p) {
        int b = x - p.x;
        if (b != 0) return b;
        return y - p.y;
    }

    //线段相交判定
    public static boolean crsSS(IntP p1, IntP p2, IntP q1, IntP q2) {
        if (Math.max(p1.x, p2.x) < Math.min(q1.x, q2.x)) return false;
        if (Math.max(q1.x, q2.x) < Math.min(p1.x, p2.x)) return false;
        if (Math.max(p1.y, p2.y) < Math.min(q1.y, q2.y)) return false;
        if (Math.max(q1.y, q2.y) < Math.min(p1.y, p2.y)) return false;
        return p2.sub(p1).det(q1.sub(p1)) * p2.sub(p1).det(q2.sub(p1)) <= 0
                && q2.sub(q1).det(p1.sub(q1)) * q2.sub(q1).det(p2.sub(q1)) <= 0;
    }

    //直线和线段的相交判定
    public static boolean crsLS(IntP l1, IntP l2, IntP s1, IntP s2) {
        return s1.sub(l2).det(l1.sub(l2)) * s2.sub(s1).det(l1.sub(l2)) <= 0;
    }

    //直线相交判定
    //返回-1表示重合，为0表示平行，为1表示相交
    public static int crsLL(IntP p1, IntP p2, IntP q1, IntP q2) {
        if (p1.sub(p2).det(q1.sub(q2)) != 0) return 1;
        if (p1.sub(q2).det(q1.sub(p2)) != 0) return 0;
        return -1;
    }

    //计算多边形的面积的二倍！
    //点不需要有顺序
    public static long area2(IntP[] ps) {
        long res = 0;
        for (int i = 0; i < ps.length; i++) {
            res += ps[i].det(ps[(i + 1) % ps.length]);
        }
        return Math.abs(res);
    }

    //凸包
    //逆时针 不包含线上的点
    //如果需要包含先上的点 将 <= 0 改成 < 0
    //但是需要注意此时不能有重点
    public static IntP[] convexHull(IntP[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        Arrays.sort(ps);
        IntP[] qs = new IntP[n * 2];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
        }
        for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
            while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) <= 0) k--;
        }
        IntP[] res = new IntP[k - 1];
        System.arraycopy(qs, 0, res, 0, k - 1);
        return res;
    }

    //点在多边形内外的判定
    //内部返回1，边上返回0，外部返回-1
    public static int contains(IntP[] ps, IntP q) {
        int n = ps.length;
        int res = -1;
        for (int i = 0; i < n; i++) {
            IntP a = ps[i].sub(q), b = ps[(i + 1) % n].sub(q);
            if (a.y > b.y) {
                IntP t = a;
                a = b;
                b = t;
            }
            if (a.y <= 0 && b.y >= 0 && a.det(b) > 0) {
                res = -res;
            }
            if (Math.abs(a.det(b)) <= 0 && a.dot(b) <= 0) return 0;
        }
        return res;
    }

    //凸多边形的直径
    //凸多边形上最远点的距离的平方
    //O(n)
    public static long convexDiameter2(IntP[] ps) {
        int n = ps.length;
        int is = 0, js = 0;
        for (int i = 1; i < n; i++) {
            if (ps[i].x > ps[is].x) is = i;
            if (ps[i].x < ps[js].x) js = i;
        }
        long maxd = ps[is].sub(ps[js]).abs2();
        int i = is, j = js;
        do {
            if (ps[(i + 1) % n].sub(ps[i]).det(ps[(j + 1) % n].sub(ps[j])) >= 0) {
                j = (j + 1) % n;
            } else {
                i = (i + 1) % n;
            }
            maxd = Math.max(maxd, ps[i].sub(ps[j]).abs2());
        } while (i != is || j != js);
        return maxd;
    }
}
