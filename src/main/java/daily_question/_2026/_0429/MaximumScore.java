package daily_question._2026._0429;

import java.util.Arrays;

public class MaximumScore {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{0, 0, 0, 0, 0}, {0, 0, 3, 0, 0}, {0, 1, 0, 0, 0}, {5, 0, 0, 3, 0}, {0, 0, 0, 0, 2}};
        long res = solution.maximumScore(grid);
        System.out.println(res);
    }
}

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        // 每列的前缀和（从上到下）
        long[][] colSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j][i + 1] = colSum[j][i] + grid[i][j];
            }
        }

        long[][][] memo = new long[n - 1][n + 1][2];
        for (long[][] mat : memo) {
            for (long[] row : mat) {
                Arrays.fill(row, -1); // -1 表示没有计算过
            }
        }

        // 枚举第 n-1 列有 i 个黑格
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, dfs(n - 2, i, 0, colSum, memo));
        }
        return ans;
    }

    // pre 表示第 j+1 列的黑格个数
    // dec=1 意味着第 j+1 列的黑格个数 (pre) < 第 j+2 列的黑格个数
    private long dfs(int j, int pre, int dec, long[][] colSum, long[][][] memo) {
        if (j < 0) {
            return 0;
        }
        if (memo[j][pre][dec] != -1) { // 之前计算过
            return memo[j][pre][dec];
        }
        long res = 0;
        // 枚举第 j 列有 cur 个黑格
        for (int cur = 0; cur <= colSum.length; cur++) {
            if (cur == pre) { // 情况一：相等
                // 没有可以计入总分的格子
                res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo));
            } else if (cur < pre) { // 情况二：右边黑格多
                // 第 j 列的第 [cur, pre) 行的格子可以计入总分
                res = Math.max(res, dfs(j - 1, cur, 1, colSum, memo) + colSum[j][pre] - colSum[j][cur]);
            } else if (dec == 0) { // 情况三：cur > pre >= 第 j+2 列的黑格个数
                // 第 j+1 列的第 [pre, cur) 行的格子可以计入总分
                res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo) + colSum[j + 1][cur] - colSum[j + 1][pre]);
            } else if (pre == 0) { // 情况四（凹形）：cur > pre < 第 j+2 列的黑格个数
                // 此时第 j+2 列全黑最优（递归过程中一定可以枚举到这种情况）
                // 第 j+1 列全白是最优的，所以只需考虑 pre=0 的情况
                // 由于第 j+1 列在 dfs(j+1) 的情况二中已经统计过，这里不重复统计
                res = Math.max(res, dfs(j - 1, cur, 0, colSum, memo));
            }
        }
        return memo[j][pre][dec] = res; // 记忆化
    }
}

