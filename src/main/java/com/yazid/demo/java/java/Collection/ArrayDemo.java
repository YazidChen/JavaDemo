package com.yazid.demo.java.java.Collection;

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

    private static int[] plusOne(int[] nums) {
        //TODO
        return nums;
    }

    public static void main(String[] args) {
        int nums[] = {9, 9};
        for (int n : plusOne(nums)) {
            System.out.println(n);
        }
    }
}
