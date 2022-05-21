package Days1_10;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Search {
    public static void main(String[] args) throws IOException{
        int count = countValidPasswords1("passwords.txt");
        System.out.println(count);
        System.out.println();
        int count2 = countValidPasswords2("passwords.txt");
        System.out.println(count2);
    }

    public static int countValidPasswords1(String filename) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        int count = 0;

        while(sc.hasNextLine()) {
            //15-20 k: kkkkkkkkkkkskpkkkkkk
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String[] minMax = parts[0].split("-");
            int min = Integer.parseInt(minMax[0]);
            int max = Integer.parseInt(minMax[1]);

            char letter = parts[1].charAt(0);
            String password = parts[2];

            int charCount = 0;
            for(char c : password.toCharArray())
                if(c == letter) charCount++;
            if(charCount >= min && charCount <= max) count++;
        }
        return count;
    }

    public static int countValidPasswords2(String filename) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        int count = 0;

        while(sc.hasNextLine()) {
            //15-20 k: kkkkkkkkkkkskpkkkkkk
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String[] minMax = parts[0].split("-");
            int ind1 = Integer.parseInt(minMax[0]) - 1;
            int ind2 = Integer.parseInt(minMax[1]) - 1;

            char letter = parts[1].charAt(0);
            String password = parts[2];

            if((password.charAt(ind1) == letter && password.charAt(ind2) != letter)
                    || (password.charAt(ind1) != letter && password.charAt(ind2) == letter)) count++;
        }
        return count;
    }
}
