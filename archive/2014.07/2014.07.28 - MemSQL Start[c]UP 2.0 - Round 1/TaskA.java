package main;

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

    String[] ss = {"vaporeon", "jolteon", "flareon", "espeon", "umbreon", "leafeon", "glaceon", "sylveon"};
    void run() {
        int n = in.nextInt();
        String s = in.next();
        s = s.replaceAll("\\.", "[a-z]");
        for (String name : ss) {
            if (name.matches(s)) {
                out.println(name);
                return ;
            }
        }
    }
}
