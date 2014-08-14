package test.on2014_08.on2014_08_08_Codeforces_Round__260__Div__1_.B___A_Lot_of_Games;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            insert(root, in.next());
        }
        boolean f0 = false;
        for (Node node : root.cs) if (node != null && dfs(node, 0)) f0 = true;
        boolean f1 = false;
        for (Node node : root.cs) if (node != null && dfs(node, 1)) f1 = true;
        Algo.debug(f0, f1);
        if (f0 && f1 || f0 && k % 2 == 1 || f1 && k % 2 == 1) {
            out.println("First");
        } else {
            out.println("Second");
        }
    }

    private boolean dfs(Node root, int c) {
        if (end(root)) {
            return c == 0;
        } else {
            for (Node n : root.cs) if (n != null) {
                if (dfs(n, c)) return false;
            }
            return true;
        }
    }

    private boolean end(Node root) {
        for (Node n : root.cs) if (n != null) return false;
        return true;
    }

    private void insert(Node root, String s) {
        for (char c : s.toCharArray()) {
            if (root.cs[c - 'a'] == null) root.cs[c - 'a'] = new Node();
            root = root.cs[c - 'a'];
        }
        root.ac = true;
    }

    class Node {
        Node[] cs = new Node[26];
        boolean ac;
    }
}
