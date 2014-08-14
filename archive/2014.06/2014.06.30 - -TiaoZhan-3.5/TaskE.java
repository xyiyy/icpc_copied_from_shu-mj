package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[][] sons = new int[26 + n][];
        int id = 26;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String[] ss = in.nextLine().split(" ");
            if (ss.length == 3) {
                if (!map.containsKey(ss[0])) map.put(ss[0], id++);
                int root = map.get(ss[0]);
                sons[root] = new int[ss[2].length()];
                for (int j = 0; j < ss[2].length(); j++)
                    sons[root][j] = ss[2].charAt(j) - 'a';
            } else {
                if (!map.containsKey(ss[0])) map.put(ss[0], id++);
                if (!map.containsKey(ss[2])) map.put(ss[2], id++);
                if (!map.containsKey(ss[4])) map.put(ss[4], id++);
                int root = map.get(ss[0]);
                sons[root] = new int[]{map.get(ss[2]), map.get(ss[4])};
            }
        }
        T[] dp = new T[26 + n];
        String start = in.next();
        T t = build(dp, sons, map.get(start));
        for (char c : in.next().toCharArray()) {
            if (t == null || !t.bs[c - 'a']) {
                out.println("NO");
                return ;
            }
            t = split(t, c);
        }
        out.println("YES");
    }

    T build(T[] dp, int[][] sons, int crt) {
        if (dp[crt] != null) return dp[crt];
        if (sons[crt] == null) return dp[crt] = new T((char) (crt + 'a'));
        T[] ss = new T[sons[crt].length];
        for (int i = 0; i < sons[crt].length; i++) {
            ss[i] = build(dp, sons, sons[crt][i]);
        }
        return dp[crt] = new T(ss);
    }
    T split(T t, char c) {
        if (t.sons == null) return null;
        for (int i = 0; i < t.sons.length; i++) {
            if (t.sons[i].bs[c - 'a']) {
                T r = split(t.sons[i], c);
                if (r != null) {
                    T[] sons = new T[t.sons.length - i];
                    sons[0] = r;
                    System.arraycopy(t.sons, i + 1, sons, 1, sons.length - 1);
                    return new T(sons);
                } else {
                    return new T(Arrays.copyOfRange(t.sons, i + 1, t.sons.length));
                }
            }
        }
        return null;
    }
    class T {
        boolean[] bs;
        T[] sons;
        T(char c) {
            bs = new boolean[26];
            bs[c - 'a'] = true;
        }
        T(T[] sons) {
            this.sons = sons;
            bs = new boolean[26];
            for (int i = 0; i < 26; i++) {
                for (T t : sons) {
                    bs[i] |= t.bs[i];
                }
            }
        }

    }
}
