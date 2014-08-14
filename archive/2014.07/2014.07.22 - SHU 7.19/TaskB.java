package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private Set<State> vis;
    private int[] path;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        for (;;) {
            String line = in.nextLine();
            if (line == null) break;
            String[] ss = line.split(" ");
            if (ss.length < 24) break;
            int[] is = new int[24];
            for (int i = 0; i < 24; i++) is[i] = Integer.parseInt(ss[i]);
            solve(is);
        }
    }

    int ans;
    int maxDepth;
    private void solve(int[] is) {
        State begin = new State(is);
        for (int i = 0; ; i++) {
            maxDepth = i;
            vis = new HashSet<State>();
            path = new int[maxDepth];
            vis.add(begin);
            if (dfs(begin, 0)) break;
        }
        if (maxDepth == 0) out.println("No moves needed");
        else {
            for (int i : path) {
                out.print((char) (i + 'A'));
            }
            out.println();
        }
        out.println(ans);
    }

    boolean dfs(State crt, int step) {
        if (step == maxDepth) return crt.goal();
        if (maxDepth - step < 8 - crt.sameNumber) return false;
        for (int i = 0; i < 8; i++) {
            State next = crt.next(i);
            if (!vis.contains(next)) {
                path[step] = i;
                vis.add(next);
                if (dfs(next, step + 1)) return true;
                vis.remove(next);
            }
        }
        return false;
    }

    int[][] moves = {
            {
                          2,    1,
                          6,    3,
                    4, 5, 11, 7, 8, 9,10,
                          15,   12,
                    13,14,20,16,17,18,19,
                          22,   21,
                          0,   23
            },
            {
                          0,    3,
                          2,    8,
                    4, 5, 6, 7, 12, 9,10,
                          11,   17,
                    13,14,15,16,21,18,19,
                          20,   23,
                          22,   1
            },
            {
                          0,    1,
                          2,    3,
                    10,4, 5, 6, 7, 8, 9,
                          11,   12,
                    13,14,15,16,17,18,19,
                          20,   21,
                          22,   23
            },
            {
                          0,    1,
                          2,    3,
                    4, 5, 6, 7, 8, 9,10,
                          11,   12,
                    19,13,14,15,16,17,18,
                          20,   21,
                          22,   23
            },
            {
                          0,    23,
                          2,    1,
                    4, 5, 6, 7, 3, 9,10,
                          11,   8,
                    13,14,15,16,12,18,19,
                          20,   17,
                          22,   21
            },
            {
                          22,    1,
                          0,    3,
                    4, 5, 2, 7, 8, 9,10,
                          6,   12,
                    13,14,11,16,17,18,19,
                          15,   21,
                          20,   23
            },
            {
                          0,    1,
                          2,    3,
                    4, 5, 6, 7, 8, 9,10,
                          11,   12,
                    14,15,16,17,18,19,13,
                          20,   21,
                          22,   23
            },
            {
                          0,    1,
                          2,    3,
                    5, 6, 7, 8, 9,10, 4,
                          11,   12,
                    13,14,15,16,17,18,19,
                          20,   21,
                          22,   23
            }
    };
    class State {
        int[] is;
        int hash;
        int sameNumber;
        //        0     1
        //        2     3
        //  4  5  6  7  8  9 10
        //       11    12
        // 13 14 15 16 17 18 19
        //       20    21
        //       22    23

        void print() {
            out.printf("      %3d   %3d%n", is[0], is[1]);
            out.printf("      %3d   %3d%n", is[2], is[3]);
            for (int i = 4; i <= 10; i++) out.printf("%3d", is[i]);
            out.println();
            out.printf("      %3d   %3d%n", is[11], is[12]);
            for (int i = 13; i <= 19; i++) out.printf("%3d", is[i]);
            out.println();
            out.printf("      %3d   %3d%n", is[20], is[21]);
            out.printf("      %3d   %3d%n", is[22], is[23]);
        }
        State(int[] is) {
            this.is = is;
            hash = Arrays.hashCode(is);
            int[] cs = new int[3];
            cs[is[6] - 1]++;
            cs[is[7] - 1]++;
            cs[is[8] - 1]++;
            cs[is[11] - 1]++;
            cs[is[12] - 1]++;
            cs[is[15] - 1]++;
            cs[is[16] - 1]++;
            cs[is[17] - 1]++;
            sameNumber = Math.max(cs[0], Math.max(cs[1], cs[2]));
        }

        State next(int d) {
            int[] js = new int[24];
            for (int i = 0; i < 24; i++) {
                js[i] = is[moves[d][i]];
            }
            return new State(js);
        }

        boolean goal() {
            if (sameNumber == 8) {
                ans = is[6];
                return true;
            }
            return false;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (!Arrays.equals(is, state.is)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return hash;
        }

    }
}
