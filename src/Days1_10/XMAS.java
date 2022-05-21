package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XMAS {
    public static void main(String[] args) throws FileNotFoundException {
        String input =  "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576";
        ArrayList<Long> numbers = readNumbers("xmas.txt");
        long wrong = findInvalid(numbers);
        System.out.println(wrong);
        long weakness = fix(numbers, wrong);
        System.out.println(weakness);
    }

    public static long fix(ArrayList<Long> numbers, long broken) {
        int pos = numbers.indexOf(broken);
        List<Long> contiguous = new ArrayList<>();

        outerloop:
        for(int i = 0; i < pos; i++) {
            long sum = 0;
            contiguous = new ArrayList<>();
            for(int j = i; j < pos; j++) {
                sum += numbers.get(j);
                if(sum > broken) break;

                contiguous.add(numbers.get(j));
                if(sum == broken) {
                    System.out.println(contiguous);
                    break outerloop;
                }
            }
        }
        if(contiguous.isEmpty()) return -1;

        //long n = contiguous.stream().mapToLong((i) -> i.longValue()).sum();
        //System.out.println(n + "=" + broken);

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i = 0; i < contiguous.size(); i++) {
            if(contiguous.get(i) > max) max = contiguous.get(i);
            if(contiguous.get(i) < min) min = contiguous.get(i);
        }
        return min + max;
    }

    public static long findInvalid(ArrayList<Long> numbers) {
        for(int i = 25; i < numbers.size(); i++) {
            boolean correct = false;
            long current = numbers.get(i);
            for(int j = i-25; j < i-1; j++) {
                for(int k = j + 1; k < i; k++) {
                    if(numbers.get(j) + numbers.get(k) == current) {
                        correct = true;
                        break;
                    }
                }
                if(correct) break;
            }
            if(!correct) return current;
        }
        return -1;
    }

    public static ArrayList<Long> readNumbers(String input) throws FileNotFoundException {
        ArrayList<Long> numbers = new ArrayList<>();
        Scanner sc = new Scanner(new File(input));

        while(sc.hasNextLine()) {
            long num = Long.parseLong(sc.nextLine());
            numbers.add(num);
        }
        return numbers;
    }
}
