import java.util.List;

public class CheaterThread extends Thread {
    private long start;
    private List<int[]> timetable;
    private int indexOfMax;
    private int max;
    private int offsetOfMax;

    public CheaterThread(long start, List<int[]> timetable, int indexOfMax,
                         int max, int offsetOfMax) {
        this.start = start;
        this.timetable = timetable;
        this.indexOfMax = indexOfMax;
        this.max = max;
        this.offsetOfMax = offsetOfMax;
    }

    public void run() {
        try {
            long stamp = (start / (max*offsetOfMax) + 1) * (max*offsetOfMax);  //only include offset of max here to speed up the final problem: this is a coincidence that the offset of max is equal to the first bus id, but this may speed up the process greatly
            long finish = start + 500000000000L;
            while (stamp < finish) {
                boolean success = true;
                //System.out.println(stamp);
                for (int i = 0; i < indexOfMax; i++) {
                    if ((stamp - offsetOfMax + timetable.get(i)[1]) % timetable.get(i)[0] == 0) {
                        continue;
                    } else {
                        success = false;
                        stamp = Math.addExact(stamp, max * offsetOfMax);
                        break;
                    }
                }

                if (!success) continue;

                for (int i = indexOfMax + 1; i < timetable.size(); i++) {
                    if ((stamp + timetable.get(i)[1] - offsetOfMax) % timetable.get(i)[0] == 0) {
                        continue;
                    } else {
                        success = false;
                        stamp = Math.addExact(stamp, max * offsetOfMax);            //only include offset of max here to speed up the final problem: this is a coincidence that the offset of max is equal to the first bus id, but this may speed up the process greatly
                        break;
                    }
                }
                if (success) {
                    System.out.println(stamp - offsetOfMax);                        //only include offset of max here to speed up the final problem: this is a coincidence that the offset of max is equal to the first bus id, but this may speed up the process greatly
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Overflow!");
        }
    }

}
