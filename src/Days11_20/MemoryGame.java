package Days11_20;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemoryGame {
    public static void main(String[] args) {
        String[] input = new String("0,3,6").split(",");

        //stores the numbers said during the game, index 0 is redundant, the game starts from index 1 for simplicity
        //2021 for the first part
        List<Integer> game = IntStream.range(0,30000001).mapToObj(i -> 0).collect(Collectors.toList());

        //keeps track of which numbers were said and their first occurrence
        Map<Integer, Integer> ageMap = new HashMap<>();
        Set<Integer> occurred = new HashSet<>();

        //store the input numbers in the "game" starting from pos 1, and in the "ageMap" starting with value 1
        for(int i = 0; i < input.length; i++) {
            int num = Integer.parseInt(String.valueOf(input[i]));
            ageMap.put(num, i+1);
            game.set(i+1, num);
            //occurred.add(num);
            System.out.println(num);
        }

        //process 2020 numbers (minus the numbers in the input list)
        for(int i = input.length + 1; i < 30000001; i++) {
            //get the previous number
            int prev = game.get(i - 1);
            //if it wasn't said or it was said for the first time
            if(!ageMap.containsKey(prev) || ageMap.get(prev) == i-1) {
                //store the occurrence of 0 if it is not in the map
                if(!ageMap.containsKey(0)) {
                    ageMap.put(0, i);
                    //occurred.add(0);
                }
                //set current character to zero
                game.set(i,0);
                System.out.println(0);
            }
            else {
                //get the first occurrence of the previous number
                int born = ageMap.get(prev);
                //search for its last occurrence
                int last = 0;
                if(!occurred.contains(prev)) last = born;
                else {
                     for(int j = i-2; j >= born; j--) {
                        if(game.get(j) == prev) {
                            last = j;
                            break;
                        }
                    }
                }

                 occurred.add(prev);
                //if its last occurrence was its first occurrence
                if(last == born) {
                    //get the difference between the pos and the first pos
                    int num = i - 1 - ageMap.get(prev);
                    //store the difference if it is not yet stored
                    if(!ageMap.containsKey(num)) {
                        ageMap.put(num, i);

                    }
                    //store the number in the game
                    game.set(i, num);
                    System.out.println(num);
                    continue;
                }

                //get the difference
                int num = (i - 1) - last;
                //store the difference if it is not yet stored
                if(!ageMap.containsKey(num)) ageMap.put(num, i);
                //store the number in the game
                game.set(i,num);

                System.out.println(num);
            }
        }
        System.out.println();
        System.out.println(game.get(game.size() - 1));
    }
}
