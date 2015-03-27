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
        int[] cnt = new int[26];
        int res = 0;
        for (char c : in.next().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                cnt[c - 'a']++;
            } else {
                if (cnt[c - 'A'] > 0) cnt[c - 'A']--;
                else res++;
            }
        }
        out.println(res);
    }
}
