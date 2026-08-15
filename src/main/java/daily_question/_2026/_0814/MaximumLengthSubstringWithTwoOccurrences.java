package daily_question._2026._0814;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences/description/
public class MaximumLengthSubstringWithTwoOccurrences {
    public static void main(String[] args) {
        System.out.println(Solution.maximumLengthSubstring("bdbbabccad"));
    }

    static class Solution {
        public static int maximumLengthSubstring(String s) {
            int ans = 0, left = 0;
            Map<Character, Integer> occ = new HashMap<>();
            for (int right = 0; right < s.length(); right++) {
                char ch = s.charAt(right);
                // 元素加入窗口，频次 + 1
                occ.put(ch, occ.getOrDefault(ch, 0) + 1);
                while (occ.get(ch) > 2) {

//                    ch = s.charAt(left); 循环里面修改了ch, 但用于判断是否进行循环的是 right制定元素，此时修改为了left指定的，肯等会有问题
                    char lch = s.charAt(left);
                    occ.put(lch, occ.getOrDefault(lch, 0) - 1);
                    left++;
                }
                // 窗口 [left, right] 长度
                ans = Math.max(ans, right - left + 1);
            }
            return ans;
        }
    }
}
