package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class CircuitsConstruction {
    public int maximizeResistance(String circuit, int[] conductors) {
        char[] cs = circuit.toCharArray();
        crt = 0;
        Node root = build(cs);
        int num = dfs(root);
        Arrays.sort(conductors);
        int sum = 0;
        Algo.reverse(conductors);
        for (int i = 0; i < num; i++) {
            sum += conductors[i];
        }
        return sum;
    }

    int dfs(Node root) {
        if (root.type == 'X') return 1;
        if (root.type == 'A') return dfs(root.left) + dfs(root.right);
        return Math.max(dfs(root.left), dfs(root.right));
    }
    int crt;
    Node build(char[] cs) {
        if (cs[crt] == 'X') return new Node(cs[crt++], null, null);
        return new Node(cs[crt++], build(cs), build(cs));
    }
    class Node {
        char type;
        Node left, right;

        Node(char type, Node left, Node right) {
            this.type = type;
            this.left = left;
            this.right = right;
        }
    }
}
