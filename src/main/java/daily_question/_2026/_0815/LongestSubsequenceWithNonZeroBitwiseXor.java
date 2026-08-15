package daily_question._2026._0815;


//https://leetcode.cn/problems/longest-subsequence-with-non-zero-bitwise-xor/description/
public class LongestSubsequenceWithNonZeroBitwiseXor {

    public static void main(String[] args) {
        System.out.println(Solution.longestSubsequence(new int[]{7, 6, 1, 9}));
    }

    static class Solution {
        public static int longestSubsequence(int[] nums) {
            // xor 从left到 right的异或结果
            int ans = 0, xor = 0, zeroN = 0;
            // 总结：
            // 1、异或全部不为0，返回
            // 2、全为0，返回0
            // 异或全部为0，说明 一定只需要少一个数，必然不为0.
            for (int num : nums) {
                xor ^= num;
                if (num == 0) {
                    zeroN++;
                }
            }
            if (xor != 0) {
                return nums.length;
            }
            if (zeroN == nums.length) {
                return 0;
            }
            return nums.length - 1;

            // 1、分情况讨论：全局是否为0
            // // 2、全局为0，只能讨论部分非0
            // xor = 0;
            // for(int left = 0, right = 0; right < nums.length; right++){
            //     xor ^= nums[right];

            //     // 子序列出现为0的了。
            //     // 特殊情况，某一段异或为0，但 全局不为0
            //     while(xor == 0 && left <= right){
            //         // left移动，消除left影响
            //         xor ^= nums[left];
            //         left++;
            //     }
            //     ans = Math.max(ans, right - left + 1);
            // }
            // return ans;
        }
    }
}
