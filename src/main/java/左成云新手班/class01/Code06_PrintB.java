package 左成云新手班.class01;


public class Code06_PrintB {
    public static void main(String[] args) {

        // 32位
        int num = 1;
        print(num);
    }

    /**
     *
     * 打印整数的二进制（位运算）。
     * 整数1二进制为 00000000000000000000000000000001。左左31位后 1000000000000000000000000000000。
     * & 2个数与逻辑，只有当两个数均为1时，值才为1，否则为0
     *
     * @param num
     */
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}
