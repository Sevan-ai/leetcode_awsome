package class01;

public class Code01_SelectionSort {

    public static void main(String[] args) {
        int[] arr = {100, 55, 23, 4, 11, 23, 1};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 冒泡排序练习
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~ N-1
        // 1~ n-1
        // 2
        for (int i = 0; i < arr.length - 1; i++) {
            // 最小值在i ~ n-1位置上
            int minIndex = i;
            // 注意i和j的范围区别，i可以不用取到 arr.length -1，而j需要。不然永远比较不到最后一个元素
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}























