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
        int n = 45;
        List<E> list = new ArrayList<E>();
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                list.add(new E(i, j));
            }
        }
        Collections.shuffle(list);
        for (E e : list) {
            out.print(e.u + ",");
        }
        out.println();
        for (E e : list) {
            out.print(e.v + ",");
        }
        out.println();
    }
    class E {
        int u;
        int v;

        E(int u, int v) {
            this.u = u;
            this.v = v;
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
