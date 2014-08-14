import java.math.BigInteger;

class BigIntegerSqrt {
	// 传参要求是正数，返回的是它的算术平方根的整数部分
	BigInteger sqrt(String theNumber) {
		int length = theNumber.length(), i;
		BigInteger res = BigInteger.ZERO;
		BigInteger twenty = BigInteger.valueOf(20);
		BigInteger t, x = BigInteger.ZERO, v, few = BigInteger.ZERO;
		BigInteger hg = BigInteger.valueOf(100);
		String tmpString = null;
		int pos = 2 - length % 2;
		tmpString = theNumber.substring(0, pos);
		while (true) {
			v = few.multiply(hg).add(
					BigInteger.valueOf(Integer.parseInt(tmpString)));
			if (res.compareTo(BigInteger.ZERO) == 0)
				i = 9;
			else
				i = v.divide(res.multiply(twenty)).intValue();
			for (; i >= 0; i--) {
				t = res.multiply(twenty).add(BigInteger.valueOf(i))
						.multiply(BigInteger.valueOf(i));
				if (t.compareTo(v) <= 0) {
					x = t;
					break;
				}
			}
			res = res.multiply(BigInteger.TEN).add(BigInteger.valueOf(i));
			few = v.subtract(x);
			pos++;
			if (pos > length)
				break;
			tmpString = theNumber.substring(pos - 1, ++pos);
		}
		return res;
	}
}
