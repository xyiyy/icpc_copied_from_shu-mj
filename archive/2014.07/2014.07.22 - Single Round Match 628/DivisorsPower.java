package main;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class DivisorsPower {
    public long findArgument(long n) {
        long sn = (long) Math.sqrt(n);
        while (sn * sn < n) sn++;
        while (sn * sn > n) sn--;
        if (sn * sn == n) {
            if (BigInteger.valueOf(sn).isProbablePrime(10)) return sn;
        }
        final int LEN = 1000000;
        int[] cnt = new int[LEN];
        for (int i = 1; i < LEN; i++) {
            for (int j = i; j < LEN; j += i) {
                cnt[j]++;
            }
        }
        for (int i = 2; i < LEN; i++) {
            if (BigInteger.valueOf(i).pow(cnt[i]).equals(BigInteger.valueOf(n))) {
                return i;
            }
        }
        return -1L;
    }
}
