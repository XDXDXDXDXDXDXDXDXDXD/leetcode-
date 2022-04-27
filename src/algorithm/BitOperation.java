package algorithm;

/**
 * @author YHZ
 * @date 2020/4/18
 */
public class BitOperation {

    /**
     * 题目：交换两数且不使用额外的空间
     * 解法：异或运算
     * @param a
     * @param b
     */
    public void singleSwap(int a, int b) {
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
    }

    /**
     * 题目：给定一个数组，其中除一个数出现次数为1外其余数出现次数为2，找出只出现次数为1的数
     * 解法：位运算--》异或，两位相同为0，两位不同为1
     * @param nums 给定数组
     * @return 只出现一次的数
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 题目：给定一个数组，其中除一个数出现次数为1外其余数出现次数为3，找出只出现次数为1的数
     * 解法：只需找到一种运算法则，使得第一次出现和第二次出现与第一题相同，第三次出现时其抵消
     * @param nums 给定数组
     * @return 只出现一次的数
     */
    public int singleNumber2(int[] nums) {
        int once = 0, twice = 0;    //once表示出现一次，twice表示出现两次
        for (int num : nums) {
            once = ~twice & (once ^ num);
            twice = ~once & (twice ^num);
        }
        return once;
    }
}
