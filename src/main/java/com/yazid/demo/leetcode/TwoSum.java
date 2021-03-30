package com.yazid.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yazid
 * @date 2020/7/1 22:14
 */
public class TwoSum {

    private static int[] twoSumMap(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumMap(nums, target);
        for (int value : result) {
            System.out.println(value);
        }
        Integer b=5;
        System.out.println(b.hashCode());

    }
}
