/**
 * 单词长度最大的面积
 *
 * 输入一个字符串数组words，请计算不包含相同字符的两个字符串words[i]和words[j]的长度乘积的最大值。如果所有字符串都包含至少一个相同
 * 字符，那么返回0.假设字符串中只包含英文小写字母。例如，输入字符串数组words为["abcw", "foo", "bar", "fxyz", "abcdef"]，数组中的
 * 字符串"bar"和"foo"没有相同的字符，它们长度的乘积为9。"abcw"与"fxyz"有相同的字符，它们长度的乘积为16，这是该数组不包含相同字符的
 * 一对字符串的长度乘积的最大值
 */
public class Question5 {
    public static int maxProduct1(String[] words) {
        // 设置一个标志数组，所有置为true的位置都是出现过的字符
        boolean[][] flags = new boolean[words.length][26];

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                // 将每个字符串中出现的字符对应到二维数组中然后置为true
                flags[i][c - 'a'] = true;
            }
        }

        int result = 0;
        // 两行两行进行比较，有true的即为存在相同字符，直接跳过
        for (int i = 0; i < flags.length; i++) {
            for (int j = i + 1; j < flags.length; j++) {
                int k = 0;
                for (; k < 26; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }

                // 当k为26时，说明已经全部比较过，可以直接计算乘积
                if (k == 26) {
                    int prod = words[i].length() * words[j].length();
                    result = Math.max(result, prod);
                }
            }
        }

        return result;
        /*
        上述代码可以分为两步：
            1. 初始化每个字符串对应的哈希表，若words的长度为n，字符串的平均长度为k，那么初始化哈希表的时间复杂度为O(nk)；
            2. 根据哈希表判断每对字符串是否包含相同的字符。总共有O(n^2)对字符串，判断每对字符串是否包含相同的字符需要的时间为O(1)，
               ，因此这一步的时间复杂度为O(n^2)。
         综上所述，该算法的时间复杂度为O(nk + n^2)，空间复杂度为O(n)
         */
    }

    public static int maxProduct2(String[] words) {
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                // 将每个字符串中的字符以对应的二进制位置存入数组中
                // int是32位的，而字母一共26位，因此不必担心溢出情况
                // a - a的整数形式是0，z - a的整数形式是26
                // 它们对应的二进制位置分别是0000 0000 0000 0000 0000 0000 0000 0000
                //                        0000 0100 0000 0000 0000 0000 0000 0000
                // 当对应的位置上有1时，说明该字符出现过
                flags[i] |= 1 << (c - 'a');
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // flags中的两个值进行比较时，本质上就是两个字符串进行比较
                // 若两个字符串中没有相同的字符，那么与运算的结果必为0
                if ((flags[i] & flags[j]) == 0) {
                    int prod = words[i].length() * words[j].length();
                    result = Math.max(result, prod);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"abcw", "foo", "bar", "fxyz", "abcdef"};
//        System.out.println(maxProduct1(words));
        System.out.println(maxProduct2(words));
    }
}
/*
    思路：
    第一反应是基于字符串str1中的每个字符ch，扫描字符串str2判断字符ch是否存在。如果两个字符串的长度分别为p和q，那么时间复杂度位O(pq)
    maxProduct1：用哈希表进行优化。
        对于每个字符串，可以用一个哈希表记录出现在该字符串中的所有字符。在判断两个字符串str1和str2中是否有相同的字符时，只需要从'a'
        到'z'判断某个字符是否在两个字符串串对应的哈希表中都出现了。在哈希表中查找的时间复杂度时O(1)。由于这个题目只需要考虑26个英文
        小写字母，因此可以用一个长度为26的布尔型数组来模拟哈希表。数组下标为0的值表示字符'a'是否出现，以此类推。
    maxProduct2：用整数的二进制位记录字符串中出现的字符
        Java中int型整数的二进制形式有32位，但只需要26位就能表示一个字符串中出现的字符，因此可以用一个int型整数记录某个字符串中出现
        的字符。如果字符串中包含'a'，那么整数最右边的数位为1；如果字符串中包含'b'，那么整数的倒数第二位为1，以此类推。如果两个字符串
        中包含相同的字符，那么它们对应的整数的某个数位都为1，两个整数的与运算将不会为0。如果两个字符串没有相同的字符，那么它们对应的整
        数的与运算的结果等于0
 */