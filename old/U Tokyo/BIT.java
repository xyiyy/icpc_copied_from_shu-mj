class BIT {
	long[] vs;

	BIT(int n) {
		vs = new long[n + 1];
	}

	void add(int k, long a) {
		for (int i = k + 1; i < vs.length; i += i & -i) {
			vs[i] += a;
		}
	}

	long sum(int s, int t) {
		if (s > 0)
			return sum(0, t) - sum(0, s);
		long res = 0;
		for (int i = t; i > 0; i -= i & -i) {
			res += vs[i];
		}
		return res;
	}
}
