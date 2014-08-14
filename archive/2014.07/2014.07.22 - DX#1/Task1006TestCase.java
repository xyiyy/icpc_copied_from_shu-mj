package main;





import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task1006TestCase {

    private int n;
    private int m;
    private int x;
    private int p;
    private int[] xs;
    private int[] as;
    private int[] bs;
    private int[] cs;
    private int[] ls;
    private int[] rs;
    private int[] ds;

    @TestCase
    public Collection<Test> createTests() {
//        List<Test> list = new ArrayList<Test>();
//        n = 4;
//        m = 3;
//        x = 5;
//        p = 8;
//        ls = new int[] {1, 2, 2, 1};
//        rs = new int[] {2, 3, 4, 5};
//        ds = new int[] {6, 3, 7, 2};
//        xs = new int[] {2, 3, 4};
//        as = new int[] {2, 1, 2};
//        bs = new int[] {1, 1, 3};
//        cs = new int[] {5, 10, 7};
//        String input = "4 3 5 8\n" +
//                "1 2 6\n" +
//                "2 3 3\n" +
//                "2 4 7\n" +
//                "1 5 2\n" +
//                "2 2 1 5\n" +
//                "3 1 1 10\n" +
//                "4 2 3 7\n" +
//                "\n";
//        String output = solve();
//        list.add(new Test(input, output));
//        for (int i = 0; i < 1; i++) {
//            String[] ss = gen(true);
////            if (i < 5) list.add(new Test(ss[0], ss[1]));
//            list.add(new Test(ss[0]));
//        }
//        return list;
        return Collections.EMPTY_LIST;
    }
    String[] gen(boolean f) {
        Random r = new Random();
        n = f ? r.nextInt(1000) + 1 : 100000;
        m = f ? r.nextInt(1000) + 1 : 100000;
        x = r.nextInt(100000) + 1;
        p = r.nextInt(10000001);
        StringBuilder sb = new StringBuilder();
        sb.append(n + " " + m + " " + x + " " + p);
        sb.append(System.lineSeparator());
        ls = new int[n];
        rs = new int[n];
        ds = new int[n];
        for (int i = 0; i < n; i++) {
            ls[i] = r.nextInt(x) + 1;
            rs[i] = r.nextInt(x) + 1;
            if (ls[i] > rs[i]) {int t = ls[i]; ls[i] = rs[i]; rs[i] = t;}
            ds[i] = f ? r.nextInt(10000000) + 1 : 10000000;
            sb.append(ls[i] + " " + rs[i] + " " + ds[i] + System.lineSeparator());
        }
        xs = new int[m];
        as = new int[m];
        bs = new int[m];
        cs = new int[m];
        for (int i = 0; i < m; i++) {
            xs[i] = r.nextInt(x) + 1;
            as[i] = r.nextInt(n + 1);
            bs[i] = r.nextInt(n + 1);
            cs[i] = r.nextInt(10000000) + 1;
            sb.append(xs[i] + " " + as[i] + " " + bs[i] + " " + cs[i] + System.lineSeparator());
        }
        if (f) return new String[] {sb.toString()};
        return new String[] {sb.toString(), solve()};
    }
    String solve() {
        StringBuilder sb = new StringBuilder();
        long pre = 1;
        for (int i = 0; i < m; i++) {
            int xi = xs[i];
            int a = as[i];
            int b = bs[i];
            int c = cs[i];
            int k = (int) ((pre * a + b) % c);
            long res = 0;
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                if (ls[j] <= xi && rs[j] >= xi) {
                    list.add(ds[j]);
                }
            }
            Collections.sort(list);
            for (int j = 0; j < k && j < list.size(); j++) {
                res += list.get(j);
            }
            if (pre > p) res *= 2;
            sb.append(res + System.lineSeparator());
            pre = res;
        }
        return sb.toString();
    }
}
