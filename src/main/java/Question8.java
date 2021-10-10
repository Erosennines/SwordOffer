/**
 * 和大于或等于k的最短子数组
 *
 * 输入一个正整数组成的数组和一个正整数k，请问数组中和大于或等于k的连续子数组的最短长度是多少？如果不存在所有数字之和大于或等于k的子数组，
 * 则返回0。例如，输入数组[5,1,4,3]，k的值为7，和大于或等于7的最短连续子数组是[4,3]，因此输出它的长度2.
 */
public class Question8 {
    public static int minSubArrayLen(int k, int[] nums) {
        int left = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (left <= right && sum >= k) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {7, 5, 1, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
    }
}
/*
    思路：
    设置两个指针P1和P2，它们都指向数组中的第一个元素，当两个指针之间的子数组中所有数字之和大于或等于k的时候，将P1向右移动，并将子数组
    中最左边的数字删除；当两个指针之间的子数组中所有数字之和小于k时，将P2向右移动，并将新的数值相加。通过不断循环获取最短子数组。

    上述代码的时间复杂度为O(n)。这是因为变量left和right都是只增加不减少，变量right从0增加到n-1，变量left从0最多增加到n-1，因此总的
    执行次数时O(n)。
 */