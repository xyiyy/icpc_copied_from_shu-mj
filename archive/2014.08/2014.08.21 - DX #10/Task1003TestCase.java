package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task1003TestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        return new Test(input.toString(), output.toString());
    }
}
