package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        double len = in.nextDouble();
        double v = in.nextDouble();
        boolean[] dir = new boolean[n];
        double[] pos = new double[n];
        String[] name = new String[n];
        double time = 0;
        for (int i = 0; i < n; i++) {
            char c = in.next().charAt(0);
            dir[i] = c == 'p' || c == 'P';
            pos[i] = in.nextDouble();
            name[i] = in.next();
            time = Math.max(time, dir[i] ? (len - pos[i]) / v : pos[i] / v);
        }
        for (int i = 0; i < n; i++) {
            pos[i] = pos[i] + v * time * (dir[i] ? 1 : -1);
        }
        Arrays.sort(pos);
        for (int i = 0; i < n; i++) {
            if (eq(pos[i], 0) || eq(pos[i], len)) {
                out.printf("%13.2f %s%n", (int) (time * 100) / 100.0, name[i]);
                return ;
            }
        }
    }

    private boolean eq(double a, double b) {
        return Math.abs(a - b) < 1e-6;
    }
}
