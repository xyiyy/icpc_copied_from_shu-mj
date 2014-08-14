package main;

import com.shu_mj.tpl.Algo;

import java.util.Arrays;

public class BuildingHeights {
    public int minimum(int[] hs) {
        int n = hs.length;
        Arrays.sort(hs);
        int[] ans = new int[n + 1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int crtMax = hs[i];
            int crtSum = 0;
            for (int j = i; j >= 0; j--) {
                crtSum += crtMax - hs[j];
                int tmpMax = Math.max(crtMax, hs[j]);
                crtSum += (tmpMax - crtMax) * (i - j + 1);
                crtMax = tmpMax;
                ans[i - j + 1] = Math.min(ans[i - j + 1], crtSum);
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) res ^= ans[i];
        return res;
    }
}
