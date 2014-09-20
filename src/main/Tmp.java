package main;

import com.shu_mj.ds.Palindrome;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jun on 6/13/2014.
 */
public class Tmp {

    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;

    void run() {
        new Prob8(in, out).run();
//        new Prob9(in, out).run();
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

class Prob9 {
    Scanner in;
    PrintStream out;

    Prob9(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    void run() {
        new P0().start();
        new P1().start();
        new P2().start();
        new P3().start();
        new P4().start();
        new P5().start();
        new P6().start();
    }

    double a;
    double b;
    double c;
    double d1;
    double d2;
    double d3;
    double d4;
    double x1;
    double x2;
    Semaphore s0 = new Semaphore(2);
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(0);
    Semaphore s4 = new Semaphore(0);
    Semaphore s5 = new Semaphore(0);
    Semaphore s6 = new Semaphore(0);

    class P0 extends Thread {
        public void run() {
            for (;;) {
                s0.waitFor();
                s0.waitFor();
                a = in.nextDouble();
                b = in.nextDouble();
                c = in.nextDouble();
                s1.signal();
                s2.signal();
                s3.signal();
            }
        }
    }

    class P1 extends Thread {
        public void run() {
            for (;;) {
                s1.waitFor();
                d1 = 4 * a * c;
                s4.signal();
            }
        }
    }
    class P2 extends Thread {
        public void run() {
            for (;;) {
                s2.waitFor();
                d2 = b * b;
                s4.signal();
            }
        }
    }
    class P3 extends Thread {
        public void run() {
            for (;;) {
                s3.waitFor();
                d3 = 2 * a;
                s5.signal();
                s6.signal();
            }
        }
    }
    class P4 extends Thread {
        public void run() {
            for (;;) {
                s4.waitFor();
                s4.waitFor();
                d4 = Math.sqrt(d2 - d1);
                s5.signal();
                s6.signal();
            }
        }
    }
    class P5 extends Thread {
        public void run() {
            for (;;) {
                s5.waitFor();
                s5.waitFor();
                x1 = (-b + d4) / d3;
                out.println("x1 = " + x1);
                s0.signal();
            }
        }
    }
    class P6 extends Thread {
        public void run() {
            for (;;) {
                s6.waitFor();
                s6.waitFor();
                x2 = (-b - d4) / d3;
                out.println("x2 = " + x2);
                s0.signal();
            }
        }
    }
}

class Prob8 {

    Scanner in;
    PrintStream out;

    Prob8(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    void run() {
        new Input().start();
        new Compute().start();
        new Output().start();
    }

    Integer buf = -1;
    Semaphore s1 = new Semaphore(1);
    Semaphore s2 = new Semaphore(0);
    Semaphore s3 = new Semaphore(0);
    Semaphore s4 = new Semaphore(0);

    class Input extends Thread {
        public void run() {
            for (;;) {
                s1.waitFor();
                buf = in.nextInt();
                s2.signal();
                s2.signal();
            }
        }
    }

    class Compute extends Thread {
        public void run() {
            for (;;) {
                s2.waitFor();
                int x = buf * buf;

                s3.waitFor();
                buf = x;
                s4.signal();
            }
        }
    }

    class Output extends Thread {
        public void run() {
            for (;;) {
                s2.waitFor();
                out.println(buf);
                s3.signal();

                s4.waitFor();
                out.println(buf);
                s1.signal();
            }
        }
    }
}

class Semaphore {
    AtomicInteger s;

    Semaphore(int i) {
        this.s = new AtomicInteger(i);
    }

    synchronized void waitFor() {
        if (s.decrementAndGet() < 0) try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void signal() {
        if (s.getAndIncrement() < 0) this.notify();
    }
}
