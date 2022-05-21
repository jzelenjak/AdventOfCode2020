package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Passports {
    public static void main(String[] args) throws FileNotFoundException {
        int count = checkPassports("passports.txt");
        System.out.println(count);
    }

    public static int checkPassports(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename)).useDelimiter("\n\n");
        int count = 0;
        while(sc.hasNext()) {
            String[] lines = sc.next().split("[\n ]");

            if(lines.length < 7) continue;
            boolean valid = true;
            boolean hasCID = false;

            for (String line : lines) {
                String[] pair = line.split(":");
                if (pair[0].equals("cid")) hasCID = true;
                else if(pair[0].equals("byr")){
                    int yr = Integer.parseInt(pair[1]);
                    if(yr < 1920 || yr > 2002) {
                        valid = false;
                        break;
                    }
                }
                else if(pair[0].equals("iyr")){
                    int yr = Integer.parseInt(pair[1]);
                    if(yr < 2010 || yr > 2020) {
                        valid = false;
                        break;
                    }
                }
                else if(pair[0].equals("eyr")){
                    int yr = Integer.parseInt(pair[1]);
                    if(yr < 2020 || yr > 2030) {
                        valid = false;
                        break;
                    }
                }
                else if(pair[0].equals("hgt")){
                    if(pair[1].endsWith("cm")) {
                        int h = Integer.parseInt(pair[1].split("cm")[0]);
                        if(h < 150 || h > 193) {
                            valid = false;
                            break;
                        }
                    } else if(pair[1].endsWith("in")) {
                        int h = Integer.parseInt(pair[1].split("in")[0]);
                        if(h < 59 || h > 76) {
                            valid = false;
                            break;
                        }
                    } else {
                        valid = false;
                        break;
                    }
                }

                else if(pair[0].equals("hcl")){
                    String symbols = "0123456789abcdef";
                    String s = pair[1];
                    if(!s.startsWith("#")) {
                        valid = false;
                        break;
                    }
                    s = s.substring(1);
                    for(char c : s.toCharArray()) {
                        if (symbols.indexOf(c) == -1) {
                            valid = false;
                            break;
                        }
                    }
                }
                else if(pair[0].equals("ecl")){
                    List<String> allowed = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                    if(!allowed.contains(pair[1])) {
                        valid = false;
                        break;
                    }
                }
                else if(pair[0].equals("pid")){
                    String s = pair[1];
                    if(s.length() != 9) {
                        valid = false;
                        break;
                    }
                    for(char c : s.toCharArray()) {
                        if(!Character.isDigit(c)) {
                            valid = false;
                            break;
                        }
                    }
                }
            }

            if(valid && (lines.length == 8 || (lines.length == 7 && !hasCID))) count++;
        }
        return count;
    }
}
