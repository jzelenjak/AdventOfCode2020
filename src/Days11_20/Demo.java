import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.LongStream;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException {
        String input = new String("939\n" +
                "1789,37,47,1889");
        //int count = calculate("shuttles.txt");
        //System.out.println(count);

        long count = calculate2("shuttles.txt");
        //System.out.println(count);
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
        //find max
        int max = Integer.MIN_VALUE;
        int offsetOfMax = 0;
        int indexOfMax = 0;
        for(int i = 0; i < timetable.size(); i++) {
            if(timetable.get(i)[0] > max) {
                max = timetable.get(i)[0];
                offsetOfMax = timetable.get(i)[1];
                indexOfMax = i;
            }
        }


        //only use threads in the final problem

        long product = 1;
        for(int[] bus : timetable) product *= bus[0];
        for(long i = 10000000000000L; i < product; i+= 500000000000L) {
            new CheaterThread(i, timetable, indexOfMax, max, offsetOfMax).start();
        }
        return 0;
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
