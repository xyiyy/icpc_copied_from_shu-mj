package main;

import com.shu_mj.datetime.DateTime;
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
        Algo.debug(DateTime.days(2000, 1, 1));
        Algo.debug(DateTime.days(2000, 1, 2));
        Algo.debug(DateTime.days(1999, 1, 1));
        Algo.debug(DateTime.days(1900, 1, 1));
        Algo.debug(DateTime.days(1900, 1, 2));
        Algo.debug(DateTime.days(1000, 1, 2));
        Algo.debug(DateTime.days(1000, 1, 2));
        Algo.debug(DateTime.days(1, 1, 1));
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
