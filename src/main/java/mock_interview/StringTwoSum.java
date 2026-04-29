package mock_interview;

/**
 * 大数相加的标准做法，是直接在字符串上模拟人工加法过程，避免任何数值类型溢出：
 *
 */
public class StringTwoSum {
    public static void main(String[] args) {
        String result = addStrings("9333852702227987", "85731737104263");
        System.out.println("result = " + result);
    }

    /**
     * 存在2个问题
     * 1、Java 中 long 类型的最大值是：
     * 9,223,372,036,854,775,807（19 位数字）
     * 而你输入的 num1 是 "9333852702227987"，这是 16 位数字，虽然表面上没超过 19 位，但加上后续的乘法和累加过程，中间值会很快超过 long 的上限，导致溢出。
     * 溢出的结果是：数值会被截断成一个完全错误的结果，而不是抛出异常，所以你看到 result = 9333852702227988 这种末位 + 1 的诡异现象。
     *
     * 2、 Math.pow(10, index) 带来的浮点精度丢失
     * Math.pow() 函数返回的是 double 类型，而 double 只有 53 位有效二进制位，无法精确表示所有整数：
     * 当指数较大时，Math.pow(10, index) 会出现精度丢失（比如本该是 10000，实际返回 9999.999999999998）
     * 当你把这个结果强制转成 long 时，会直接截断小数部分，导致数值错误
     * 你的代码里，每次循环都用 (num1.charAt(i) - '0') * Math.pow(10, index) 计算，本质上是把字符串转成了 double 再转 long，双重精度损失
     * @param num1
     * @param num2
     * @return
     */
//    public static String addStrings(String num1, String num2) {
//        long res = 0;
//        int index = 0;
//        for (int i = num1.length() - 1; i >= 0; i--) {
//            res += (long) ((num1.charAt(i) - '0') * Math.pow(10, index));
//            index++;
//        }
//
//        index = 0;
//        for (int i = num2.length() - 1; i >= 0; i--) {
//            res += (long) ((num2.charAt(i) - '0') * Math.pow(10, index));
//            index++;
//        }
//
//        return String.valueOf(res);
//    }

    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0; // 进位
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += num1.charAt(i--) - '0';
            if (j >= 0) sum += num2.charAt(j--) - '0';
            carry = sum / 10; // 计算进位
            sb.append(sum % 10); // 当前位结果
        }
        return sb.reverse().toString();
    }

}
