/**
 * @author YHZ
 * @date 2020/4/18
 */
public class DualPointer {

    /**
     * 题目：给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2
     *
     * 解法：双指针
     * @param height 垂直线高度
     * @return 最多能容纳的水
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1, res = 0, area = 0;  //left为左边界，right为右边界
        while (left < right) {
            area = (right - left) * Math.min(height[left],height[right]);    //得到当前容量
            res = Math.max(res,area);
            if (height[left] <= height[right]) {    //如果左边界高度小，则左边界改变
                left++;
            }else {     //右边界高度小，右边界改变
                right--;
            }
        }
        return res;
    }
}
