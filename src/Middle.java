import java.util.*;

/**
 * @author YHZ
 * @date 2020/4/22
 */
public class Middle {

    //二叉树的右视图.199
    /*给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值*/
    public List<Integer> rightSideView(TreeNode root){

        //BFS,层次遍历，使用队列。每一层的最后一个元素能被看见
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /*搜索旋转排序数组。.33
    * 假设按照升序排序的数组在预先未知的某个点上进行了旋转,例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
    * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
        你可以假设数组中不存在重复的元素。
        你的算法时间复杂度必须是 O(log n) 级别
    */
    public int search(int[] nums, int target) {

        if (nums.length == 0) return -1;
        else if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) {      //左半边有序
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {    //右半边有序
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;

        /*一种看不懂的解法
        * if((nums[0]<=target)^(target<=nums[mid])^(nums[mid]<nums[0])){
                left = mid+1;
            }
            else
                right = mid;
        }
        return left = (left==right)&&(target==nums[left])?left:-1;*/

        /*解释：
        判断降序点的同时二分数组，数组内部情况可能有下面三种
            注意到原数组为有限制的有序数组（除了在某个点会突然下降外均为升序数组）

            if nums[0] <= nums[I] 那么 nums[0] 到 nums[i] 为有序数组,那么当 nums[0] <= target <= nums[i] 时我们应该在0−i 范围内查找；
            if nums[i] < nums[0] 那么在0-i区间的某个点处发生了下降(旋转),那么 I+1I+1 到最后一个数字的区间为有序数组，并且所有的数字
            都是小于 nums[0] 且大于 nums[i]，当target不属于 nums[0] 到 nums[i] 时（target <= nums[i] < nums[0] or nums[i] < nums[0] <= target）
            我们应该在 0-i区间内查找。可以总结如下
            nums[0] <= target <= nums[mid]
                       target <= nums[mid] < nums[0]
                                 nums[mid] < nums[0] <= target
            if中三项不可能同时为真或假，采用异或操作，若两项为真结果为假，一项为真结果为真*/
    }

    //实现pow(x,n),即x的n次方
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n == 1)
            return x;
        if(n == -1)
            return 1.0 / x;

        double half = myPow(x,n / 2);
        double mod = myPow(x,n % 2);
        return half * half * mod;

    }

    //数组中数字出现次数.面56-1
    /*一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)*/
    public int[] singleNumbers(int[] nums) {

        int a = 0, b = 0, total = 0;
        for (int n : nums) {    //得到异或和
            total ^= n;
        }
        int div = 1;
        while ((div & total) == 0) {    //找到第一个为1的位
            div <<= 1;
        }
        //根据这一位对数组分组,分组方法：该位为0为一组，该位为1为一组；
        // 注：div除一位为1外其他为均为0。两个相同的数对应位也相同，所以相同的数肯定分在同一组；又因为当前位为1则a和b当前位不同所以a和b不在同一组
        // 即a和b分别在不同的组,且两组其他数均出现两次，然后两组分别异或和得到a,b
        for (int n : nums) {
            if ((div & n) == 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a,b};
    }

    /*每日温度.739
    * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替
    * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]
    * */
    public int[] dailyTemperatures(int[] T) {
        return new int[]{1};
    }

    /*快乐数.202
    * 编写一个算法来判断一个数 n 是不是快乐数
    * 快乐数即将对于一个正整数，该数替换为每个位置上的平方和，如果最后能变为1则该数是快乐数*/
    public boolean isHappy(int n) {

        /*Set<Integer> set = new HashSet<>();     //使用set来判断是否出现循环情况
        int up = n, d, temp = 0;
        while (up != 1 && !set.contains(up)) {
            set.add(up);
            up = this.getNext(up);
        }
        return up == 1;*/

        //弗洛伊德循环查找算法,双指针
        //如果没有循环，那么fast一定先到1，跳出循环；如果有循环，那么fast与slow始终会相遇，跳出循环
        int slow = n, fast = this.getNext(n);
        while (fast != 1 && fast != slow) {
            slow = this.getNext(slow);
            fast = this.getNext(getNext(fast));
        }
        return fast == 1;

    }
    public int getNext(int n) {     //计算各位平方和
        int sum = 0, d;
        while (n > 0) {
            d = n % 10;
            n /= 10;
            sum += d * d;
        }
        return sum;
    }

    /*无重复字符的最长子串.3
    * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度*/
    public int lengthOfLongestSubstring(String s) {

        //滑动窗口
        //记录字符上一次出现的位置
        Map<Character,Integer> map = new HashMap<>();
        int res = 0, left = 0;  //left为左指针，i为右指针
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);    //如果有重复元素则左指针右移至重复元素的右邻位置
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    /*两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表,你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
     * eg:给定 1->2->3->4, 你应该返回 2->1->4->3*/
    public Simple.ListNode swapPairs(Simple.ListNode head) {

        if (head == null || head.next == null) return head;     //如果之后有一个节点或者无节点则不用交换

        //两个需要交换的节点
        Simple.ListNode p1 = head;
        Simple.ListNode p2 = head.next;

        //交换
        p1.next = swapPairs(p2.next);   //递归连接所有奇数节点
        p2.next = p1;   //弹栈连接偶数与奇数节点

        //交换完成p2为头结点
        return p2;
    }

    /*另一棵树的子树.572
    * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
    * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树*/
    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (s == null) return false;
        if (t == null) return true;     //空树是任何树的子树
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSame(s, t);   //t为s的左右子树或者s本身和t相同
    }
    public boolean isSame(TreeNode s, TreeNode t) {

        //根据结构判断是否相同
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        //进而根据val判断是否相同
        if (s.val != t.val) return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    /*
    * 验证二叉搜索树.98
    * 验证给定树是否为有效的二叉搜索树*/
    public boolean isValidBST(TreeNode root) {
        return dfsBST(root, null, null);
    }
    public boolean dfsBST(TreeNode root, Integer low, Integer upper) {
        if (root == null) return true; //空树是一个二叉搜索树
        Integer val = root.val;
        if (low != null && val <= low) return false;    //根节点比左子节点小不是
        if (upper != null && val >= upper) return false; //根节点比右子节点大不是
        //递归左右子节点
        if (!dfsBST(root.left, null, val)) return false;    //判断左子树时应该将上界更改为根节点的值
        if (!dfsBST(root.right, val, null)) return false; //判断右子树时应该将下界更改为根节点的值
        //递归完成返回true
        return true;
    }

    /*
    给你一个字符串 s，找到 s 中最长的回文子串。
     */
    public String longestPalindrome(String s) {

        if (s.length() <= 1) {
            return s;
        }
        char[] charArr = s.toCharArray();
        String res = "";
        int l = 0;

        for (int i = 0; i < s.length(); ++i) {

        }
        return "";
    }
}
