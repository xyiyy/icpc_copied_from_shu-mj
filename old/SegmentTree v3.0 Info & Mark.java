import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

class Info {
	int min, max, sum;

	public Info() {
		this(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
	}

	public Info(int min, int max, int sum) {
		this.min = min;
		this.max = max;
		this.sum = sum;
	}

	public void maintain(Mark m, int size) {
		if (!m.isINIT()) {
			min = m.mul * min + m.add;
			max = m.mul * max + m.add;
			sum = m.mul * sum + m.add * size;
		}
	}

	public void merge(Info a, Info b) {
		min = min(a.min, b.min);
		max = max(a.max, b.max);
		sum = a.sum + b.sum;
	}

}

class Mark {
	int mul, add;
	static final Mark INIT = new Mark();

	public Mark() {
		this(1, 0);
	}

	public Mark(int mul, int add) {
		this.mul = mul;
		this.add = add;
	}

	public void push(Mark m) {
		if (!m.isINIT()) {
			mul = m.mul * mul;
			add = m.mul * add + m.add;
		}
	}

	public Mark clone() {
		return new Mark(mul, add);
	}

	public void init() {
		mul = 1;
		add = 0;
	}

	public boolean isINIT() {
		return mul == INIT.mul && add == INIT.add;
	}
}

class Seg {
	int N;
	Info[] is;
	Mark[] ms;

	Seg(int n) {
		N = Integer.highestOneBit(n) << 1;
		is = new Info[N * 2];
		ms = new Mark[N * 2];
		// ...
		for (int i = 0; i < N * 2; i++) {
			is[i] = new Info();
		}
		for (int i = 0; i < N * 2; i++) {
			ms[i] = new Mark();
		}
	}

	int s, t;
	Mark mark;

	void update(int s, int t, Mark mark) {
		this.s = s;
		this.t = t;
		this.mark = mark;
		update(1, 0, N, new Mark());
	}

	void update(int o, int L, int R, Mark m) {
		if (s <= L && R <= t) {
			m = m.clone();
			m.push(mark);
		}
		ms[o].push(m);
		if (t <= L || R <= s || s <= L && R <= t) {
			is[o].maintain(m, R - L);
		} else {
			int M = (L + R) / 2;
			update(o * 2, L, M, ms[o]);
			update(o * 2 + 1, M, R, ms[o]);
			ms[o].init();
			is[o].merge(is[o * 2], is[o * 2 + 1]);
		}
	}

	Info query(int s, int t) {
		Info res = new Info();
		while (0 < s && s + (s & -s) <= t) {
			int i = (N + s) / (s & -s);
			res.merge(res, is[i]);
			s += s & -s;
		}
		while (s < t) {
			int i = (N + t) / (t & -t) - 1;
			res.merge(res, is[i]);
			t -= t & -t;
		}
		return res;
	}

	void printb(int i) {
		int n = Integer.numberOfTrailingZeros(N) - i;
		n = 2 * (1 << n) - 2;
		for (int j = 0; j < n; j++) {
			out.print(" ");
		}
	}

	void print() {
		for (int i = 0; i <= Integer.numberOfTrailingZeros(N); i++) {
			int b = 1 << i;
			for (int j = 0; j < (1 << i); j++) {
				printb(i);
				out.printf("%4d", is[b + j].sum);
				printb(i);
			}
			out.println();
		}
	}
}