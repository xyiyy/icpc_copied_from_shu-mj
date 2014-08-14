import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.fill;

class Seg {
	int N;
	int[][] iss;
	int[] mul, add;

	Seg(int n) {
		N = Integer.highestOneBit(n) << 1;
		iss = new int[N * 2][3];
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
			iss[o] = merge(iss[o * 2], iss[o * 2 + 1]);
		}
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
		iss[o][0] = m * iss[o][0] + size(o) * a;
		iss[o][1] = m * iss[o][1] + a;
		iss[o][2] = m * iss[o][2] + a;
		mul[o] *= m;
		add[o] = m * add[o] + a;
	}

	int[] query(int s, int t) {
		update(s, t, 1, 0);
		int[] res = init;
		while (0 < s && s + (s & -s) <= t) {
			int i = (N + s) / (s & -s);
			res = merge(res, iss[i]);
			s += s & -s;
		}
		while (s < t) {
			int i = (N + t) / (t & -t) - 1;
			res = merge(res, iss[i]);
			t -= t & -t;
		}
		return res;
	}

	int[] init = { 0, Integer.MAX_VALUE, Integer.MIN_VALUE };

	int[] merge(int[] a, int[] b) {
		return new int[] { a[0] + b[0], min(a[1], b[1]), max(a[2], b[2]) };
	}
}
