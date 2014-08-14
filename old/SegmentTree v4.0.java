class Seg {
	int N;
	long[] is, mul, add;

	Seg(int n) {
		N = Integer.highestOneBit(n) << 1;
		is = new long[N * 2];
		// 初始化过程 根据需要修改
		for (int i = 0; i < n; i++)
			is[N + i] = in.nextLong();
		for (int i = N - 1; i > 0; i--)
			is[i] = merge(is[i * 2], is[i * 2 + 1]);
		mul = new long[N * 2];
		add = new long[N * 2];
		fill(mul, 1);
	}

	int s, t;
	long m, a;

	void update(int s, int t, long m, long a) {
		this.s = s;
		this.t = t;
		this.m = m;
		this.a = a;
		update(1, 0, N, 1, 0);
	}

	void update(int o, int L, int R, long m, long a) {
		if (s <= L && R <= t) {
			// push this.m, this.a to m, a
			m = this.m * m;
			a = this.m * a + this.a;
		}
		// push m, a to mul[o], add[o]
		mul[o] = m * mul[o];
		add[o] = m * add[o] + a;
		if (t <= L || R <= s || s <= L && R <= t) {
			// maintain is[o] for m, a
			is[o] = m * is[o] + a * (R - L); // 根据维护信息修改
		} else {
			int M = (L + R) / 2;
			update(o * 2, L, M, mul[o], add[o]);
			update(o * 2 + 1, M, R, mul[o], add[o]);
			// init mul[o], add[o]
			mul[o] = 1;
			add[o] = 0;
			is[o] = merge(is[o * 2], is[o * 2 + 1]);
		}
	}

	long query(int s, int t) {
		update(s, t, 1, 0);
		long res = 0; // 初始化 根据维护信息修改
		while (0 < s && s + (s & -s) <= t) {
			int i = (N + s) / (s & -s);
			res = merge(res, is[i]);
			s += s & -s;
		}
		while (s < t) {
			int i = (N + t) / (t & -t) - 1;
			res = merge(res, is[i]);
			t -= t & -t;
		}
		return res;
	}

	long merge(long a, long b) {
		return a + b; // 根据维护信息修改
	}

	// 后面是另一种 update 实现，会慢一点
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
			is[o] = merge(is[o * 2], is[o * 2 + 1]);
		}
	}

	void pushdown(int o) {
		push(mul[o], add[o], o * 2);
		push(mul[o], add[o], o * 2 + 1);
		mul[o] = 1;
		add[o] = 0;
	}

	long size(int o) {
		return N / Integer.highestOneBit(o);
	}

	void push(long m, long a, int o) {
		is[o] = m * is[o] + size(o) * a; // 根据维护信息修改
		mul[o] *= m;
		add[o] = m * add[o] + a;
	}

}
