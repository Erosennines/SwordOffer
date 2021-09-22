import java.util.Arrays;

/**
 * 排序数组中的两个数字之和
 *
 * 输入一个递增排序的数组和一个值k，请问如何在数组中找出两个和为k的数字并返回它们的下标？假设数组存在且只存在一对符合条件的数字，
 * 同时一个数字不能使用两次。例如，输入数组[1, 2, 4, 6, 10]，k的值为8，数组中的数字2与6的和为8，它们的下标分别为1与3.
 */
public class Question6 {
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while ((i < j) && (target != numbers[i] + numbers[j])) {
            if (target > numbers[i] + numbers[j]) {
                i++;
            } else {
                j--;
            }
        }

        return new int[]{i, j};
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 6, 10};
        System.out.println(Arrays.toString(twoSum(numbers, 8)));
    }
}
/*
    思路：二分法从两边向中间计算
    在上述代码中，变量i相当于指针P1，变量j相当于指针P2。由于上述代码中只有一个while循环，循环执行的次数最多等于数组的长度，
    因此时间复杂度为O(n)。
 */