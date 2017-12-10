package myArithmeticCoding;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BigDecimal left = BigDecimal.ZERO;
    private static BigDecimal right = BigDecimal.valueOf(1);
    private static Integer textSize = 0;
    private static Segment segment = new Segment(BigDecimal.ZERO, BigDecimal.ONE);

    public static void main(String[] args) {
        File inputFile = new File("in.txt");
        File outputFile = new File("out.txt");
        File outputFile2 = new File("out2.txt");

        Map<String, Integer> freq = new HashMap<>();

        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(inputFile))) {
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                String symbol = String.valueOf((char) i);
                freq.putIfAbsent(symbol, 0);
                freq.put(symbol, freq.get(symbol) + 1);
                textSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        freq = freq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((o1, o2) -> o2 - o1))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        Map<String, Segment> segments = new HashMap<>();

        final BigDecimal[] start = {BigDecimal.ZERO};
        freq.forEach((key, value) -> {
            segments.put(key, new Segment(start[0], start[0].add(BigDecimal.valueOf(value).divide(BigDecimal.valueOf(textSize)))));
            start[0] =start[0].add(BigDecimal.valueOf(value).divide(BigDecimal.valueOf(textSize), new MathContext(5)));
        });

        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(inputFile))) {
            int i;
            int k = 0;
            System.out.println("ENCODE");

            while ((i = bufferedInputStream.read()) != -1) {
                String symbol = String.valueOf((char) i);
                BigDecimal rangeR = segments.get(symbol).getRight();
                BigDecimal rangeL = segments.get(symbol).getLeft();
                BigDecimal leftBuf = (right.subtract(left)).multiply(rangeL).add(left);
                BigDecimal rightBuf = (right.subtract(left)).multiply(rangeR).add(left);
                left = leftBuf;
                right = rightBuf;
                k++;
                System.out.println((double) k*100/textSize + "%");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(outputFile2))) {
            BigInteger theInt = left.unscaledValue();
            byte[] arr = theInt.toByteArray();
            System.out.println((double) arr.length * 8  / textSize);
            bufferedWriter.write(arr);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DECODE:");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < textSize; i++) {
                Map.Entry<String, Segment> entry = segments.entrySet().stream()
                        .filter(stringSegmentEntry -> left.compareTo(stringSegmentEntry.getValue().getLeft()) >= 0 && left.compareTo(stringSegmentEntry.getValue().getRight()) < 0)
                        .findFirst().get();
                left = (left.subtract(entry.getValue().getLeft())).divide(entry.getValue().getRight().subtract(entry.getValue().getLeft()), RoundingMode.DOWN);
//                System.out.println((double) i*100/textSize + "%");

                bufferedWriter.write(entry.getKey());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
