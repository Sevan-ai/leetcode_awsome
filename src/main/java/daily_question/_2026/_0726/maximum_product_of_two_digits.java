package daily_question._2026._0726;

public class maximum_product_of_two_digits {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.maxProduct(31);
    }

    static class Solution {
        public int maxProduct(int num) {
            // 0~9每个数字出现次数
            int[] cnt = new int[10];
            while (num != 0) {
                cnt[num % 10]++;
                num /= 10;
            }

            // 选出最大的2个数字
            int m = -1, n = -1;
            for (int i = 9; i >= 0; i--) {
                // 判断是否取到
                if (m != -1 && n != -1) {
                    break;
                }
                //
                if (cnt[i] >= 2) {
                    if (m != -1) {
                        n = i;
                    } else {
                        m = i;
                        n = i;
                    }
                } else if (cnt[i] == 1) {
                    if (m != -1) {
                        n = i;
                    } else {
                        m = i;
                    }
                }
            }
            return m * n;
        }
    }
}
