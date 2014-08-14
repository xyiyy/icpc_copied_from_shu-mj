import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

class Seg {
	int N;
	int[][] iss; // sum, min, max
	int[] mul, add;

	Seg(int n) {
		N = Integer.highestOneBit(n) << 1;
		iss = new int[3][N * 2];
		mul = new int[N * 2];
		add = new int[N * 2];
		fill(mul, 1);
	}

	int s, t, m, a;

	void update(int s, int t, int m, int a) {
		this.s = s;
		this.t = t;
		this.m = m;
		this.a = a;
		update(1, 0, N);
	}

	void update(int o, int L, int R) {
		if (s <= L && R <= t) {
			push(m, a, o);
		} else {
			pushdown(o);
			int M = (L + R) / 2;
			if (s < M)
				update(o * 2, L, M);
			if (t > M)
				update(o * 2 + 1, M, R);
			merge(o, o * 2, o * 2 + 1);
		}
	}

	void merge(int o, int a, int b) {
		iss[0][o] = iss[0][a] + iss[0][b];
		iss[1][o] = min(iss[1][a], iss[1][b]);
		iss[2][o] = max(iss[2][a], iss[2][b]);
	}

	void pushdown(int o) {
		push(mul[o], add[o], o * 2);
		push(mul[o], add[o], o * 2 + 1);
		mul[o] = 1;
		add[o] = 0;
	}

	int size(int o) {
		return N / Integer.highestOneBit(o);
	}

	void push(int m, int a, int o) {
		iss[0][o] = m * iss[0][o] + size(o) * a;
		iss[1][o] = m * iss[1][o] + a;
		iss[2][o] = m * iss[2][o] + a;
		mul[o] *= m;
		add[o] = m * add[o] + a;
	}

	int[] query(int s, int t) {
		update(s, t, 1, 0);
		iss[0][0] = 0;
		iss[1][0] = Integer.MAX_VALUE;
		iss[2][0] = Integer.MIN_VALUE;
		while (0 < s && s + (s & -s) <= t) {
			int i = (N + s) / (s & -s);
			merge(0, 0, i);
			s += s & -s;
		}
		while (s < t) {
			int i = (N + t) / (t & -t) - 1;
			merge(0, 0, i);
			t -= t & -t;
		}
		return new int[] { iss[0][0], iss[1][0], iss[2][0] };
	}
}