package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrokenCode {
    public static void main(String[] args) throws FileNotFoundException {
        /*String input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6";*/
        //part 1
        ArrayList<String> instructions = readInstructions("code.txt");
        int acc = execute(instructions);
        System.out.println("The value of the accumulator before the crash is " + acc);

        //part 2
        fix(instructions);
    }

    /**
     * fix the code
     */
    public static void fix(ArrayList<String> instructions) {
        //lines that were attempted to be fixed
        ArrayList<Integer> attempted = new ArrayList<>();
        //copy of the instructions to be tried to execute
        ArrayList<String> instructionsCopy = new ArrayList<>(instructions);


        while(true) {
            //try to run the instructions
            boolean attempt = tryToRun(instructionsCopy);
            //break if successfully
            if(attempt) break;

            //make a new instruction copy
            instructionsCopy = new ArrayList<>(instructions);
            //try to run now
            tryToFix(attempted, instructionsCopy);
        }
    }

    public static void tryToFix(ArrayList<Integer> attempted, ArrayList<String> instructionCopy) {
        //iterate over all the instructions from the back (except the last one)
        for(int i = 0; i < instructionCopy.size()-2; i++) {
            //if we already fixed this line, continue
            if(attempted.contains(i)) continue;

            //get instruction
            String instruction = instructionCopy.get(i);
            if(instruction.startsWith("nop")) {
                instruction = "jmp" + instruction.substring(3);
                instructionCopy.set(i, instruction);
                attempted.add(i);
                return;
            } else if(instruction.startsWith("jmp")) {
                instruction = "nop" + instruction.substring(3);
                instructionCopy.set(i, instruction);
                attempted.add(i);
                return;
            }
        }
    }

    /**
     * try to run the code and determine if it is correct
     */
    public static boolean tryToRun(ArrayList<String> instructions) {
        try {
            execute(instructions);
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }


    /**
     * Executes the code, returns the last value of the accumulator if unsuccessful; only prints it if successful
     */
    public static int execute(ArrayList<String> instructions) {
        int acc = 0;
        ArrayList<Integer> executed = new ArrayList<>();
        int line = 0;

        try {
            while (true) {
                String instruction = instructions.get(line);
                //System.out.println(line + " " + instruction);
                if (executed.contains(line)) break;

                executed.add(line);
                int number = Integer.parseInt(instruction.substring(5));

                if (instruction.startsWith("acc")) {
                    if (instruction.contains("-")) acc -= number;
                    else acc += number;
                } else if (instruction.startsWith("jmp")) {
                    if (instruction.contains("-")) line -= number;
                    else line += number;
                    continue;
                }
                line++;
            }
        } catch (IndexOutOfBoundsException e) {
            if(line >= instructions.size()) {
                System.out.println("The value of the accumulator after fixing is " + acc);
                throw new IndexOutOfBoundsException();
            }
            return acc;
        }
        return acc;
    }

    public static ArrayList<String> readInstructions(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        ArrayList<String> instructions = new ArrayList<>();
        while(sc.hasNextLine()) instructions.add(sc.nextLine());
        return instructions;
    }
}
