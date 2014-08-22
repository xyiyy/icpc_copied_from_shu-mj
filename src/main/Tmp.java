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
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i : list) out.print(i + " ");
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
