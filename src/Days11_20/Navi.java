package Days11_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Navi {
    public static void main(String[] args) throws FileNotFoundException {
        String input = new String("F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11");
        ArrayList<String> instr = readIn("navi.txt");
        System.out.println(instr);
        //int m = execute1(instr);
        //System.out.println(m);
        int m2 = execute2(instr);
        System.out.println(m2);
    }

    private static int execute2(ArrayList<String> instructions) {
        int[] waypoint = {1,10,0,0};            //[N,E,S,W]
        int[] dirs = {0,1};
        int[] info = new int[4];

        for(String instruction : instructions) {
            char instr = instruction.charAt(0);
            int val = Integer.parseInt(instruction.substring(1));
            System.out.println(instruction);

            if(instr == 'F') {
                moveShip(info, dirs, waypoint, val);

            }
            else if (instr == 'L') {
                int v1 = waypoint[dirs[0]];
                int v2 = waypoint[dirs[1]];
                waypoint = new int[4];
                dirs[0] = (dirs[0] - val/90 + 4) % 4;
                dirs[1] = (dirs[1] - val/90 + 4) % 4;
                waypoint[dirs[0]] = v1;
                waypoint[dirs[1]] = v2;
            }
            else if (instr == 'R') {
                int v1 = waypoint[dirs[0]];
                int v2 = waypoint[dirs[1]];
                waypoint = new int[4];
                dirs[0] = (dirs[0] + val/90) % 4;
                dirs[1] = (dirs[1] + val/90) % 4;
                waypoint[dirs[0]] = v1;
                waypoint[dirs[1]] = v2;
            }
            else if (instr == 'N') {
                updateWaypoint(dirs, waypoint, 0, val);
            } else if (instr == 'S') {
                updateWaypoint(dirs, waypoint, 2, val);
            } else if (instr == 'E') {
                updateWaypoint(dirs, waypoint, 1, val);
            } else if (instr == 'W') {
                updateWaypoint(dirs, waypoint, 3, val);
            }
            System.out.println(Arrays.toString(info));
            System.out.println(Arrays.toString(waypoint));
            System.out.println(Arrays.toString(dirs));
            System.out.println();
        }
        return calculateManhattan(info);
    }

    private static void moveShip(int[] info, int dirs[], int[] waypoint, int val) {
        int compl1 = (dirs[0] + 2) % 4;
        int compl2 = (dirs[1] + 2) % 4;
        if(info[compl1] == 0) info[dirs[0]] += waypoint[dirs[0]] * val;
        else {
            info[compl1] -= waypoint[dirs[0]] * val;
            if (info[compl1] < 0) {
                info[dirs[0]] += Math.abs(info[compl1]);
                info[compl1] = 0;
            }
        }

        if(info[compl2] == 0) info[dirs[1]] += waypoint[dirs[1]] * val;
        else {
            info[compl2] -= waypoint[dirs[1]] * val;
            if (info[compl2] < 0) {
                info[dirs[1]] += Math.abs(info[compl2]);
                info[compl2] = 0;
            }
        }
    }

    private static void updateWaypoint(int[] dirs, int[] waypoint, int dir, int val) {
        int compl = (dir + 2) % 4;
        if(waypoint[compl] == 0 && waypoint[dir] == 0) {
            waypoint[dir] = val;
            for(int i = 0; i < dirs.length; i++) {
                if (dirs[i] == compl) {
                    dirs[i] = dir;
                    int temp = dirs[0];
                    dirs[0] = dirs[1];
                    dirs[1] = temp;
                    return;
                }
            }
        }
        else if(waypoint[compl] == 0) waypoint[dir] += val;
        else {
            waypoint[compl] -= val;
            if(waypoint[compl] < 0) {
                waypoint[dir] += Math.abs(waypoint[compl]);
                waypoint[compl] = 0;
                for(int i = 0; i < dirs.length; i++) {
                    if(dirs[i] == compl) {
                        dirs[i] = dir;
                        break;
                    }

                }
                //if(waypoint[dir] == 0) return;
                int temp = dirs[0];
                dirs[0] = dirs[1];
                dirs[1] = temp;
            }
        }
    }


    /** Part1
     */
    private static int execute1(ArrayList<String> instructions) {
        int dir = 1;
        int[] info = new int[4];    //[N,E,S,W]

        for (String instruction : instructions) {
            char instr = instruction.charAt(0);
            int val = Integer.parseInt(instruction.substring(1));

            if (instr == 'F') {
                int compl = (dir + 2) % 4;                          //opposite direction
                info[compl] -= val;                                 //subtract units from opposite direction
                if(info[compl] < 0) {                               //if negative
                    info[dir] = info[dir] + Math.abs(info[compl]);  //add the remaining units to the current direction (will be just adding if opposite was 0 originally)
                    info[compl] = 0;                                //reset the opposite direction units
                }
            }
            else if (instr == 'L') dir = (dir - val / 90 + 4) % 4;
            else if (instr == 'R') dir = (dir + val / 90) % 4;

            else if (instr == 'N') {
                info[2] -= val;
                if (info[2] < 0) {
                    info[0] += Math.abs(info[2]);
                    info[2] = 0;
                }
            } else if (instr == 'S') {
                info[0] -= val;
                if (info[0] < 0) {
                    info[2] += Math.abs(info[0]);
                    info[0] = 0;
                }
            } else if (instr == 'E') {
                info[3] -= val;
                if (info[3] < 0) {
                    info[1] += Math.abs(info[3]);
                    info[3] = 0;
                }
            } else if (instr == 'W') {
                info[1] -= val;
                if (info[1] < 0) {
                    info[3] += Math.abs(info[1]);
                    info[1] = 0;
                }
            }
            System.out.println(Arrays.toString(info));
        }
        return calculateManhattan(info);
    }

    private static int calculateManhattan(int[] info) {
        int sum = 0;

        for(int i = 0; i < info.length; i++) sum += info[i];
        return sum;
    }

    private static ArrayList<String> readIn(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        ArrayList<String> intsr = new ArrayList<>();

        while (sc.hasNextLine()) intsr.add(sc.nextLine());
        return intsr;
    }
}
