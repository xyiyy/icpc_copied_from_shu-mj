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
        int n = in.nextInt();
        char[] cs = in.next().toCharArray();
        for (int i = 0; i < n; i++) if (cs[i] == '*') {
            for (int j = i + 1; j < n; j++) if (cs[j] == '*') {
                int k = j + j - i;
                int l = k + j - i;
                int m = l + j - i;
                if (m < n && cs[k] == '*' && cs[l] == '*' && cs[m] == '*') {
                    out.println("yes");
                    return ;
                }
            }
        }
        out.println("no");
    }
}
