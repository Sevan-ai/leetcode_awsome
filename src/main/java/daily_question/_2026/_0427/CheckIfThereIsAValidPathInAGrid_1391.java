package daily_question._2026._0427;

/**
 * 检查网格中是否存在有效路径
 */
public class CheckIfThereIsAValidPathInAGrid_1391 {

    public static void main(String[] args) {
        int[][] grid = {{2, 4, 3}, {6, 5, 2}};
        Solution solution = new Solution();
        boolean result = solution.hasValidPath(grid);
        System.out.println("res: " + result);
    }
}

class Solution {

    class DisjointSet {
        int[] f;

        public DisjointSet(int m, int n) {
            f = new int[m * n];
            for (int i = 0; i < m * n; i++) {
                f[i] = i;
            }
        }

        /**
         * 并查集（Union-Find） 中最核心的路径压缩查找函数
         *
         * if (x == f[x]) {
         *     // 自己就是根节点，直接返回
         *     return x;
         * } else {
         *     // 递归找根，并且把当前节点直接连到根上（路径压缩）
         *     f[x] = find(f[x]);
         *     return f[x];
         * }
         * @param x
         * @return
         */
        public int find(int x) {
//            return x == f[x] ? x : (f[x] = find(f[x]));  // 下面的简写
            if (x == f[x]) {
                // 自己就是根节点，直接返回
                return x;
            } else {
                // 递归找根，并且把当前节点直接连到根上（路径压缩）
                f[x] = find(f[x]);
                return f[x];
            }
        }

        public void merge(int x, int y) {
            int tx = find(x);
            int ty = find(y);
            f[tx] = ty;
        }
    }

    int[][] grid;
    int m, n;
    DisjointSet ds;


    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        ds = new DisjointSet(m, n);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                handler(i, j);
            }
        }
        return ds.find(getId(0, 0)) == ds.find(getId(m - 1, n - 1));
    }

    public int getId(int x, int y) {
        return x * n + y;
    }

    public void detectL(int x, int y) {
        if (y - 1 > 0 && (grid[x][y - 1] == 4 || grid[x][y - 1] == 6 || grid[x][y - 1] == 1)) {
            ds.merge(getId(x, y), getId(x, y - 1));
        }
    }

    public void detectR(int x, int y) {
         if (y + 1 < n && (grid[x][y + 1] == 3 || grid[x][y + 1] == 5 || grid[x][y + 1] == 1)) {
            ds.merge(getId(x, y), getId(x, y + 1));
        }
    }

    public void detectU(int x, int y) {
        if (x - 1 > 0 && (grid[x - 1][y] == 3 || grid[x - 1][y] == 4 || grid[x - 1][y] == 2)) {
            ds.merge(getId(x, y), getId(x - 1, y));
        }
    }


    public void detectD(int x, int y) {
        if (x + 1 < m && (grid[x + 1][y] == 5 || grid[x + 1][y] == 6 || grid[x + 1][y] == 2)) {
            ds.merge(getId(x, y), getId(x + 1, y));
        }
    }


    public void handler(int x, int y) {
        switch (grid[x][y]) {
            case 1:
                detectL(x, y);
                detectR(x, y);
                break;
            case 2:
                detectU(x, y);
                detectD(x, y);
                break;
            case 3:
                detectL(x, y);
                detectD(x, y);
                break;
            case 4:
                detectR(x, y);
                detectD(x, y);
                break;
            case 5:
                detectU(x, y);
                detectL(x, y);
                break;
            case 6:
                detectR(x, y);
                detectU(x, y);
                break;
        }
    }
}
