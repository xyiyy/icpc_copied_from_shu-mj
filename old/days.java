class Days {
	int days(int y, int m, int d) {
		m = (m + 9) % 12;
		y = y - m / 10;
		return 365 * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10 + (d - 1);
	}
}