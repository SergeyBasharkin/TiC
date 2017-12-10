package utils;

import java.math.BigDecimal;
import java.math.MathContext;

public class Util {
    public static BigDecimal binom(int n, int k, BigDecimal p){
        return fact(n).divide(fact(n-k).multiply(fact(k)), new MathContext(64)).multiply(BigDecimal.valueOf(Math.pow(p.doubleValue(),k))).multiply(BigDecimal.valueOf(Math.pow(1 - p.doubleValue(),n-k)));
    }

    public static BigDecimal fact(int bigInteger){
        BigDecimal fac = BigDecimal.ONE;
        for (int i = 1; i <= bigInteger ; i++) {
            fac = fac.multiply(BigDecimal.valueOf(i));
        }
        return fac;
    }
}
