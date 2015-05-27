package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        String s = in.next();
        int n = in.nextInt();
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : s.toCharArray()) Num.inc(cnt, c);
        if (cnt.size() > n) {
            out.println(-1);
            return ;
        }
        for (int i = 1; ; i++) {
            Map<Character, Integer> res = new HashMap<>();
            for (Map.Entry<Character, Integer> e : cnt.entrySet()) {
                res.put(e.getKey(), (e.getValue() + i - 1) / i);
            }
            if (Algo.sum(Algo.unBox(res.values().toArray(new Integer[0]))) <= n) {
                res.put(s.charAt(0), n - (int) Algo.sum(Algo.unBox(res.values().toArray(new Integer[0]))) + res.get(s.charAt(0)));
                out.println(i);
                for (Map.Entry<Character, Integer> e : res.entrySet()) {
                    for (i = 0; i < e.getValue(); i++) {
                        out.print(e.getKey());
                    }
                }
                out.println();
                return ;
            }
        }
    }
}
