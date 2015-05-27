package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: cryptcow
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        String os = "Begin the Escape execution at the Break of Dawn";
        String s = in.nextLine();
        if (s.length() < os.length() || (s.length() - os.length()) % 3 != 0) {
            out.println("0 0");
            return ;
        }
        int k = (s.length() - os.length()) / 3;
        List<Integer> ci = new ArrayList<Integer>();
        List<Integer> oi = new ArrayList<Integer>();
        List<Integer> wi = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'C') ci.add(i);
            else if (s.charAt(i) == 'O') oi.add(i);
            else if (s.charAt(i) == 'W') wi.add(i);
        }
        if (ci.size() != k || oi.size() != k || wi.size() != k) {
            out.println("0 0");
            return ;
        }
        int[] cnt = new int[128];
        for (int i = 0; i < os.length(); i++) cnt[os.charAt(i)]++;
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i)]--;
        for (int i = 0; i < 128; i++) if (i != 'C' && i != 'O' && i != 'W' && cnt[i] != 0) {
            out.println("0 0");
            return ;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'C') {
                if (s.charAt(i) != os.charAt(i)) {
                    out.println("0 0");
                    return ;
                }
            } else {
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != 'W') {
                if (s.charAt(i) != os.charAt(os.length() - (s.length() - i))) {
                    out.println("0 0");
                    return ;
                }
            } else {
                break;
            }
        }
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < os.length(); i++) {
            for (int j = i + 1; j < os.length(); j++) {
                set.add(os.substring(i, j));
                set.add(new StringBuilder(os.substring(i, j)).reverse().toString());
            }
        }
        for (int i = 0, b = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'C' || s.charAt(i) == 'O' || s.charAt(i) == 'W') {
                if (i != b && !set.contains(s.substring(b, i))) {
                    out.println("0 0");
                    return ;
                }
                b = i + 1;
            }
        }
        out.println("1 " + k);
    }
}
