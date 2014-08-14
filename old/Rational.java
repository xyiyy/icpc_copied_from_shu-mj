import java.math.BigInteger;
import static java.math.BigInteger.*;

class Rational implements Comparable<Rational> {
	static final Rational R0 = new Rational(ZERO, ONE), R1 = new Rational(ONE,
			ONE);
	BigInteger num, den;

	Rational(BigInteger num, BigInteger den) {
		this.num = num;
		this.den = den;
		red();
	}

	void red() {
		BigInteger gcd = num.gcd(den);
		if (gcd.signum() != 0) {
			num = num.divide(gcd);
			den = den.divide(gcd);
		}
		if (den.signum() < 0) {
			num = num.negate();
			den = den.negate();
		}
	}

	Rational add(Rational r) {
		return new Rational(num.multiply(r.den).add(r.num.multiply(den)),
				den.multiply(r.den));
	}

	Rational sub(Rational r) {
		return new Rational(num.multiply(r.den).subtract(r.num.multiply(den)),
				den.multiply(r.den));
	}

	Rational mul(Rational r) {
		return new Rational(num.multiply(r.num), den.multiply(r.den));
	}

	Rational div(Rational r) {
		return new Rational(num.multiply(r.den), den.multiply(r.num));
	}

	int signum() {
		return num.signum();
	}

	Rational pow(int b) {
		BigInteger n = ONE, d = ONE, an = num, ad = den;
		while (b > 0) {
			if ((b & 1) == 1) {
				n = n.multiply(an);
				d = d.multiply(ad);
			}
			an = an.multiply(an);
			ad = ad.multiply(ad);
			b >>>= 1;
		}
		return new Rational(n, d);
	}

	public int compareTo(Rational o) {
		return (num.multiply(o.den).compareTo(o.num.multiply(den)));
	}
}