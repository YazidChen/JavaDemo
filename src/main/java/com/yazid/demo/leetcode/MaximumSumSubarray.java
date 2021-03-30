package com.yazid.demo.leetcode;

/**
 * @author YazidChen
 * @date 2020/01/19 0019 11:47
 */
public class MaximumSumSubarray {
    /**
     * 动态规划法
     *
     * @param nums
     */
    public int dp(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 贪心算法
     *
     * @param nums
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

}
