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
        P[] ps = new P[12];
        ps[0] = new P(0, 0);
        ps[1] = new P(1, 0);
        ps[2] = new P(2, 0);
        ps[3] = new P(2, 1);
        ps[4] = new P(1, 1);
        ps[5] = new P(0, 1);
        for (int i = 6; i < 12; i++) ps[i] = ps[i < 9 ? i - 6 : i - 9];
        ps = P.convexHull(ps);
        Algo.debug(ps);
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
