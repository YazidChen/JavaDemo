package com.yazid.demo.java.java.Collection;

/**
 * @author YazidChen
 * @date 2018/10/25 0025 9:43
 */
public class ArrayDemo {

    public static int findPivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        for (int index = 0; index < nums.length; index++) {
            int leftSum = 0;
            int rightSum = 0;
            for (int l = 0; l < index; l++) {
                leftSum += nums[l];
            }
            for (int r = 0; r < nums.length - 1 - index; r++) {
                rightSum += nums[nums.length - 1 - r];
            }
            if (leftSum == rightSum) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {-1, -1, -1, 0, 1, 1};
        System.out.println(findPivotIndex(nums));
    }
}
