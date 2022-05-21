package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Seats {
    public static void main(String[] args) throws FileNotFoundException {
        //int maxID = findMaxID("seats.txt");
        //System.out.println(maxID);

        List<Integer> ids = findAllIDs("seats.txt");
        Collections.sort(ids);
        System.out.println(ids);
        int id = findYourID(ids);
        System.out.println(id);

    }

    public static int findYourID(List<Integer> ids) {
        for(int i = 0; i < ids.size() - 1; i++) {
            if(ids.get(i+1) - ids.get(i) != 1) return ids.get(i) + 1;
        }
        return -1;
    }

    public static List<Integer> findAllIDs(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        List<Integer> ids = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            int id = findRow(line.substring(0,7)) * 8 + findSeat(line.substring(7));
            ids.add(id);
        }
        return ids;
    }

    public static int findMaxID(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        int max = Integer.MIN_VALUE;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            int id = findRow(line.substring(0,7)) * 8 + findSeat(line.substring(7));
            if(id > max) max = id;
        }
        return max;
    }

    public static int findRow(String s) {
        int low = 0;
        int high = 127;
        int m;

        for(char c : s.toCharArray()) {
            m = (low+high)/2;
            if(c == 'F') high = m;
            else low = m+1;
        }
        //System.out.println("Low=" + low + ", high=" + high);
        return low;
    }

    public static int findSeat(String s){
        int low = 0;
        int high = 7;
        int m;

        for(char c : s.toCharArray()) {
            m = (low+high)/2;
            if(c == 'L') high = m;
            else low = m+1;
        }
        //System.out.println("Low=" + low + ", high=" + high);
        return low;
    }
}
