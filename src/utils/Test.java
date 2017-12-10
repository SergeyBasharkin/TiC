package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        List<BigDecimal> p = new LinkedList<>();
        for (int i = 0; i <= 70; i++) {
            p.add(Util.binom(71,i, BigDecimal.valueOf(0.1)));
        }
        p.forEach(bigDecimal -> System.out.println(bigDecimal.toPlainString()));

        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < p.size() ; i++) {
            sum = sum.subtract(p.get(i).multiply(BigDecimal.valueOf(Math.log(p.get(i).doubleValue())).divide(BigDecimal.valueOf(Math.log(2)), new MathContext(64, RoundingMode.DOWN))));
        }
        System.out.println(sum);

        double sumD = 0.0;
        for (int i = 0; i <p.size() ; i++) {
            System.out.println(p.get(i).toPlainString());
            sumD += p.get(i).doubleValue();
        }
        System.out.println(sumD);
//
//       double [] p1 = new double[]{0.51,0.31,0.12,0.06};
//
//       sumD = 0;
//        for (int i = 0; i < 4; i++) {
//            sumD -= p1[i]*((double) Math.log(p1[i])/Math.log(2));
//        }
//        System.out.println(sumD);
//        System.out.println(sumD*500);
    }
}
