package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;

import java.io.*;
import java.util.*;

/**
 * Created by Jun on 6/13/2014.
 */
public class Tmp {

    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;

    void run() {
        int n = 15;
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int d = 1;
                for (int k = i; k <= j; k++) {
                    is[k] += d;
                    d *= -1;
                }
            }
        }
        Algo.debug(is);
    }

    public static void main(String[] args) {
//        try {
//            System.setOut(new PrintStream("out.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        new Tmp().run();
        new Tmp().run();
    }
}
