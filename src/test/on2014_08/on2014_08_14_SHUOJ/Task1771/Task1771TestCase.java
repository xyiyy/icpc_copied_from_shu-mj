package test.on2014_08.on2014_08_14_SHUOJ.Task1771;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task1771TestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 10;
        input.append(n + ln);
        int bound = 100000;
        int[] ms = new int[bound];
        for (int i = 0; i < bound; i++) {
            ms[i] = f(i);
        }
        for (int i = 0; i < n; i++) {
            int l = in.nextInt(bound);
            int r = in.nextInt(bound);
            if (l > r) {int t = l; l = r; r = t;}
            int m = in.nextInt(200) - 100;
            input.append(l + " " + r + " " + m + ln);
            output.append("Case " + (i + 1) + ":" + ln + num(ms, l, r, m) + " " + sum(ms, l, r, m) + ln);
        }
        return new Test(input.toString(), output.toString());
    }

    private int sum(int[] ms, int l, int r, int m) {
        long s = 0;
        for (int i = l; i <= r; i++) {
            if (ms[i] == m) s += i;
        }
        return (int) (s % (int) (1e8 + 7));
    }

    private int num(int[] ms, int l, int r, int m) {
        int c = 0;
        for (int i = l; i <= r; i++) {
            if (ms[i] == m) c++;
        }
        return c;
    }

    private int f(int x) {
        int r = 0;
        for (char c : Integer.toString(x).toCharArray()) {
            r += g(c - '0') * (c - '0');
        }
        return r;
    }

    private int g(int x) {
        return (x & 1) != 0 ? -1 : 1;
    }
}
