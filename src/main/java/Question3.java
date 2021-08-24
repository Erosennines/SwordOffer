import java.util.Arrays;

/**
 * 前n个数据二进制形式中1的个数
 *
 * 输入一个非负数n，请计算0到n之间每个数字的二进制形式中1的个数，并输出一个数组。
 * 例如输入的n为4，由于0、1、2、3、4的二进制形式中1的个数分别为0、1、1、2、1，因此输出数组[0, 1, 1, 2, 1]
 */
public class Question3 {
    public static int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int j = i;
            while (j != 0) {
                result[i]++;
                j = j & (j - 1);
            }
        }

        return result;
    }

    public static int[] countBits2(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public static int[] countBits3(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = countBits1(9);
//        int[] arr2 = countBits2(9);
//        int[] arr3 = countBits3(9);

        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));
//        System.out.println(Arrays.toString(arr3));
    }
}

/*
    思路：
    countBits1
        对于题目而言，一个比较高效的办法是每次用“i & (i - 1)”将正数最右边的1变成0。每次变成功就加1，直到i = 0。
        如果一个整数有k位，极限情况下，将会有k个1，while循环将执行k次，此时时间复杂度为O(kn)
    countBits2
        经过1的解法我们已经知道：i & (i - 1)将i的二进制形式中的最右边的1变成0，也就是说，整数i的二进制形式中1的个数比i & (i - 1）
        多1。
        不管i中的二进制形式中有多少个1，上述代码只根据O(1)的时间就能得出，因此时间复杂度为O(n)
    countBits3
 */
