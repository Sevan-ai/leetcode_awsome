package daily_question._2026._0430;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        Solution solution = new Solution();
        List<List<Integer>> result =  solution.threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));
    }
}


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // 时间复杂度 O(NlogN);
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举a
        for(int first =0; first < n; first++){
            // 需要和上一次枚举的数不同
            if(first > 0 && nums[first] == nums[first -1]){
                continue;
            }
            // c 对应的指针初始指向数组的最末端
            int third = n - 1;
            int target = -nums[first];
            // 枚举b
            for(int second = first + 1; second < n; ++second){
                // 需要和上一次枚举的数不同
                if(second > first + 1 && nums[second] == nums[second -1]){
                    continue;
                }
                // 需要保证b的指针在c指针的右侧
                while(second < third && nums[second] + nums[third] > target){
                    third--;
                }

                // 如果出现指针重合，随着b的增加，就不会满足 a+b+c = 0并且b < c 的c了，可以退出循环
                if(third == second){
                    break;
                }

                if(nums[second] + nums[third] == target){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}