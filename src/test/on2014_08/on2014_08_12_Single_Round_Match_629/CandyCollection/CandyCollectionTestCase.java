package test.on2014_08.on2014_08_12_Single_Round_Match_629.CandyCollection;



import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.task.Task;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class CandyCollectionTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<NewTopCoderTest>();
        Random r = new Random();
        for (int i = 0; i < 0; i++) {
            int n = 4;
            int[] ty1 = get(n);
            int[] ty2 = get(n);
            while (someSame(ty1, ty2)) ty2 = get(n);
            int[] nm1 = new int[n];
            int[] nm2 = new int[n];
            for (int j = 0; j < n; j++) {
                nm1[j] = r.nextInt(10) + 1;
                nm2[j] = r.nextInt(10) + 1;
            }
            list.add(new NewTopCoderTest(new Object[]{ ty1, nm1, ty2, nm2 }, solve(ty1, nm1, ty2, nm2)));
        }
        return list;
    }

    private boolean someSame(int[] ty1, int[] ty2) {
        for (int i = 0; i < ty1.length; i++) if (ty1[i] == ty2[i]) return true;
        return false;
    }

    private int[] get(int n) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return Algo.unBox(list.toArray(new Integer[0]));
    }

    public int solve(int[] Type1, int[] Number1, int[] Type2, int[] Number2) {
        int n = Type1.length;
        boolean[] markShape = new boolean[n];
        int[] seqShapeNumber = new int[n];
        int[] seqShapePrevNumber = new int[n];
        int[] seqFlavor = new int[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (!markShape[i]) {
                markShape[i] = true;
                int nseq = 1;
                seqFlavor[0] = Type1[i];
                seqShapeNumber[0] = Number1[i];
                seqShapePrevNumber[0] = Number2[i];
                outer: while (seqFlavor[nseq - 1] != Type2[i]) {
                    for (int j = 0; j < n; ++j) if (!markShape[j] && (Type1[j] == seqFlavor[nseq - 1] || Type2[j] == seqFlavor[nseq - 1])) {
                        markShape[j] = true;
                        if (Type1[j] == seqFlavor[nseq - 1]) {
                            seqFlavor[nseq] = Type2[j];
                            seqShapeNumber[nseq] = Number2[j];
                            seqShapePrevNumber[nseq] = Number1[j];
                        } else {
                            seqFlavor[nseq] = Type1[j];
                            seqShapeNumber[nseq] = Number1[j];
                            seqShapePrevNumber[nseq] = Number2[j];
                        }
                        ++nseq;
                        continue outer;
                    }
                    throw new RuntimeException();
                }
                res += handle(nseq, seqShapeNumber, seqShapePrevNumber);
            }
        }
        return res;
    }

    private int handle(int nseq, int[] seqShapeNumber, int[] seqShapePrevNumber) {
        int res = Integer.MAX_VALUE;
        for (boolean coverOverBoundary : new boolean[]{false, true}) {
            int atLeast = coverOverBoundary ? seqShapeNumber[0] + 1 : 0;
            int[] best = new int[2];
            best[0] = atLeast;
            best[1] = Math.max(atLeast, seqShapePrevNumber[0] + 1);
            for (int i = 1; i < nseq; ++i) {
                int[] nbest = new int[2];
                nbest[0] = Math.min(best[0] + seqShapeNumber[i] + 1, best[1]);
                nbest[1] = Math.min(best[0] + Math.max(seqShapeNumber[i], seqShapePrevNumber[i]) + 1, best[1] + seqShapePrevNumber[i] + 1);
                best = nbest;
            }
            int got = coverOverBoundary ? best[0] : best[1];
            res = Math.min(res, got);
        }
        return res;
    }
}
