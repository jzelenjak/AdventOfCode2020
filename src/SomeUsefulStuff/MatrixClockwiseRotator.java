package SomeUsefulStuff;

import java.util.Arrays;

public class MatrixClockwiseRotator {
    public static void main(String[] args) {
        int[][] arr = new int[7][7];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                arr[i][j] = arr.length * i + 1 + j;
            }
        }
        print(arr);
        System.out.println();
        rotateSquareImageCW(arr);
        print(arr);
    }
    public static void rotateSquareImageCW(int[][] matrix) {
        int l = matrix.length;
        for(int i = 0; i < l-i; i++) {
            for(int j = i; j < l-1-i; j++) {
                swap(matrix, i, j, j, l-1-i);
                swap(matrix, i, j, l-1-i, l-1-j);
                swap(matrix, i, j, l-1-j, i);
            }
        }
    }

    public static void rotateSquareImageCCW(int[][] matrix) {
        int l = matrix.length;
        for(int i = 0; i < l/2; i++) {
            for(int j = i; j < l-1-i; j++) {
                //temp variables
                int first = matrix[i][j];
                int second = matrix[l-1-j][i];
                int third = matrix[l-1-i][l-1-j];

                //swapping
                matrix[i][j] = matrix[j][l-1-i];    //4th -> 1st
                matrix[l-1-j][i] = first;           //1st -> 2nd
                matrix[l-1-i][l-1-j] = second;      //2nd -> 3rd
                matrix[j][l-1-i] = third;           //3rd -> 4th
            }
        }
    }

    private static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print("[");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println("]");
        }
    }
}
