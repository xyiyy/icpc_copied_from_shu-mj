class Seg {
	BIT dif, pre;

	Seg(int n) {
		dif = new BIT(n);
		pre = new BIT(n);
	}

	void add(int s, int t, long v) {
		dif.add(s, v);
		dif.add(t, -v);
		pre.add(s, v * s);
		pre.add(t, -v * t);
	}

	long sum(int s, int t) {
		if (s > 0)
			return sum(0, t) - sum(0, s);
		return dif.sum(0, t) * t - pre.sum(0, t);
	}
}
