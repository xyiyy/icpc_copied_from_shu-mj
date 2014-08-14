package main;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class CandyMaking {
    public double findSuitableDensity(int[] containerVolume, int[] desiredWeight) {
        /*BigDecimal l = BigDecimal.valueOf(0), r = BigDecimal.valueOf((long) (1e7));
        for (int i = 0; i < 200; i++) {
            BigDecimal lm = l.add(r.subtract(l).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128));
            BigDecimal rm = r.subtract((r.subtract(l).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128)));
            if (calc(containerVolume, desiredWeight, lm) > calc(containerVolume, desiredWeight, rm)) {
                l = lm;
            } else {
                r = rm;
            }
        }
        return calc(containerVolume, desiredWeight, l);*/
        double r = calc2(containerVolume, desiredWeight, (double) desiredWeight[0] / containerVolume[0]);
        for (int i = 1; i < containerVolume.length; i++) r = Math.min(r, calc2(containerVolume, desiredWeight, (double) desiredWeight[i] / containerVolume[i]));
        return r;
    }

    private double calc2(int[] containerVolume, int[] desiredWeight, double v) {
        double r = 0;
        for (int i = 0; i < containerVolume.length; i++) r += Math.abs(containerVolume[i] * v - desiredWeight[i]);
        return r;
    }

    private double calc(int[] containerVolume, int[] desiredWeight, BigDecimal m) {
        BigDecimal r = BigDecimal.ZERO;
        for (int i = 0; i < containerVolume.length; i++) {
            r = r.add(m.multiply(BigDecimal.valueOf(containerVolume[i])).subtract(BigDecimal.valueOf(desiredWeight[i])).abs());
        }
        return r.doubleValue();
    }
}
