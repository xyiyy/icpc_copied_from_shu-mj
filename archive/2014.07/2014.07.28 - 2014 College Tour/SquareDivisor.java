package main;
import java.math.BigInteger;
import java.util.*;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;

public class SquareDivisor {
    public long biggest(long n) {
        Map<BigInteger, Integer> factors = new HashMap<BigInteger, Integer>();
        Num.factorize(BigInteger.valueOf(n), factors);
        long res = 1;
        for (Map.Entry<BigInteger, Integer> e : factors.entrySet()) {
            res *= e.getKey().pow(e.getValue() / 2 * 2).longValue();
        }
        return res;
    }
}
