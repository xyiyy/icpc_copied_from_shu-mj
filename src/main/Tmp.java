package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.ds.Hash;
import com.shu_mj.geo.P;
import com.shu_mj.graph.Bridge;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;

/**
 * Created by Jun on 6/13/2014.
 */
public class Tmp {

    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;
    void run() {
        Num.primeTable(100, new ArrayList<Integer>());
        for (int a = 1; a <= 100; a++) {
            for (int b = 1; b <= 100; b++) {
                for (int c = 1; c <= 100; c++) {
                    long r1 = calc1(a, b, c);
                    long r2 = clac2(a, b, c);
                    if (r1 != r2) Algo.debug(a, b, c, r1, r2);
                }
            }
        }
    }

    private long clac2(int a, int b, int c) {
        if (b < Num.phi(c)) return calc1(a, b, c);
        return Num.pow(a, b % Num.phi(c) + Num.phi(c), c);
    }

    private long calc1(int a, int b, int c) {
        return Num.pow(a, b, c);
    }

    public static void main(String[] args) {
//        try {
//            System.setOut(new PrintStream("out.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        new Tmp().run();
    }
}
