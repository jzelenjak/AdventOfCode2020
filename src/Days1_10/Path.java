package Days1_10;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Path {
    public static void main(String[] args) throws IOException{
        /*
        * Right 1, down 1.
          Right 3, down 1. (This is the slope you already checked.)
          Right 5, down 1.
          Right 7, down 1.
          Right 1, down 2.*/
        long count1 = countTreesOnPath("trees.txt", 1, 1);
        System.out.println(count1);
        long count2 = countTreesOnPath("trees.txt", 3, 1);
        System.out.println(count2);
        long count3 = countTreesOnPath("trees.txt", 5, 1);
        System.out.println(count3);
        long count4 = countTreesOnPath("trees.txt", 7, 1);
        System.out.println(count4);
        long count5 = countTreesOnPath("trees.txt", 1, 2);
        System.out.println(count5);

        long res = (((count1 * count2) * count3) * count4) * count5;
        System.out.println("After multiplication: " + res);
        /*String draft = "..##.........##.........##.........##.........##.........##.......\n" +
                       "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..\n" +
                ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.\n" +
                "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#\n" +
                ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.\n" +
                "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....\n" +
                ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#\n" +
                ".#........#.#........#.#........#.#........#.#........#.#........#\n" +
                "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...\n" +
                "#...##....##...##....##...##....##...##....##...##....##...##....#\n" +
                ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#\n";*/
    }

    public static int countTreesOnPath(String filename, int right, int down) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        int x = 0;
        int count = 0;
        int l = sc.nextLine().length();
        int y = 2;
        for(int i = 1; i < down; i++) sc.nextLine();                 //discard lines

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            x = (x + right) % l;
            //System.out.println("x="+x+", y="+y+", character=" + line.charAt(x));
            if(line.charAt(x) == '#') count++;

            for(int i = 1; i < down; i++) {
                if(sc.hasNextLine()) sc.nextLine();
            }
            y+=2;
        }
        return count;
    }
}
