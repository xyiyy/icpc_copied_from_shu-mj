package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int l = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = new int[n + 2];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt();
        }
        is[n] = 0; is[n + 1] = l;
        Arrays.sort(is);
        int s = 0, t = Integer.MAX_VALUE / 2;
        while (s < t) {
            int mid = (s + t) / 2;
            if (delNum(is, mid) <= m) s = mid + 1;
            else t = mid;
        }
        s--;
        out.println(s);
    }

    private int delNum(int[] is, int m) {
        int last = 0;
        int del = 0;
        for (int i = 1; i < is.length; i++) {
            if (is[i] - last < m) {
                del++;
            } else {
                last = is[i];
            }
        }
        return del;
    }
}
