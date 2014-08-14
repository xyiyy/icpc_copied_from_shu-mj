package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class CliqueGraph {
    public long calcSum(int N, int[] V, int[] sizes) {
        int sum = 0;
        int[][] dis = new int[N][N];
        for (int s : sizes) {
            for (int i = sum; i < sum + s; i++) {
                for (int j = i + 1; j < sum + s; j++) {
                    int u = V[i];
                    int v = V[j];
                    dis[u][v] = dis[v][u] = 1;
                }
            }
            sum += s;
        }
        V[] vs = new V[N];
        for (int i = 0; i < N; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dis[i][j] != 0) vs[i].add(j);
            }
        }
        long ans = 0;
        for (int i = 0; i < N; i++) {
            Queue<Integer> que = new LinkedList<Integer>();
            int[] d = new int[N];
            Arrays.fill(d, -1);
            que.add(i);
            d[i] = 0;
            while (!que.isEmpty()) {
                int u = que.poll();
                for (int v : vs[u]) {
                    if (d[v] == -1) {
                        d[v] = d[u] + 1;
                        que.add(v);
                    }
                }
            }
            ans += Algo.sum(d);
        }
        return ans / 2;
    }
    class V extends ArrayList<Integer> {

    }
}
