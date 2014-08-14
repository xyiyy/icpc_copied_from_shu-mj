class PrimeTable {
	
	int[] primeTable(int n) {
		ArrayList<Integer> prime = new ArrayList<Integer>();
		boolean[] vis = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {
			if (!vis[i])
				prime.add(i);
			for (int p : prime) {
				if (i > n / p)
					break; // ·ÀÖ¹ i * p Òç³ö
				vis[i * p] = true;
				if (i % p == 0)
					break;
			}
		}
		return toi(prime.toArray(new Integer[0]));
	}
}
