import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组中和为0的3个数字
 *
 * 输入一个数组，如何找出数组中所有和为0的3个数字的三元组？需要注意的是，返回值中不得包含重复的三元组。例如，在数组
 * [-1,0,1,2,-1,4]中有两个三元组的和为0，它们分别是[-1,0,1]和[-1,-1,2].
 */
public class Question7 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        if (nums.length >= 3) {
            // 首先要对数组进行排序
            Arrays.sort(nums);

            int i = 0;
            while (i < nums.length - 2) {
                // 固定一个值，去寻找和为相反数的两个其他值
                twoSum(nums, i, result);
                int temp = nums[i];

                // 去重
                while (i < nums.length && nums[i] == temp) {
                    i++;
                }
            }
        }

        return result;
    }

    private static void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int j = i + 1;
        int k = nums.length - 1;

        while (j < k) {
            if (nums[i] + nums[j] + nums[k] == 0) {
                      result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                      int temp = nums[j];

                      // 去重
                      while (nums[j] == temp && j < k) {
                          j++;
                      }
            } else if (nums[i] + nums[j] + nums[k] < 0) {
                j++;
            } else {
                k--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,4};
        System.out.println(threeSum(nums));
    }
}
/*
    思路：
    该题的解决办法和Q6类似，输入的数组如果是排序的，就可以先固定一个数字i，然后在排序数组中查找和为-i的两个数字。
    由于已经有了O(n)时间在排序数组中找出和为定值的两个数字的方法，因此查找三元组的时间复杂度为O(n^2)。
    但题目并没有说明数组是排序数组，使用Java自带的排序方法的时间复杂度为O(nlogn)，加在一起的时间复杂度仍为O(n^2)。
    由于要避免重复的三元数组，因此函数twoSum使用一个while循环下标j跳过重复的数字。基于同意的原因，函数threeSum中也要有一个while方法去重。
 */