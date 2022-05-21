package Days11_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Shuttles {
    public static void main(String[] args) throws FileNotFoundException {
        String input = new String("939\n" +
                "7,13,x,x,59,x,31,19");
        //int count = calculate("shuttles.txt");
        //System.out.println(count);

        long count = calculate2("shuttles.txt");
        System.out.println(count);
        //Long start = 100000000000003L;
        //System.out.println(start % 19 == 0);
    }

    private static long calculate2(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        sc.nextLine();      //discard timestamp
        int counter = 0;
        List<int[]> timetable = new ArrayList<>();
        String[] info = sc.nextLine().split(",");

        //create a list of buses: [id, offset]
        for(String bus : info) {
            if(bus.equals("x")) counter++;
            else {
                timetable.add(new int[]{Integer.parseInt(bus), counter++});
            }
        }
        for(int[] entry : timetable) System.out.println(Arrays.toString(entry));
        return findTimestamp(timetable);
    }

    private static long findTimestamp(List<int[]> timetable) {
        int max = Integer.MIN_VALUE;
        int offsetOfMax = 0;
        int indexOfMax = 0;
        //for(int i = 0; i < timetable.size(); i++) {
            //if(timetable.get(i)[0] > max) {
                //max = timetable.get(i)[0];
                //offsetOfMax = timetable.get(i)[1];
                //indexOfMax = i;
            //}
        //}

        OptionalLong optional;
        long time = LongStream.range(100000000000000L, 100000000000000L*50)
                .filter(i -> (i + timetable.get(2)[1])%timetable.get(2)[0] == 0
                                && (i + timetable.get(7)[1])%timetable.get(7)[0] == 0)
                .filter(i -> (i + timetable.get(1)[1])%timetable.get(1)[0] == 0
                            && i % timetable.get(0)[0] == 0)
                .filter(i -> (i + timetable.get(3)[1])%timetable.get(3)[0] == 0
                         && (i + timetable.get(4)[1])%timetable.get(4)[0] == 0)
                .filter(i -> (i + timetable.get(5)[1])%timetable.get(5)[0] == 0)
                .filter(i -> (i + timetable.get(6)[1])%timetable.get(6)[0] == 0)
                .filter(i -> (i + timetable.get(8)[1])%timetable.get(8)[0] == 0).findFirst().orElse(-1);

        System.out.println(time);
        /*long stamp = ((Long.MAX_VALUE - max) / max + 1) * max;
        System.exit(0);
        while(true) {
            boolean success = true;
            System.out.println(stamp);
            for (int i = 0; i < indexOfMax; i++) {
                if((stamp - offsetOfMax + timetable.get(i)[1]) % timetable.get(i)[0] == 0) {
                    continue;
                } else {
                    success = false;
                    stamp = Math.addExact(stamp, -max);
                    break;
                }
            }

            if(!success) continue;

            for(int i = indexOfMax + 1; i < timetable.size(); i++) {
                if((stamp + timetable.get(i)[1] - offsetOfMax) % timetable.get(i)[0] == 0) {
                    continue;
                } else {
                    success = false;
                    stamp = Math.addExact(stamp, -max);
                    break;
                }
            }
            if(success) return stamp - offsetOfMax;
        }*/
        return 1;
    }


    /** part 1*/
    private static int calculate(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        int timestamp = Integer.parseInt(sc.nextLine());    //read timestamp
        int perf_id = 0;                                    //initial value
        int minWait = Integer.MAX_VALUE;                    //initial value
        String[] info = sc.nextLine().split(",");     // array of bus ids
        //System.out.println(Arrays.toString(info));

        for(String id_str : info) {
            if(id_str.equals("x")) continue;                //no need to process
            int id = Integer.parseInt(id_str);              //get the numeric id
            if(timestamp % id == 0) {                       //is exactly at that point
                minWait = 0;
                perf_id = id;
            } else {
                int waitingTime = (timestamp / id + 1) * id - timestamp; //waiting time is: ceil * id - timestamp
                if(waitingTime < minWait) {
                    minWait = waitingTime;
                    perf_id = id;
                }
            }
        }
        System.out.println(perf_id + ", waiting time: " + minWait);
        return perf_id * minWait;
    }
}
