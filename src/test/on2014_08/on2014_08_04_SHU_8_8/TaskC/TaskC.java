package test.on2014_08.on2014_08_04_SHU_8_8.TaskC;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        // dp[位置][是否有 1 或 2][是否有连续的 0 3 或 3 0][末尾数]
        long[][][][] dp = new long[32][2][2][4];
        for (int i = 1; i <= 31; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 1) dp[i][j == 1 || j == 2 ? 1 : 0][0][j] = 1;
                else {
                    for (int x = 0; x < 2; x++) {
                        for (int y = 0; y < 2; y++) {
                            for (int z = 0; z < 4; z++) {
                                if (j == 0 || j == 3) {
                                    dp[i][x][y == 1 || j + z == 3 ? 1 : 0][j] += dp[i - 1][x][y][z];
                                } else {
                                    dp[i][1][y][j] += dp[i - 1][x][y][z];
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 2; i <= 31; i++) {
            out.println("N=" + i + ": " + Algo.sum(dp[i][1][1]));
        }
    }
}
