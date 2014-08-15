import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.math.BigInteger;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author shu_mj @ http://shu-mj.com
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		Scanner in = new Scanner(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task4945 solver = new Task4945();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task4945 {
    Scanner in;
    PrintWriter out;
    private long[] two;
    private long[] p;
    private long[] rP;
    private int[] logTwo;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        two = new long[100010];
        for (int i = 0; i < two.length; i++) {
            if (i == 0) two[i] = 1;
            else two[i] = two[i - 1] * 2 % M;
        }
        p = new long[100010];
        for (int i = 0; i < p.length; i++) {
            if (i == 0) p[i] = 1;
            else p[i] = p[i - 1] * i % M;
        }
        rP = new long[100010];
        for (int i = 100009; i >= 0; i--) {
            if (i == 100009) rP[i] = BigInteger.valueOf(p[i]).modInverse(BigInteger.valueOf(M)).longValue();
            else rP[i] = rP[i + 1] * (i + 1) % M;
        }
        logTwo = new int[2049];
        Arrays.fill(logTwo, -1);
        for (int i = 1, j = 0; i <= 2048; i <<= 1) {
            logTwo[i] = j;
        }
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case #%d: ", cs++);
            solve(n);
        }
    }
    final long M = 998244353;
    private void solve(int n) {
        int[] cs = new int[12];
        int other = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (logTwo[x] != -1) cs[logTwo[x]]++;
            else other++;
        }
        long[][] dp = new long[13][2049];
        dp[12][0] = 1;
        for (int i = 12; i > 0; i--) {
            for (int j = 0; j <= 2048; j += two[i]) if (dp[i][j] != 0) {
                long sum = 0;
                for (int k = 0; k <= cs[i - 1]; k++) {
                    int crt = j + k * (int) two[i - 1];
                    if (crt >= 2048) {
                        dp[i - 1][2048] += dp[i][j] * (two[cs[i - 1]] - sum) % M;
                        dp[i - 1][2048] %= M;
                        break;
                    } else {
                        dp[i - 1][crt] += dp[i][j] * C(cs[i - 1], k) % M;
                        dp[i - 1][crt] %= M;
                    }
                    sum += C(cs[i - 1], k);
                    sum %= M;
                }
                Algo.debug(i, j);
            }
        }
        long res = dp[0][2048] * two[other] % M;
        res %= M;
        res += M;
        res %= M;
        out.println(res);
    }

    private long C(int m, int n) {
        return p[m] * rP[n] % M * rP[m - n] % M;
    }
}

class Scanner {
    BufferedReader br;
    StringTokenizer st;

    public Scanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
        eat("");
    }

    private void eat(String s) {
        st = new StringTokenizer(s);
    }

    public String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!st.hasMoreTokens()) {
            String s = nextLine();
            if (s == null)
                return false;
            eat(s);
        }
        return true;
    }

    public String next() {
        hasNext();
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

}

class Algo {

    public static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }


}

