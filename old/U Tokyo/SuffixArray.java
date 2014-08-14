class SuffixArray {
	int n;
	char[] cs;
	int[] si, is;// ソート後と元のインデックスの対応

	int[] indexSort(int[] is) {
		int[] c = new int[128];
		for (int i : is)
			c[i]++;
		for (int i = 1; i < 128; i++)
			c[i] += c[i - 1];
		int n = is.length;
		int[] si = new int[n];
		for (int i = n - 1; i >= 0; i--)
			si[--c[is[i]]] = i;
		return si;
	}

	SuffixArray(char[] t) {
		n = t.length;
		cs = new char[n + 1];
		System.arraycopy(t, 0, cs, 0, n);
		cs[n] = 0;// 番兵(他の全ての値より小さくすること)
		is = new int[n + 1];
		for (int i = 0; i <= n; i++)
			is[i] = cs[i];
		si = indexSort(is);
		int[] a = new int[n + 1], b = new int[n + 1];
		for (int h = 0;;) {
			for (int i = 0; i < n; i++) {
				int x = si[i + 1], y = si[i];
				b[i + 1] = b[i];
				if (is[x] > is[y] || is[x + h] > is[y + h])
					b[i + 1]++;
			}
			for (int i = 0; i <= n; i++)
				is[si[i]] = b[i];
			if (b[n] == n)
				break;
			h = max(1, h << 1);
			for (int k = h; k >= 0; k -= h) {
				fill(b, 0);
				b[0] = k;
				for (int i = k; i <= n; i++)
					b[is[i]]++;
				for (int i = 0; i < n; i++)
					b[i + 1] += b[i];
				for (int i = n; i >= 0; i--) {
					a[--b[si[i] + k > n ? 0 : is[si[i] + k]]] = si[i];
				}
				int[] tmp = si;
				si = a;
				a = tmp;
			}
		}
	}

	int[] hs;// hs[i]:=ソート後のsuffix のi とi+1 のLCP

	void buildHs() {
		hs = new int[n + 1];
		for (int i = 0, h = 0; i < n; i++) {
			for (int j = si[is[i] - 1]; cs[i + h] == cs[j + h]; h++)
				;
			hs[is[i] - 1] = h;
			if (h > 0)
				h--;
		}
	}

	RMQ rmq;

	void buildRMQ() {
		rmq = new RMQ(hs);// 値を返す版
	}

	// 位置i,j から始まる部分列のLCP を求める
	int getLCP(int i, int j) {
		if (i == j)
			return n - i;
		return rmq.query(min(is[i], is[j]), max(is[i], is[j]));
	}
}
