package ProgrammingChallenges;

public class Debug {
    public static void main(String[] args) {
        int num = n2(2);
        System.out.println(num);
    }

    public static int n(int n) {
        if (n == 1 || n == 0) return 0;
        int divisor = 2;
        while(divisor < n && n % divisor != 0) divisor++;

        if(divisor == n) return 1;
        return 0;
    }

    public static int n2(int n) {
        if(n == 1 || n == 0 || (n != 2 && n % 2 == 0) || (n != 3 && n % 3 == 0) || (n != 5 && n % 5 == 0)
                || (n != 7 && n % 7 == 0) || (n != 11 && n % 11 == 0) || (n != 13 && n % 13 == 0)
                || (n != 17 && n % 17 == 0) || (n != 19 && n % 19 == 0) || (n != 23 && n % 23 == 0)
                || (n != 29 && n % 29 == 0) || (n != 31 && n % 31 == 0) || (n != 37 && n % 37 == 0)
                || (n != 39 && n % 39 == 0)) return 0;
        return 1;
    }
}
