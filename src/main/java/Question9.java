/**
 * 乘积小于k的子数组
 *
 * 输入一个由正整数组成的数组和一个正整数k，请问数组中有多少个数字的乘积小于k的连续子数组？例如，输入数组[10,5,2,6]，k的值为100，
 * 有8个子数组的所有数字的乘积小于100，它们分别是[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]和[5,2,6].
 */
public class Question9 {
    public static int minSubArrayLen(int k, int[] nums) {
        long product = 1;
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (left <= right && product >= k) {
                product /= nums[left++];
            }

            count = count + (right >= left ? right - left + 1 : 0);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        System.out.println(minSubArrayLen(100, nums));
    }
}
/*
    思路：
    设置两个指针P1和P2，它们都指向数组中的第一个元素，当两个指针之间的子数组中所有数字之和大于或等于k的时候，将P1向右移动，并将子数组
    中最左边的数字删除；当两个指针之间的子数组中所有数字之和小于k时，将P2向右移动，并将新的数值相加。通过不断循环获取最短子数组。

    上述代码的时间复杂度为O(n)。这是因为变量left和right都是只增加不减少，变量right从0增加到n-1，变量left从0最多增加到n-1，因此总的
    执行次数时O(n)。
 */