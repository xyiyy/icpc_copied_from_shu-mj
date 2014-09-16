package com.shu_mj.ds;

/**
 * Created by Jun on 9/16/2014.
 */
public class Palindrome {
    public static int[] palindrome(char[] cs) {
        int n = cs.length;
        int[] len = new int[n * 2];
        for (int i = 0, j = 0, k; i < n * 2; i += k, j = Math.max(j - k, 0)) {
            while (i - j >= 0 && i + j + 1 < n * 2
                    && cs[(i - j) / 2] == cs[(i + j + 1) / 2]) j++;
            len[i] = j;
            for (k = 1; i - k >= 0 && j - k >= 0 && len[i - k] != j - k; k++) {
                len[i + k] = Math.min(len[i - k], j - k);
            }
        }
        return len;
    }
}
