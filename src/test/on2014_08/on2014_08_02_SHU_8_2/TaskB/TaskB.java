package test.on2014_08.on2014_08_02_SHU_8_2.TaskB;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[][] iss = new int[n][];
        for (int i = 0; i < n; i++) {
            iss[i] = in.nextIntArray(i + 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                iss[i][j] += Math.max(iss[i + 1][j], iss[i + 1][j + 1]);
            }
        }
        out.println(iss[0][0]);
    }
}
