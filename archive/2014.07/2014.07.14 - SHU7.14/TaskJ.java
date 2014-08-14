package main;

import com.shu_mj.collections.map.IncHashMap;
import com.shu_mj.collections.map.IncMap;
import com.shu_mj.collections.map.IncTreeMap;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        IncMap<String> cnt = new IncTreeMap<>();
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            cnt.inc(line.split(" ")[0]);
        }
        for (Map.Entry<String, Integer> e : cnt.entrySet()) {
            out.println(e.getKey() + " " + e.getValue());
        }
    }
}
