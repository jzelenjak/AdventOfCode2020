package Days11_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BitMask {
    public static void main(String[] args) throws FileNotFoundException {
        String input = new String("mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1");
        //long sum = readIn1("mems.txt");
        //System.out.println(sum);
        //binaryCount();

        //part 2
        long sum2 = readIn2("mems.txt");
        System.out.println(sum2);
    }

    private static long readIn2(String filename) throws FileNotFoundException {
        HashMap<Long, Long> memory = new HashMap<>();
        ArrayList<String> binaryCounter = binaryCount();
        Scanner sc = new Scanner(new File(filename));
        String mask = "";

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith("mask")) mask = line.substring(7);
            else if (line.startsWith("mem")) {
                String[] str = line.split(" = ");
                long addr = Long.parseLong(str[0].substring(4,str[0].length()-1));
                long value = Long.parseLong(str[1]);
                updateMemory2(mask, addr, value, memory, binaryCounter);
            }
        }
        System.out.println(memory.toString());
        return calculateSum(memory);
    }

    private static void updateMemory2(String mask, long addr, long value,
                                      HashMap<Long,Long> memory, ArrayList<String> binaryCounter) {
        String binValue = Long.toBinaryString(addr);
        int zeros = Long.numberOfLeadingZeros(addr) - 28;
        //if(addr == 0) zeros--;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < zeros; i++) sb.append("0");
        sb.append(binValue);
        ArrayList<Integer> flPos = new ArrayList<>();

        for(int i = 0; i < mask.length(); i++) {
            if(mask.charAt(i) == '0') continue;
            else if(mask.charAt(i) == '1') {
                sb.replace(i, i+1, "1");
            } else if(mask.charAt(i) == 'X') {
                sb.replace(i, i+1, "0");
                flPos.add(i);
            }
        }
        //Collections.reverse(flPos);
        //long addr = Long.parseLong(sb.toString(), 2);
        memory.put(Long.parseLong(sb.toString(), 2), value);

        updateAllMemoryAddresses(memory, flPos, binaryCounter, value, sb);
    }

    private static void updateAllMemoryAddresses(HashMap<Long, Long> memory, ArrayList<Integer> flPos,
                                                 ArrayList<String> binaryCounter, long value, StringBuilder sb) {
        //number of combinations minus 1 (for 0)
        int times = (int) Math.pow(2, flPos.size());

        //iterate over all possible combinations excl zeros
        for(int i = 1; i < times; i++) {
            //get the "current binary count(for replacement)"
            String current = binaryCounter.get(i);
            //position of digit in the binary string
            int pos = 0;
            //replace digits at the floating positions with the combination of digits in the binary string
            for(int j = flPos.size() - 1; j >= 0; j--) {
                sb.replace(flPos.get(j), flPos.get(j)+1, String.valueOf(current.charAt(current.length() - (pos++) - 1)));
            }
            memory.put(Long.parseLong(sb.toString(), 2), value);
        }
    }

    private static ArrayList<String> binaryCount() {
        int max = (int) Math.pow(2, 10);
        ArrayList<String> binaryCounter = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 36; i++) sb.append("0");
        binaryCounter.add(sb.toString());

        for(int i = 1; i < max; i++) {
            String binValue = Long.toBinaryString(i);
            int zeros = Long.numberOfLeadingZeros(i) - 28;

            sb = new StringBuilder();
            for(int j = 0; j < zeros; j++) sb.append("0");
            sb.append(binValue);
            binaryCounter.add(sb.toString());
        }
        //System.out.println(binaryCounter);
        return binaryCounter;
    }

    /**
     * part 1
     */
    private static long readIn1(String filename) throws FileNotFoundException {
        HashMap<Long, Long> memory = new HashMap<>();
        Scanner sc = new Scanner(new File(filename));
        String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith("mask")) mask = line.substring(7);
            else if (line.startsWith("mem")) {
                String[] str = line.split(" = ");
                long addr = Long.parseLong(str[0].substring(4,str[0].length()-1));
                long value = Long.parseLong(str[1]);
                updateMemory1(mask, addr, value, memory);
            }
        }
        System.out.println(memory.toString());
        return calculateSum(memory);
    }

    private static void updateMemory1(String mask, long addr, long value, HashMap<Long,Long> memory) {
        String binValue = Long.toBinaryString(value);
        int zeros = Long.numberOfLeadingZeros(value) - 28;
        if(value == 0) zeros--;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < zeros; i++) sb.append("0");
        sb.append(binValue);
        //sb.append("0");

        for(int i = 0; i < mask.length(); i++) {
            if(mask.charAt(i) == 'X') continue;
            else if(mask.charAt(i) == '1') {
                sb.replace(i, i+1, "1");
            } else if(mask.charAt(i) == '0') {
                sb.replace(i, i+1, "0");
            }
        }
        //sb.deleteCharAt(mask.length());

        long val = Long.parseLong(sb.toString(), 2);
        memory.put(addr, val);
    }

    private static long calculateSum(HashMap<Long, Long> memory) {
        long sum = 0;
        for(long val : memory.values()) sum += val;
        return sum;
    }
}
