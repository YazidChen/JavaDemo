package com.yazid.demo.java.java.Collection;

/**
 * @author YazidChen
 * @date 2018/10/25 0025 9:43
 */
public class ArrayDemo {

    public static int findPivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x : nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int nums[] = {-1, -1, -1, 0, 1, 1};
        System.out.println(findPivotIndex(nums));
    }
}
