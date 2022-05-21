package Days11_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FerrySeats {
    public static void main(String[] args) throws FileNotFoundException {
        String input = "L.LL.LL.LL\n" +
                        "LLLLLLL.LL\n" +
                        "L.L.L..L..\n" +
                        "LLLL.LL.LL\n" +
                        "L.LL.LL.LL\n" +
                        "L.LLLLL.LL\n" +
                        "..L.L.....\n" +
                        "LLLLLLLLLL\n" +
                        "L.LLLLLL.L\n" +
                        "L.LLLLL.LL";
        char[][] seats = setUp("ferryseats.txt");
        System.out.println(seats[0].length);
        printArray(seats);
        //part1
        //int count = relocate1(seats);
        //System.out.println(count);

        //part2
        int count2 = relocate2(seats);
        System.out.println(count2);

    }


    public static int relocate2(char[][] seats) {
        char[][] copy = new char[seats.length][seats[0].length];

        while(true) {
            for(int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if(seats[i][j] == 'L') {
                        if(checkSeatIfFree2(seats, i, j)) copy[i][j] = '#';
                        else copy[i][j] = seats[i][j];
                    } else if(seats[i][j] == '#') {
                        if(checkIfSeatNeedsToBeEmpty2(seats,i,j)) copy[i][j] = 'L';
                        else copy[i][j] = seats[i][j];
                    } else copy[i][j] = seats[i][j];
                }
            }
            boolean notChanged = equals(seats, copy);
            if(notChanged) return countOccupiedSeats(copy);
            printArray(copy);
            System.out.println();
            seats = copy;
            copy = new char[seats.length][seats[0].length];
        }
    }

    public static boolean checkSeatIfFree2(char[][] seats, int i, int j) {
        //check up
        int origI = i;
        int origJ = j;
        while(i > 0) {
            char seat = seats[--i][j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        i = origI;
        //check down
        while(i < seats.length - 1) {
            char seat = seats[++i][j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        i = origI;
        //check left
        while(j > 0) {
            char seat = seats[i][--j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        j = origJ;
        //check right
        while(j < seats[i].length - 1) {
            char seat = seats[i][++j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        j = origJ;

        //check up left
        while(i > 0 && j > 0) {
            char seat = seats[--i][--j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        i = origI;
        j = origJ;
        //check up right
        while(i > 0 && j < seats[i].length - 1) {
            char seat = seats[--i][++j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        i = origI;
        j = origJ;
        //check down left
        while(i < seats.length - 1 && j > 0) {
            char seat = seats[++i][--j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        i = origI;
        j = origJ;
        //check down right
        while(i < seats.length - 1 && j < seats[i].length - 1) {
            char seat = seats[++i][++j];
            if(seat == '#') return false;
            if(seat == 'L') break;
        }
        return true;
    }

    public static boolean checkIfSeatNeedsToBeEmpty2(char[][] seats, int i, int j) {
        int countOccupied = 0;
        //check up
        int origI = i;
        int origJ = j;
        while(i > 0) {
            char seat = seats[--i][j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        i = origI;
        //check down
        while(i < seats.length - 1) {
            char seat = seats[++i][j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        i = origI;
        //check left
        while(j > 0) {
            char seat = seats[i][--j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        j = origJ;
        //check right
        while(j < seats[i].length - 1) {
            char seat = seats[i][++j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        j = origJ;

        //check up left
        while(i > 0 && j > 0) {
            char seat = seats[--i][--j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        if(countOccupied == 5) return true;
        i = origI;
        j = origJ;
        //check up right
        while(i > 0 && j < seats[i].length - 1) {
            char seat = seats[--i][++j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        if(countOccupied == 5) return true;
        i = origI;
        j = origJ;
        //check down left
        while(i < seats.length - 1 && j > 0) {
            char seat = seats[++i][--j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        if(countOccupied == 5) return true;
        i = origI;
        j = origJ;
        //check down right
        while(i < seats.length - 1 && j < seats[i].length - 1) {
            char seat = seats[++i][++j];
            if(seat == '#') {
                countOccupied++;
                break;
            }
            if(seat == 'L') break;
        }
        return countOccupied == 5;
    }

    /**
     * Part 1
     */
    public static int relocate1(char[][] seats) {
        char[][] copy = new char[seats.length][seats[0].length];

        while(true) {
            for(int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if(seats[i][j] == 'L') {
                        if(checkSeatIfFree1(seats, i, j)) copy[i][j] = '#';
                        else copy[i][j] = seats[i][j];
                    } else if(seats[i][j] == '#') {
                        if(checkIfSeatNeedsToBeEmpty1(seats,i,j)) copy[i][j] = 'L';
                        else copy[i][j] = seats[i][j];
                    } else copy[i][j] = seats[i][j];
                }
            }
            boolean notChanged = equals(seats, copy);
            if(notChanged) return countOccupiedSeats(copy);
            printArray(copy);
            System.out.println();
            seats = copy;
            copy = new char[seats.length][seats[0].length];
        }
    }

    private static boolean checkSeatIfFree1(char[][] seats, int i, int j) {
        switch(j) {
            case 0:
                //top left corner
                if (i == 0) {
                    if (seats[i][j + 1] != '#' && seats[i + 1][j] != '#' && seats[i + 1][j + 1] != '#') return true;
                    return false;
                }
                //bottom left corner
                else if(i == seats.length - 1){
                    if(seats[i][j+1] != '#' && seats[i-1][j] != '#' && seats[i-1][j+1] != '#') return true;
                    return false;
                } //regular cell in the left column
                else if(seats[i][j+1] != '#' && seats[i-1][j] != '#' && seats[i+1][j] != '#' &&
                        seats[i+1][j+1] != '#' && seats[i-1][j+1] != '#') return true;
                break;
            case 90:
                //top right corner
                if(i == 0) {
                    if (seats[i][j - 1] != '#' && seats[i + 1][j] != '#' && seats[i + 1][j - 1] != '#') return true;
                    return false;
                }
                //bottom right corner
                else if(i == seats.length - 1){
                    if(seats[i][j-1] != '#' && seats[i-1][j] != '#' && seats[i-1][j-1] != '#') return true;
                    return false;
                }
                else if(seats[i][j-1] != '#' && seats[i-1][j] != '#' && seats[i+1][j] != '#' &&
                        seats[i+1][j-1] != '#' && seats[i-1][j-1] != '#') return true;
                break;
            default:
                //somewhere in the top row
                if(i == 0) {
                    if (seats[i][j + 1] != '#' && seats[i + 1][j] != '#' && seats[i + 1][j + 1] != '#' &&
                            seats[i + 1][j - 1] != '#' && seats[i][j - 1] != '#') return true;
                    return false;
                }
                //somewhere in the bottom row
                else if(i == seats.length - 1) {
                    if (seats[i][j + 1] != '#' && seats[i - 1][j] != '#' && seats[i - 1][j + 1] != '#' &&
                            seats[i - 1][j - 1] != '#' && seats[i][j - 1] != '#') return true;
                    return false;
                }
                else if(seats[i][j+1] != '#' && seats[i-1][j] != '#' && seats[i-1][j+1] != '#' &&
                        seats[i-1][j-1] != '#' && seats[i][j-1] != '#' && seats[i+1][j] != '#' &&
                        seats[i+1][j+1] != '#' && seats[i+1][j-1] != '#') return true;
                break;
        }
        return false;
    }

    private static boolean checkIfSeatNeedsToBeEmpty1(char[][] seats, int i, int j) {
        int numOccupiedSeats = 0;
        //check cell to the right
        if(j < seats[i].length-1 && seats[i][j+1] == '#') numOccupiedSeats++;
        //check cell to the left
        if(j > 0 && seats[i][j-1] == '#') numOccupiedSeats++;
        //check the cell just above
        if(i > 0 && seats[i-1][j] == '#') numOccupiedSeats++;
        //check the cell below
        if(i < seats.length -1 && seats[i+1][j] == '#') numOccupiedSeats++;
        if(numOccupiedSeats == 4) return true;

        //check cell below to the left
        if(j > 0 && i < seats.length -1 && seats[i+1][j-1] == '#') numOccupiedSeats++;
        //check cell below to the right
        if(j < seats[i].length-1 && i < seats.length -1 && seats[i+1][j+1] == '#') numOccupiedSeats++;
        //check cell above to the left
        if(i > 0 && j > 0 && seats[i-1][j-1] == '#') numOccupiedSeats++;
        //check cell above to the right
        if(i > 0 && j < seats[i].length-1 && seats[i-1][j+1] == '#') numOccupiedSeats++;
        return numOccupiedSeats >= 4;
    }

    /**
     * Common part
     */
    private static int countOccupiedSeats(char[][] copy) {
        int count = 0;
        for(int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[i].length; j++) {
                if(copy[i][j] == '#') count++;
            }
        }
        return count;
    }

    public static char[][] setUp(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        ArrayList<String> temp = new ArrayList<>();

        while(sc.hasNextLine()) temp.add(sc.nextLine());

        char[][] seats = new char[temp.size()][];
        for(int i = 0; i < temp.size(); ++i) {
            seats[i] = temp.get(i).toCharArray();
        }
        return seats;
    }

    private static void printArray(char[][] seats) {
        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean equals(char[][] arr1, char[][] arr2) {
        for(int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr1[i].length; j++) {
                if(arr1[i][j] != arr2[i][j]) return false;
            }
        }
        return true;
    }
}
