package utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Task2 {

    public static void main(String[] args) {
        StringBuilder text=new StringBuilder("айм блю дабуди дабудай дабуди дабудай дабуди дабудай ");
        StringBuilder text2=new StringBuilder("айм длю дадуди дадудай дадуди дадудай дадуди дадудай ");

        Map<String, Double> p1 = new HashMap<>();
        Map<String, Double> p2 = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            p1.putIfAbsent(String.valueOf(text.charAt(i)), 1d);
            p1.put(String.valueOf(text.charAt(i)),p1.get(String.valueOf(text.charAt(i))) + 1);
        }
        for (int i = 0; i < text2.length(); i++) {
            p2.putIfAbsent(String.valueOf(text2.charAt(i)), 1d);
            p2.put(String.valueOf(text2.charAt(i)),p2.get(String.valueOf(text2.charAt(i))) + 1);
        }
        System.out.println();
        HashMap<String, Double> pZU = new HashMap<>();

        p1.forEach((s, aDouble) -> {
            p2.forEach((s1, aDouble1) -> {
                if (s.equals(s1)) {
                    pZU.put(s1 + s, p1.get(s1)/ text2.length());
                }
                if (s.equals("б") && s1.equals("д")){
                    pZU.put(s1+s, p1.get(s)/text2.length());
                }
            });
        });
        System.out.println();

        final double[] sum = {0};
        p2.forEach((s, aDouble) ->{
            p1.forEach((s1, aDouble1) -> {
                if (pZU.get(s+s1)!= null)sum[0] -= pZU.get(s+s1)*(Math.log((double) pZU.get(s+s1)/(p1.get(s1)*p2.get(s)))/Math.log(2));
            });
        });

        System.out.println(sum[0]);
    }
}
