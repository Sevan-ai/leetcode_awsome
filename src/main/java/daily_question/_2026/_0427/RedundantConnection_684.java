package daily_question._2026._0427;

import java.util.Arrays;

/**
 * 684. 冗余连接
 */
public interface RedundantConnection_684 {
    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        Solution2 solution = new Solution2();

        int[] result = solution.findRedundantConnection(edges);
        System.out.println("result: " + Arrays.toString(result));
    }
}


class Solution2 {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 初始化将根节点指向自身
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                return edge;
            }
        }
        return new int[0];
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}