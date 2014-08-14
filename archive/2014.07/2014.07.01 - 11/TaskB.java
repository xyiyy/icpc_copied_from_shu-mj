package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            Stack<Integer> st = new Stack<Integer>();
            int j = 1;
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                if (!flag) while (st.isEmpty() || st.peek() != x) {
                    if (j > n) {
                        flag = true;
                        out.println("No");
                        break;
                    } else {
                        st.add(j++);
                    }
                }
                if (!flag) st.pop();
            }
            if (!flag) out.println("Yes");
        }
    }
}
