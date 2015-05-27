package main;

import com.shu_mj.math.Num;
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
        int n = in.nextInt();
        int N = (int) 1e6;
        int[] cnt = new int[N + 2];
        for (int i : in.nextIntArray(n)) cnt[i]++;
        Queue<Integer> que = new PriorityQueue<>(Comparator.<Integer>reverseOrder());
        for (int i = N; i >= 1; i--) {
            for (int j = 0; j < (cnt[i] + cnt[i + 1]) / 2; j++) que.add(i);
//            if (cnt[i] + cnt[i + 1] >= 2) Algo.debug(cnt[i], cnt[i + 1]);
            if (cnt[i] > 0) {
                cnt[i] -= cnt[i + 1];
                cnt[i] %= 2;
            }
        }
//        Algo.debug(que);
        long res = 0;
        while (que.size() > 1) {
            res += (long) que.poll() * que.poll();
        }
        out.println(res);
    }
}
