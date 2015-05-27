package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Similars {
    public int maxsim(int L, int R) {
        int[] set = new int[1 << 10];
        int res = 0;
        for (int i = L; i <= R; i++) {
            Set<Character> s = new HashSet<>();
            for (char c : Integer.toString(i).toCharArray()) s.add(c);
            int ss = 0;
            for (int j = 0; j < 10; j++) {
                if (s.contains((char) (j + '0'))) {
                    ss |= 1 << j;
                }
            }
            set[ss]++;
        }
        for (int i = 0; i < (1 << 10); i++) if (set[i] > 0) {
            for (int j = i; j < (1 << 10); j++) if ((j == i && set[i] > 1) || (j != i && set[j] > 0)) {
                int t = 0;
                for (int k = 0; k < 10; k++) if ((i & (1 << k)) != 0 && (j & (1 << k)) != 0) {
                    t++;
                }
                res = Math.max(res, t);
            }
        }
        return res;
    }
}
