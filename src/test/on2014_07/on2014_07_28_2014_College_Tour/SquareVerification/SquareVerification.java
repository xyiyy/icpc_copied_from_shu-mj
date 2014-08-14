package test.on2014_07.on2014_07_28_2014_College_Tour.SquareVerification;


import java.util.*;
import com.shu_mj.tpl.Algo;

public class SquareVerification {
    public int calculateCode(int x) {
        return Algo.max(Integer.toString(x * x).toCharArray()) - '0';
    }
}
