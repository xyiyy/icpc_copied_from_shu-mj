public class SolutionsSpace {
	final double EPS = 1e-6;
	double[][] solutionSpace(double[][] A, double[] b) {
		int n = A.length, m = A[0].length;
		double[][] a = new double[n][m + 1];
		for (int i = 0; i < n; i++) {
			System.arraycopy(A[i], 0, a[i], 0, m);
			a[i][m] = b[i];
		}
		int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
		fill(id, -1);
		int pi = 0; // 矩阵 A 的秩
		for (int pj = 0; pi < n && pj < m; pj++) {
			for (int i = pi + 1; i < n; i++) {
				if (abs(a[i][pj]) > abs(a[pi][pj])) {
					double[] t = a[i];
					a[i] = a[pi];
					a[pi] = t;
				}
			}
			if (abs(a[pi][pj]) < EPS) // 当前列已经全零
				continue;
			double inv = 1 / a[pi][pj]; // 如果取模运算，可以用大数模逆
			for (int j = 0; j <= m; j++) // 化主元为 1，可以优化
				a[pi][j] *= inv;
			for (int i = 0; i < n; i++)
				if (i != pi) {
					double d = a[i][pj];
					for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
						a[i][j] -= d * a[pi][j];
				}
			id[pi++] = pj;
		}
		for (int i = pi; i < n; i++)
			if (abs(a[i][m]) > EPS) // 增广矩阵的秩更大，无解
				return null;
		double[][] X = new double[1 + m - pi][m];
		for (int j = 0, k = 0; j < m; j++) {
			if (id[k] == j)
				X[0][j] = a[k++][m];
			else {
				for (int i = 0; i < k; i++)
					X[1 + j - k][id[i]] = -a[i][j];
				X[1 + j - k][j] = 1;
			}
		}
		return X;
	}
}