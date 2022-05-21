package ProgrammingChallenges;

import java.math.BigInteger;

public class Demo2 {
    public static void main(String[] args) {
        long n = solve(1000000000000000000L);
        //long n2 = solve2((long) Math.pow(10,18));
        System.out.println();
        System.out.println(n);
        //System.out.println(n2);
    }

    private static long solve2(long n) {
        long count = 0;
        for(long i = 5; i <= n; i += 5) {
            long div = i;
            while(div / 5 != 0 && div % 5 == 0) {
                count++;
                div = div / 5;
            }
        }
        return count;
    }

    private static long solve(long num) {
        long count = 0;

        if(num < 5) return count;
        else {
            long power = (long) (Math.log(num) / Math.log(5));
            for(long i = 1; i <= power; i++) {
                count += num / (long) (Math.pow(5,i));
                System.out.println((long) (Math.pow(5,i)));
            }
        }
        return count;
    }
}
