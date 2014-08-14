class RMQ {
	int[] vs;
	int[][] min;

	RMQ(int[] vs) {
		int n = vs.length, m = log2(n) + 1;
		this.vs = vs;
		min = new int[m][n];
		for (int i = 0; i < n; i++)
			min[0][i] = i;
		for (int i = 1, k = 1; i < m; i++, k <<= 1) {
			for (int j = 0; j + k < n; j++) {
				min[i][j] = vs[min[i - 1][j]] <= vs[min[i - 1][j + k]] ? min[i - 1][j]
						: min[i - 1][j + k];
			}
		}
	}

	// ��С�����}�����ڤ�����Ϥ�һ������Τ򷵤�
	int query(int from, int to) {
		int k = log2(to - from);
		return vs[min[k][from]] <= vs[min[k][to - (1 << k)]] ? min[k][from]
				: min[k][to - (1 << k)];
	}

	int log2(int b) {
		return 31 - Integer.numberOfLeadingZeros(b);
	}
}
