package test.on2014_08.on2014_08_11_MemSQL_Start_c_UP_2_0___Round_2___Online_Round.A___Golden_System;



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
        char[] acs = in.next().toCharArray();
        char[] bcs = in.next().toCharArray();
        int n = Math.max(acs.length, bcs.length);
        if (acs.length != n) acs = sameLen(acs, n);
        if (bcs.length != n) bcs = sameLen(bcs, n);
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = acs[i] - '0';
            b[i] = bcs[i] - '0';
        }
        if (lower(a.clone(), b.clone())) out.println('<');
        else if (lower(b, a)) out.println('>');
        else out.println('=');
    }

    private boolean lower(int[] a, int[] b) {
        int n = a.length;
        for (int i = 0; i < n; i++) if (a[i] != b[i]) {
            if (a[i] == 1) {
                if (!dfs(b, i)) return false;
            } else {
                if (!dfs(a, i)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int[] a, int i) {
        int n = a.length;
        if (i >= n) return false;
        if (a[i] == 1) {
            a[i] = 0;
            return true;
        }
        if (i + 1 >= n || a[i + 1] == 0) return false;
        if (dfs(a, i + 1) && dfs(a, i + 2)) {
            a[i + 1] = a[i + 2] = 0;
            return true;
        }
        return false;
    }

    private char[] sameLen(char[] a, int n) {
        char[] cs = new char[n];
        for (int i = n - 1, j = a.length - 1; i >= 0; i--, j--) {
            if (j >= 0) cs[i] = a[j];
            else cs[i] = '0';
        }
        return cs;
    }
}
