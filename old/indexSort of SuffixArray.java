// indexSort of class SuffixArray
class IndexSort {
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
}