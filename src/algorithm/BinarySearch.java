package algorithm;

public class BinarySearch {
    //二分查找 给定一个n个元素有序的（升序）整型数组nums和一个目标值 target，写一个函数搜索nums中的target，如果目标值存在返回下标，否则返回-1
    public int Binary_search(int[] nums, int target){
        if(nums.length==0) return -1;
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]<target)
                left = mid+1;
            else
                right = mid-1;
        }
        return -1;
    }

    //x的平方根实现 int sqrt(int x) 函数。计算并返回x的平方根，其中x是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去
    public int mySqrt(int x){
        //2ms 80% 或者牛顿迭代
        long left = 0,right = x;        //平方时可能越int界使用long
        //二分查找
        while(left<right){
            long mid = (left+right+1)>>1;   //向上取整否则可能死循环（考虑left=3,right=4的情况）
            long square = mid*mid;
            if(square>x)
                right = mid-1;
            else
                left = mid;
        }
        return (int)left;

        /*数学方法，换底公式，Math.exp(n):e的n次方，Math.log(x):lnx，由于计算结果为浮点数所以最后需要判断哪个是正确答案
        if (x == 0) {
            return 0;
        }
        int ans = (int)Math.exp(0.5 * Math.log(x));
        return (long)(ans + 1) * (ans + 1) <= x ? ans + 1 : ans;*/

    }

    /*搜索旋转数组 假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
    你可以假设数组中不存在重复的元素。
    你的算法时间复杂度必须是 O(log n) 级别。*/
    public int searchRotatedArray(int[] nums, int target){
        //复杂度为log n
        int left = 0,right = nums.length-1;
        while(left<right){
            int mid = (left+right)>>1;
            /*判断降序点的同时二分数组，数组内部情况可能有下面三种
            注意到原数组为有限制的有序数组（除了在某个点会突然下降外均为升序数组）

            if nums[0] <= nums[I] 那么 nums[0] 到 nums[i] 为有序数组,那么当 nums[0] <= target <= nums[i] 时我们应该在0−i 范围内查找；
            if nums[i] < nums[0] 那么在0-i区间的某个点处发生了下降(旋转),那么 I+1I+1 到最后一个数字的区间为有序数组，并且所有的数字
            都是小于 nums[0] 且大于 nums[i]，当target不属于 nums[0] 到 nums[i] 时（target <= nums[i] < nums[0] or nums[i] < nums[0] <= target）
            我们应该在 0-i区间内查找。可以总结如下
            nums[0] <= target <= nums[mid]
                       target <= nums[mid] < nums[0]
                                 nums[mid] < nums[0] <= target
            if中三项不可能同时为真或假，采用异或操作，若两项为真结果为假，一项为真结果为真
            */
            if((nums[0]<=target)^(target<=nums[mid])^(nums[mid]<nums[0])){
                left = mid+1;
            }
            else
                right = mid;
        }
        return left = (left==right)&&(target==nums[left])?left:-1;
    }

    /*寻找峰值
    峰值元素是指其值大于左右相邻值的元素。
    给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
    数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
    你可以假设 nums[-1] = nums[n] = -∞。*/
    public int findPeakElement(int[] nums){
        int left = 0,right = nums.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(nums[mid]>nums[mid+1]){      //局部下降搜索区间左移
                right = mid;
            }
            else{       //局部上升右移
                left = mid+1;
            }
        }
        return left;
    }
}
