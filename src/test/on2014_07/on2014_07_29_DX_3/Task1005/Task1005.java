package test.on2014_07.on2014_07_29_DX_3.Task1005;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1005 {
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
            StringBuilder sb = new StringBuilder();
            while (n-- != 0) {
                sb.append(in.nextLine());
            }
            solve(sb.toString());
        }
    }

    private void solve(String s) {
        int n = s.length();
        long res = 1;
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == '{') {
                int j = i;
                while (j < n && s.charAt(j) != '}') j++;
                int c = 0;
                for (int k = i; k < j; k++) if (s.charAt(k) == '|') c++;
                res *= c + 1;
                if (res > 1e5) {
                    im();
                    return ;
                }
                i = j;
            } else if (s.charAt(i) == '$') {
                int j = i + 1;
                while (j < n && s.charAt(j) != '$') j++;
                int k = i + 1;
                while (k < j) {
                    int c = 0;
                    if (s.charAt(k) == ' ') {
                        while (s.charAt(k) == ' ') {
                            k++;
                            c++;
                        }
                    }
                    res *= c + 1;
                    if (res > 1e5) {
                        im();
                        return ;
                    }
                    k++;
                }
                i = j + 1;
            } else {
                i++;
            }
        }
        out.println(res);
    }

    private void im() {
        out.println("doge");
    }
}
