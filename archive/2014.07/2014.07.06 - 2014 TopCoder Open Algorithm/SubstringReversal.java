package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class SubstringReversal {
    public int[] solve(String S) {
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
        int n = S.length();
        int x = 0, y = 0;
        char[] ss = S.toCharArray();
        char[] ans = S.toCharArray();
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) != cs[i]) {
                for (int j = i + 1; j < n; j++) {
                    Algo.reverse(ss, i, j + 1);
                    if (lower(ss, ans)) {
                        ans = ss.clone();
                        x = i;
                        y = j;
                    }
                    Algo.reverse(ss, i, j + 1);
                }
                break;
            }
        }
        return new int[] {x, y};
    }

    private boolean lower(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) return true;
            if (a[i] > b[i]) return false;
        }
        return false;
    }
}
