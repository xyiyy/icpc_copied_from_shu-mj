package main;

import com.shu_mj.tpl.Algo;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.util.Arrays;
import java.util.Scanner;

public class TaskIChecker implements Checker {
    public TaskIChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner in = new Scanner(input);
        Scanner out = new Scanner(actualOutput);
        int n = in.nextInt();
        int[] is = new int[n + 1];
        int[] js = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            is[i] = in.nextInt();
            js[i] = i;
        }
        int k = out.nextInt();
        for (int i = 0; i < k; i++) {
            int l = out.nextInt();
            int r = out.nextInt();
            Algo.reverse(js, l, r + 1);
        }
        return Arrays.equals(is, js) ? Verdict.OK : Verdict.WA;
    }
}
