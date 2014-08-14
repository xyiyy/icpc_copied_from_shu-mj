package main;

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
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            if (i != 0) out.println();
            out.printf("Case %d:%n", i + 1);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Item ans = null;
        Item crt = null;
        for (int i = 0; i < n; i++) {
            crt = merge(add(crt, new Item(is[i], i, i)), new Item(is[i], i, i));
            ans = merge(ans, crt);
        }
        out.println(ans.val + " " + (ans.l + 1) + " " + (ans.r + 1));
    }

    private Item merge(Item a, Item b) {
        if (a == null) return b;
        if (b == null) return a;
        if (a.val != b.val) return a.val < b.val ? b : a;
        if (a.l != b.l) return a.l < b.l ? a : b;
        return a.r < b.r ? a : b;
    }

    private Item add(Item a, Item b) {
        if (a == null) return b;
        if (b == null) return a;
        return new Item(a.val + b.val, a.l, b.r);
    }

    class Item {
        int val;
        int l;
        int r;

        Item(int val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
}
