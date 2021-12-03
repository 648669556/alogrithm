package com.alogrithm;


import java.util.*;

public class Solution {
    final Random random = new Random();
    final int MOD = (int) 1e9 + 7;


    public int largestSumAfterKNegations(int[] arr, int target) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0, minNumber = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i <= 0) {
                queue.offer(i);
            } else {
                res += i;
            }
            minNumber = Math.min(minNumber, Math.abs(i));
        }
        while (target > 0 && !queue.isEmpty()) {
            final Integer poll = queue.poll();
            res += Math.abs(poll);
            target--;
        }
        while (!queue.isEmpty()) {
            res += queue.poll();
        }
        if (target > 0 && (target & 1) == 1) {
            return res - minNumber * 2;
        } else {
            return res;
        }
    }


    public void run() {
        int[] input = {3, -1, 0, 2};
        long before = System.currentTimeMillis();
        System.out.println(largestSumAfterKNegations(input, 3));
        System.out.println("耗时" + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.run();
    }

}
