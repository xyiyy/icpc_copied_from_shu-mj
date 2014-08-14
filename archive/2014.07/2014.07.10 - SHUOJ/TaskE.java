package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int time77 = 0;
        int timeCC = 0;
        int count77 = 0;
        int countCC = 0;
        int lastTime = -1;
        for (int i = 0; i < n; i++) {
            boolean CC = in.next().charAt(0) == 'C';
            String s = in.next();
            int time = Integer.parseInt(s.substring(0, 2)) * 3600 + Integer.parseInt(s.substring(3, 5)) * 60 + Integer.parseInt(s.substring(6, 8));
            if (i == 0) {

            } else {
                if (CC) timeCC += time - lastTime;
                else time77 += time - lastTime;
            }
            if (CC) countCC++;
            else count77++;
            lastTime = time;
        }
        out.println(time77 + " " + timeCC + " " + count77 + " " + countCC + " " + (fit(time77, timeCC, count77, countCC) ? "happy" : "unhappy"));
    }

    private boolean fit(int time77, int timeCC, int count77, int countCC) {
        return time77 * 2 >= timeCC && timeCC * 2 >= time77 && count77 * 2 >= countCC && countCC * 2 >= count77;
    }
}
