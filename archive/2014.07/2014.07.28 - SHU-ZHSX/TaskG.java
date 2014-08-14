package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        boolean[][] mat = new boolean[101][101];
        while (t-- != 0) {
            char c = in.next().charAt(0);
            int x = in.nextInt();
            int y = in.nextInt();
            int l = in.nextInt();
            if (c == 'B') {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        mat[x + i][y + j] = true;
                    }
                }
            } else if (c == 'W') {
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        mat[x + i][y + j] = false;
                    }
                }
            } else {
                int cnt = 0;
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < l; j++) {
                        if (mat[x + i][y + j]) cnt++;
                    }
                }
                out.println(cnt);
            }
        }
    }
}
