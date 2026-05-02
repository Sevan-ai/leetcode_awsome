package hot100._0501;

import java.util.Deque;
import java.util.LinkedList;

//42. 接雨水
public class TrappingRainWater_42 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Solution solution = new Solution();
        solution.trap(height);
    }
}


class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWith = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currHeight * currWith;
            }
            stack.push(i);
        }
        return ans;
    }
}