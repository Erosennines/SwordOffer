/**
 * 只出现一次的数字
 *
 * 输入一个整数数组，数组中只有一个数字出现了一次，而其他数字都出现了3次。请找出那个只出现1次的数字。
 * 例如：如果输入的数组为[0, 1, 0, 1, 0, 1, 100]，则只出现一次的数字是100
 */
public class Question4 {
    public static int singleNumber(int[] nums) {
        int[] bitSums = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitSums[i] += (num >> (31 - i)) & 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + bitSums[i] % 3;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 1, 0, 1, 100};
        System.out.println(singleNumber(arr));
    }
}
/*
    思路：
    TODO 任何数字异或它自己的结果都是0.
    一个整数是由32个0或1组成的。我们可以将数组中所有数字的同一位置的数位相加。如果将出现3次的数字单独拿出来，那么这些出现了3次的数字的任意
    第i个数位之和都能被3整除。因此，
        如果数组中所有数字的任意第i个数位之和都能被3整除，那么只出现一次的数字的第i个数位一定是0；
        如果数组中的所有数字的第i个数位相加之和被3除余1，那么只出现一次的数字的第i个数位一定是1.
    这样只出现一次的任意第i个数位可以由数组中所有数字的第i个数位之和推算出来。当我们知道一个整数任意一位是0还是1之后，就可以知道它的数值。
 */