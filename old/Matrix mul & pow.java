class Matrix {
	long M = 1000000007;

	long[][] mul(long[][] a, long[][] b) {
		int n = a.length;
		long[][] c = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < n; k++) {
				for (int j = 0; j < n; j++) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % M;
				}
			}
		}
		return c;
	}

	long[][] pow(long[][] a, long b) {
		int n = a.length;
		long[][] c = new long[n][n];
		for (int i = 0; i < n; i++)
			c[i][i] = 1;
		while (b > 0) {
			if ((b & 1) != 0)
				c = mul(c, a);
			a = mul(a, a);
			b >>>= 1;
		}
		return c;
	}
}