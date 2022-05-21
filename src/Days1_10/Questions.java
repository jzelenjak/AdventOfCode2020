package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Questions {
    public static void main(String[] args) throws FileNotFoundException {
        //int num = findSumAnyone("questions.txt");
        int num2 = findSumEveryone("questions.txt");
        //System.out.println(num);
        System.out.println(num2);
    }

    public static int findSumEveryone(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input)).useDelimiter("\n\n");
        int totalCount = 0;
        Set<Character> letters;
        while(sc.hasNext()){
            letters = new HashSet<>();
            String[] answers = sc.next().split("\n");

            for(char c : answers[0].toCharArray()) letters.add(c);
            Set<Character> copyLetters = new HashSet<>(letters);

            for(int i = 1; i < answers.length; ++i) {
                for(char c : letters) {
                    if(answers[i].indexOf(c) == -1) copyLetters.remove(c);
                }
            }
            System.out.println(copyLetters.size());
            totalCount += copyLetters.size();
        }
        return totalCount;
    }

    public static int findSumAnyone(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input)).useDelimiter("\n\n");
        int totalCount = 0;
        Set<Character> letters;
        while(sc.hasNext()){
            letters = new HashSet<>();
            String[] answers = sc.next().split("\n");
            for(String answer : answers) {
                for(char c : answer.toCharArray()) letters.add(c);
            }
            totalCount += letters.size();
        }
        return totalCount;
    }
}
