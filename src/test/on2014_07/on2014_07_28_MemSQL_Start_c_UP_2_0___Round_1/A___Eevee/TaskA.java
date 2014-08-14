package test.on2014_07.on2014_07_28_MemSQL_Start_c_UP_2_0___Round_1.A___Eevee;



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
