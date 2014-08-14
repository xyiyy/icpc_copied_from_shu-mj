package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        in.nextInt();
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int[] is = in.nextIntArray(5);
        int ans = 0;
        for (int i = 1; i < (1 << 5); i++) {
            int[] id = new int[Integer.bitCount(i)];
            for (int j = 0, k = 0; j < 5; j++) {
                if ((i & (1 << j)) != 0) id[k++] = j;
            }
            if (up(is, id)) ans = Math.max(ans, id.length);
            Algo.reverse(id);
            if (up(is, id)) ans = Math.max(ans, id.length);
        }
        out.println(ans);
    }

    private boolean up(int[] is, int[] id) {
        for (int i = 1; i < id.length; i++) {
            if (is[id[i]] < is[id[i - 1]]) return false;
        }
        return true;
    }
}
