package test.on2014_07.on2014_07_28_MemSQL_Start_c_UP_2_0___Round_1.D___Washer__Dryer__Folder;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;
    private int time;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int k = in.nextInt();
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int t3 = in.nextInt();
        int[] f1 = new int[n1];
        int[] f2 = new int[n2];
        int[] f3 = new int[n3];
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int p1 = f1[i % n1] + t1 + t2 + t3;
            int p2 = f2[i % n2] + t2 + t3;
            int p3 = f3[i % n3] + t3;
            int p = Math.max(Math.max(p1, p2), p3);
            f1[i % n1] = p - t2 - t3;
            f2[i % n2] = p - t3;
            f3[i % n3] = p;
            ans = p;
        }
        out.println(ans);
    }

}
