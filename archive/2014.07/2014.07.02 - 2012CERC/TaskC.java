package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private Set<String> set;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }
    String bs = "H He " +
            "Li Be B C N O F Ne " +
            "Na Mg Al Si P S Cl Ar " +
            "K Ca Sc Ti V Cr Mn Fe Co Ni Cu Zn Ga Ge As Se Br Kr " +
            "Rb Sr Y Zr Nb Mo Tc Ru Rh Pd Ag Cd In Sn Sb Te I Xe " +
            "Cs Ba Hf Ta W Re Os Ir Pt Au Hg Tl Pb Bi Po At Rn " +
            "Fr Ra Rf Db Sg Bh Hs Mt Ds Rg Cn Fl Lv " +
            "La Ce Pr Nd Pm Sm Eu Gd Tb Dy Ho Er Tm Yb Lu " +
            "Ac Th Pa U Np Pu Am Cm Bk Cf Es Fm Md No Lr";
    void run() {
        bs = bs.toLowerCase();
        String[] ss = bs.split(" ");
        set = new HashSet<String>();
        for (String s : ss) set.add(s);
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        if (cs.length == 1) {
            if (set.contains(String.valueOf(cs))) out.println("YES");
            else out.println("NO");
        }
        if (cs.length == 2) {
            if (set.contains(String.valueOf(cs)) || set.contains(String.valueOf(cs[0])) && set.contains(String.valueOf(cs[1]))) out.println("YES");
            else out.println("NO");
        }
        int n = cs.length;
        boolean[] dp = new boolean[n];
        if (set.contains(String.valueOf(cs[0]))) dp[0] = true;
        if (dp[0] && set.contains(String.valueOf(cs[1])) || set.contains(String.valueOf(cs, 0, 2))) dp[1] = true;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] && set.contains(String.valueOf(cs[i])) ||
                    dp[i - 2] && set.contains(String.valueOf(cs, i - 1, 2));
        }
        out.println(dp[n - 1] ? "YES" : "NO");
    }
}
