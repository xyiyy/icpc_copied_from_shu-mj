package test.on2014_07.on2014_07_31_DX_4.Task1005;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1005 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    final int M = (int) (1e9 + 7);
    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[][] left = new int[n][1024];
        int[][] right = new int[n][1024];
        for (int i = 0; i < n; i++) {
            if (i != 0) for (int j = 0; j < 1024; j++) left[i][j] = left[i - 1][j];
            left[i][is[i]]++;
            left[i][is[i]] %= M;
            if (i != 0) for (int j = 0; j < 1024; j++) {
                left[i][j ^ is[i]] += left[i - 1][j];
                left[i][j ^ is[i]] %= M;
            }
        }
        int[][] left2 = new int[n][1024];
        for (int i = n - 1; i >= 0; i--) {
            left2[i][is[i]] = 1;
            if (i != 0) for (int j = 0; j < 1024; j++) {
                left2[i][j ^ is[i]] += left[i - 1][j];
                left2[i][j ^ is[i]] %= M;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1) for (int j = 0; j < 1024; j++) right[i][j] = right[i + 1][j];
            right[i][is[i]]++;
            right[i][is[i]] %= M;
            if (i != n - 1) for (int j = 0; j < 1024; j++) {
                right[i][j & is[i]] += right[i + 1][j];
                right[i][j & is[i]] %= M;
            }
        }
        long res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 1024; j++) {
                res += (long) left2[i][j] * right[i + 1][j] % M;
                res %= M;
            }
        }
        out.println(res);
    }
}
