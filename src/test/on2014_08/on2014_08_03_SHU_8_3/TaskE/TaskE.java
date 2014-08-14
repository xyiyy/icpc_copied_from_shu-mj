package test.on2014_08.on2014_08_03_SHU_8_3.TaskE;



import com.shu_mj.math.Matrix;
import com.shu_mj.math.Num;
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
        while (in.hasNext()) {
            int c = in.nextInt();
            if (c == 0) break;
            int n = in.nextInt();
            int m = in.nextInt();
            solve(c, n, m);
        }
    }

    private void solve(int c, int n, int m) {
        if (m < 0 || m > c) { out.println("0.000"); return ; }
        double[][] mat = new double[c + 1][c + 1];
        for (int j = 0; j <= c; j++) {
            if (j > 0) mat[j - 1][j] = (c - j + 1.0) / c;
            if (j < c) mat[j + 1][j] = (j + 1.0) / c;
        }
        double[][] ini = new double[c + 1][c + 1];
        ini[0][0] = 1;
        mat = Matrix.pow(mat, n);
        out.printf("%.3f%n", Matrix.mul(ini, mat)[0][m]);
    }
}
