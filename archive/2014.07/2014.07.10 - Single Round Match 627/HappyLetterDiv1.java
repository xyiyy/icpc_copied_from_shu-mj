package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class HappyLetterDiv1 {
    public String getHappyLetters(String letters) {
        String res = "";
        for (char c = 'a'; c <= 'z'; c++) {
            if (fit(c, letters)) res = res + c;
        }
        return res;
    }

    private boolean fit(char c, String s) {
        if (s.indexOf(c) == -1) return false;
        int[] is = new int[26];
        for (int i = 0; i < s.length(); i++) is[s.charAt(i) - 'a']++;
        PriorityQueue<Item> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) if (is[i] != 0) {
            pq.add(new Item((char) (i + 'a'), is[i]));
        }
        while (pq.size() > 1) {
//            Algo.debug(pq);
            Item a = pq.poll();
            Item b = pq.poll();
            Item cp = null;
            if ((a.c == c || b.c == c) && !pq.isEmpty()) {
                if (a.c == c) {
                    cp = a;
                    a = pq.poll();
                } else {
                    cp = b;
                    b = pq.poll();
                }
            }
            a.cnt --;
            b.cnt --;
            if (a.cnt != 0) pq.add(a);
            if (b.cnt != 0) pq.add(b);
            if (cp != null) pq.add(cp);
        }
        return !pq.isEmpty() && pq.peek().c == c;
    }

    class Item implements Comparable<Item> {
        char c;
        int cnt;

        Item(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Item o) {
            return o.cnt - cnt;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "c=" + c +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    private boolean haveTwo(int[] is) {
        return cnt(is) == 2;
    }

    private int cnt(int[] is) {
        int r = 0;
        for (int i : is) if (i != 0) r++;
        return r;
    }

    private boolean haveThree(int[] is) {
        return cnt(is) >= 3;
    }
}
