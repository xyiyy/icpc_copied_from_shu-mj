package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1010 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    String lose = "Why give up treatment";
    String win = "Wake up to code";
    void run() {
        long v1 = in.nextInt();
        long v2 = in.nextInt();
        long r = in.nextInt();
        long d = in.nextInt();
        if (v2 <= v1) {
            out.println(lose);
            return ;
        }
        
    }
}
