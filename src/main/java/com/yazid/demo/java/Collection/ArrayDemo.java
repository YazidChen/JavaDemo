package com.yazid.demo.java.Collection;

import java.util.ArrayList;

/**
 * @author YazidChen
 * @date 2018/10/25 0025 9:43
 */
public class ArrayDemo {

    private static int findPivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x : nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }

    private static int largestNumTwice(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int index = 0;
        int largestOne = nums[0];
        int largestTwo = nums[1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > largestOne) {
                largestTwo = largestOne;
                largestOne = nums[i];
                index = i;
            } else if (nums[i] > largestTwo) {
                largestTwo = nums[i];
            }
        }
        if (largestTwo * 2 <= largestOne) {
            return index;
        }
        return -1;
    }

    private static int[] plusOne(int[] digits) {
        int n = digits.length;
        //个位加1，小于9其他位不变，大于等于9进1。
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;

        return newNumber;
    }

    private static void diagonalTraverse() {
        int[][] a = new int[3][3];
        int num = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = num;
                num++;
            }
        }

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            System.out.println("i:" + i);
            for (int j = 0; j < a[i].length; j++) {
                System.out.println("j:" + j);
                for (int l = i, k = j; k >= 0; l++, k--) {
                    System.out.println("l:" + l + " k:" + k + " a[l][k]:" + a[l][k]);
                    arrayList.add(a[l][k]);
                }
            }
        }
        System.out.println(arrayList);
    }

    private static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int r = 0, c = 0, m = matrix.length, n = matrix[0].length;
        int[] arr = new int[m * n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[r][c];
            if ((r + c) % 2 == 0) {
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] a = new int[3][3];
        int num = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = num;
                num++;
            }
        }
        int[] result = findDiagonalOrder(a);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
