/**
 * 二进制加法
 *
 * 输入两个表示二进制的字符串，请计算它们的和，并以二进制字符串的形式输出。例如，输入的二进制字符串分别是“11”和“10”，输出“101”
 */
public class Question2 {
    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        // 通过length函数定位到字符串的最末位，类似普通加法，从右往左
        int i = a.length() - 1;
        int j = b.length() - 1;

        // 进位标志，满2进1
        int carry = 0;

        while (i >= 0 || j >= 0) {
            // 通过减去字符串0获取真正的数字0或1
            int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int digitB = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = digitA + digitB + carry;

            // 标志位满2进1
            carry = sum >= 2 ? 1 : 0;

            // 二进制不能出现大于2的值
            sum = sum >= 2 ? sum - 2 : sum;

            // 结果添加到字符串中
            result.append(sum);
        }

        // 当进行到最高位时需要根据标志位判断是否需要进位
        if (carry == 1) {
            result.append(1);
        }

        // 从低到高添加，需要反转
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11111111111111111111111", "10101010101010110101010110"));
    }
}
/*
   思路：
   看到题目的第一反应是将字符串转成int或long，然而这存在一个问题，字符串没有限制长度，可能会引发溢出。
   因此加法操作需要针对字符串进行。完全可以参照十进制加法来完成二进制加法。
   我们需要将右边对齐，然后从个位开始相加，如果有进位还需要加上进位。
   最后需要注意由于是从右向左计算，最后得到的字符串需要进行翻转
 */