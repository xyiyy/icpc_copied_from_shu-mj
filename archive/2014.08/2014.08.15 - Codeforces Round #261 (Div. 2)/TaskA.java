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
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dx != 0 && dy != 0) {
            if (dx != dy && dx != -dy) out.println(-1);
            else out.println(x1 + " " + y2 + " " + x2 + " " + y1);
        } else if (dx != 0) {
            out.println(x1 + " " + (y1 + dx) + " " + x2 + " " + (y2 + dx));
        } else {
            out.println((x1 + dy) + " " + y1 + " " + (x2 + dy) + " " + y2);
        }
    }
}
