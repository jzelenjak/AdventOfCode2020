package ProgrammingChallenges;

public class Power {
    public static void main(String[] args) {
        int n1 = power(1,1);
        int n2 = power(1, 3);
        int n3 = power(2,0);
        int n4 = power(2,5);
        int n5 = power(2,4);

        System.out.println(n1 + " " + n2 + " " + n3 + " " +n4 + " " + n5);
    }
    private static int power(int x, int n) {
        if(n <= 0) return 1;
        if(n % 2 == 0) {
            int res = power(x, n/2);
            return res * res;
        }
        else {
            int res = power(x, n/2);
            return res * res * x;
        }
    }
}
