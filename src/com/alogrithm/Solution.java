package com.alogrithm;


import com.alogrithm.util.UtilTool;

import java.io.*;
import java.util.*;


public class Solution {
    final Random random = new Random();
    final int MOD = (int) 1e9 + 7;
    final int INF = 0x3f3f3f3f;

    public int bestRotation(int[] nums) {
        int len = nums.length;
        int maxIndex = -1;
        int maxCount = 0;
        int[] sum = new int[len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(sum, 1);
            sum[i] = -(len - 1);
            sum[0] = (len - i) % len;
            int curIndex = 0;
            int curCount = 0;
            for (int j = 0; j < len; j++) {
                curIndex += sum[j];
                if (nums[j] <= curIndex) curCount++;
            }
            if (curCount > maxCount) {
                maxIndex = i;
                maxCount = curCount;
            }
        }
        return maxIndex;
    }

    public void run() throws IOException {
        int[] input = UtilTool.arrOne();
        long before = System.currentTimeMillis();
        System.out.println(bestRotation(input));
        System.out.println("耗时" + (System.currentTimeMillis() - before) + "ms");
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.run();
    }

}
