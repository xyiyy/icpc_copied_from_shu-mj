package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class SquareVerification {
    public int calculateCode(int x) {
        return Algo.max(Integer.toString(x * x).toCharArray()) - '0';
    }
}
