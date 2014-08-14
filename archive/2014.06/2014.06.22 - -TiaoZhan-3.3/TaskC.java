package main;

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
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        final int B = 1000;
        int bs = (n + B - 1) / B;
        int[][] bucket = new int[bs][];

        for (int i = 0; i < bs; i++) {
            int len = i == bs - 1 ? n - B * (bs - 1): B;
            bucket[i] = new int[len];
            for (int j = 0; j < len; j++) {
                bucket[i][j] = is[i * B + j];
            }
            Arrays.sort(bucket[i]);
        }
        int[] nums = is.clone();
        Arrays.sort(nums);
//        nums = Algo.unique(nums);

        while (m-- != 0) {
            int s = in.nextInt() - 1, t = in.nextInt(), v = in.nextInt();
            int l = 0, r = nums.length;
            while (l < r) {
                int mid = (l + r) / 2;
                if (count(bucket, s, t, B, is, nums[mid]) < v) l = mid + 1;
                else r = mid;
            }
            out.println(nums[l]);
        }
    }

    private int count(int[][] bucket, int l, int r, int B, int[] is, int num) {
        int res = 0;
        while (l < r && l % B != 0) if (is[l++] <= num) res++;
        while (l < r && r % B != 0) if (is[--r] <= num) res++;
        for (int i = l / B; i < r / B; i++) {
            res += Algo.upperBound(bucket[i], num);
        }
        return res;
    }
}
