package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Joltage {
    public static void main(String[] args) throws FileNotFoundException {
        //ArrayList<Integer> list = new ArrayList<>();
        //for(int i = 1000; i >= 0; i--) list.add(i);
        //quickSort(list);
        //System.out.println(list);
        String input1 = new String("16\n" +
                "10\n" + "15\n" + "5\n" + "1\n" + "11\n" + "7\n" + "19\n" + "6\n" + "12\n" + "4");
        String input2 = new String("28\n" + "33\n" + "18\n" + "42\n" + "31\n" + "14\n" + "46\n" + "20\n" + "48\n" + "47\n" + "24\n" + "23\n" + "49\n" + "45\n" + "19\n" + "38\n" + "39\n" + "11\n" + "1\n" + "32\n" + "25\n" + "35\n" + "8\n" + "17\n" + "7\n" + "9\n" + "4\n" + "2\n" + "34\n" + "10\n" + "3");
        ArrayList<Integer> adapters = readAdapters("adapters.txt");
        quickSort(adapters);

        //add built-in adapter
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        System.out.println(adapters);

        //System.out.println(countDifferences(adapters));

        //part 2
        ArrayList<Character> removables = findRemovables(adapters);
        System.out.println(removables);
        int notTriples = 0;
        int triples = 0;

        //count triples
        for(int i = 1; i < removables.size() - 2 ; i++) {
            if (removables.get(i) == 't' && removables.get(i + 1) == 't' && removables.get(i + 2) == 't') triples++;
        }

        //count pairs as 2 not triples
        for(int i = 1; i < removables.size() - 2; i++) {
            if(removables.get(i) == 't' && removables.get(i+1) == 't' && removables.get(i+2) != 't'  && removables.get(i-1) != 't') {
                notTriples += 2;
            }
        }

        //count singles as 1 not triple
        for(int i = 1; i < removables.size()-1; i++) {
            if (removables.get(i) == 't' && removables.get(i-1) != 't' && removables.get(i+1) != 't') notTriples++;
        }

        System.out.println(triples);
        System.out.println(notTriples);

        //the result is 7^(#triples) * 2^(not triples)
        long result = (long) Math.pow(7, triples) * (long) Math.pow(2, notTriples);
        System.out.println(result);

    }

    public static ArrayList<Character> findRemovables(List<Integer> adapters) {
        ArrayList<Character> removables = new ArrayList<>();
        removables.add('f');
        for(int i = 1; i < adapters.size() - 1; i++) {
            if(adapters.get(i) - adapters.get(i-1) == 1 && adapters.get(i+1) - adapters.get(i) == 1) removables.add('t');
            else removables.add('f');
        }
        removables.add('f');
        return removables;
    }

    public static int countDifferences(ArrayList<Integer> adapters) {
        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        for(int i = 0; i < adapters.size() - 1; i++) {
            if(adapters.get(i + 1) - adapters.get(i) == 1) countOne++;
            else if(adapters.get(i + 1) - adapters.get(i) == 3) countThree++;
            else if(adapters.get(i + 1) - adapters.get(i) == 2) countTwo++;
        }
        System.out.println("Differences of one: " + countOne + "\nDifferences of three: " + countThree + "\nDifferences of two: " + countTwo);
        return countOne * countThree;
    }

    public static ArrayList<Integer> readAdapters(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        ArrayList<Integer> adapters = new ArrayList<>();
        adapters.add(0);
        while(sc.hasNextLine()) {
            adapters.add(Integer.parseInt(sc.nextLine()));
        }
        sc.close();
        return adapters;
    }

    public static void quickSort(List<Integer> list) {
        if(list.size() <= 1) return;

        Random r = new Random();
        int p = r.nextInt(list.size());
        int pivo = list.get(p);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> equal = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (Integer integer : list) {
            if (integer < pivo) left.add(integer);
            else if (integer == pivo) equal.add(integer);
            else right.add(integer);
        }

        quickSort(left);
        quickSort(right);
        for(int i = 0; i < left.size(); i++) list.set(i, left.get(i));
        for(int i = 0; i < equal.size(); i++) list.set(i + left.size(), equal.get(i));
        for(int i = 0; i < right.size(); i++) list.set(i + left.size() + equal.size(), right.get(i));
    }
}
