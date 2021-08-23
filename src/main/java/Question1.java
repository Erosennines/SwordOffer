/**
 * 整数除法
 *
 * 要求输入两个int型整数，它们进行除法计算并返回商，要求不得使用乘号'*'、除号'/'以及求余符号'%'。
 * 当发生溢出时，返回最大的整数值。
 * 假使除数不为0.例如，输入15和2，输出15/2的结果，即7。
 */
public class Question1 {
    public static int divide(int dividend, int divisor) {
        // 首先考虑负数极值的情况，要求返回最大的整数值
        if (dividend == 0x80000000 && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 负数标志位。当被除数和除数异号时，negative必为1，此时结果需要置为负数，否则为正数
        int negative = 2;

        // 将负数转换成正数是一个问题，因为在转换时可能引发溢出问题（0x80000000）
        // 这里建议将正数全部转换为负数进行处理
        if (dividend > 0) {
            negative--;
            dividend = -dividend;
        }

        if (divisor > 0) {
            negative--;
            divisor = -divisor;
        }

        int result = divideCore(dividend, divisor);
        return negative == 1 ? -result : result;
    }

    public static int divideCore(int dividend, int divisor) {
        int result = 0;
        // 由于转换成了负数进行处理，要注意是小于等于
        while (dividend <= divisor) {   // 为什么要进行循环判断？只用if会有什么后果？（思考当除数为1、-1时）
            int value = divisor;
            int quotient = 1;

            // 优化：当除数的已经小于 最大负数的一半（用绝对值理解比较容易，就是除数已经超过被除数的一半多，不能商2，只能商1）
            // 且 被除数大于2倍的value值时，已经没必要进行判断了
            // 首先要理解解体思路，value + value相当于一个倍数叠加的过程，被除数大于2倍的value值就停止循环是一种优化的策略（详见思路）
            // 此处的被除数是一个优化后的被除数，而非原被除数
            while (value >= 0xc0000000 && dividend <= value + value) {
                quotient += quotient;
                value += value;
            }

            result += quotient;
            dividend -= value;
        }

        return result;
    }

    public static void main(String[] args) {
        int result = divide(3, 1);
        System.out.println(result);
    }
}
/*
  思路：
  题目的思路应该基于减法实现除法。例如为了求的15/2的商，可以不断的从15里减去2。当余数为1时停止。
  但是这个方法在极限情况下（2^32 - 1） / 1 将执行（2^32 - 1） 次，若被除数为n，其时间复杂度为O(n)。
  优化：当被除数大于除数时，继续判断被除数是否大于除数的2倍，如果是，则继续判断被除数是否大于除数的4倍，8倍等。
  如果被除数最多大于除数的2^k倍，那么将被除数减去除数的2^k倍，然后将剩余的被除数重复前面的步骤。
  由于每次将除数翻倍，因此优化后的时间复杂度是O(n)
  最后则是需要考虑溢出的情况，由于正数比负数少一位，因此在计算时尽量转为负数进行处理。
 */