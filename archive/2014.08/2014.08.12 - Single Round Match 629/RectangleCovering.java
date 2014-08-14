package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class RectangleCovering {
    public int minimumNumber(int holeH, int holeW, int[] boardH, int[] boardW) {
        int res = Integer.MAX_VALUE;
        res = Math.min(res, work(holeH, holeW, boardH, boardW));
        res = Math.min(res, work(holeW, holeH, boardH, boardW));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int work(int holeH, int holeW, int[] boardH, int[] boardW) {
        int n = boardH.length;
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            int w = 0;
            if (boardH[i] > holeH) w = Math.max(w, boardW[i]);
            if (boardW[i] > holeH) w = Math.max(w, boardH[i]);
            for (int j = 0; j <= i; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + w);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = n; i >= 0; i--) if (dp[n][i] >= holeW) res = i;
        return res;
    }
}
