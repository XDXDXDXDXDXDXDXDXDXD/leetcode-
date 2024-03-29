package algorithm;

import base.dataStructure.Node;

import java.util.*;

/**
 * @author YHZ
 * @date 2020/4/22
 */
public class Middle implements Question {

    //二叉树的右视图.199
    /*给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值*/
    public List<Integer> rightSideView(TreeNode root) {

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
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n == -1)
            return 1.0 / x;

        double half = myPow(x, n / 2);
        double mod = myPow(x, n % 2);
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
        return new int[]{a, b};
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
        Map<Character, Integer> map = new HashMap<>();
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

    /**
     * 给定一个升序数组，找到目标值开始和结束位置
     *
     * @param num
     * @param target
     * @return
     */
    public int[] searchRange(int[] num, int target) {
        int left = 0, right = num.length;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) >> 2;
            int i = num[middle];
            if (i == target) {
                break;
            } else if (i < target) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        if (left == right) {
            return new int[]{-1, -1};
        }

        int start = middle, end = middle;
        while (num[start] == target) {
            start--;
        }
        while (num[end] == target) {
            end--;
        }
        return new int[]{start, end};
    }

    /**
     * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
     * <p>
     * 返回这三个数的和。
     * <p>
     * 假定每组输入只存在恰好一个解。
     */
    public int threeSumClosest(int[] nums, int target) {

        int abs, sum = 0;
        abs = target < 0 ? Integer.MAX_VALUE + target : Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                for (int k = j + 1; k < nums.length; ++k) {
                    if (Math.abs(target - (nums[i] + nums[j] + nums[k])) <= Math.abs(target - abs)) {
                        abs = Math.abs(target - (nums[i] + nums[j] + nums[k]));
                        sum = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 657.机器人是否能返回原点
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {

        int[] arr = new int[]{0, 0, 0, 0};

        char[] chars = moves.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'U':
                    arr[0]++;
                    break;
                case 'D':
                    arr[1]++;
                    break;
                case 'L':
                    arr[2]++;
                    break;
                case 'R':
                    arr[3]++;
                    break;
            }
        }
        return arr[0] == arr[1] && arr[2] == arr[3];
    }

    /**
     * 688.骑士在棋盘上的概率
     * 动态规划，最优子结构
     *
     * @param n      n×n的棋盘
     * @param k      k步
     * @param row    起始行
     * @param column 起始列
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {
        int[][] dirs = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        // dp[step][i][j]表示从(i, j)出发走了step步时仍在棋盘内的概率
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; ++step) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    // 还要走0步时必然在棋盘内
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            // 移动后的位置为(ni, nj))
                            int ni = i + dir[0], nj = j + dir[1];
                            // 如果移动后在棋盘内,1/8为由(ni,nj)到(i,j)的概率
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    /**
     * 969.煎饼排序
     * 可以根据两次翻转将最大值翻转至末尾，找到当前数组最大值并将其翻转至首位，再将当前数组翻转，最大值则到末尾。
     * 完成翻转后去掉末尾元素生成新数组，再对新数组进行上述操作，直至数组长度为1
     *
     * @param arr 原数组
     * @return 煎饼反转的数组形式
     */
    public List<Integer> pancakeSort(int[] arr) {

        List<Integer> res = new ArrayList<>();
        for (int n = arr.length; n > 1; --n) {

            // 找到当前数组的最大值下标(index)
            int index = 0;
            for (int i = 1; i < n; ++i) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }

            // 如果最大值已是最后一个元素则不翻转
            if (index == n - 1) continue;

            // 如果最大值不是第一个元素则需要先翻转至首位
            if (index != 0) {
                reverse(arr, index);
                // 翻转的是[0,..,k - 1],所以k=index + 1
                res.add(index + 1);
            }
            // 翻转最大值至末尾
            reverse(arr, n - 1);
            res.add(n);
        }
        return res;
    }

    // 双指针翻转数组
    private void reverse(int[] arr, int end) {
        for (int i = 0, j = end; i < j; ++i, --j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[j] ^ arr[i];
            arr[i] = arr[i] ^ arr[j];
        }
    }

    /**
     * 838. 推多米诺
     * <p>
     * "RR.L" -> "RR.L
     * ".L.R...LR..L.." -> "LL.RR.LLRRLL.."
     *
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        Deque<Integer> queue = new ArrayDeque<Integer>();
        int[] time = new int[n];
        Arrays.fill(time, -1);
        List<Character>[] force = new List[n];
        for (int i = 0; i < n; i++) {
            force[i] = new ArrayList<Character>();
        }
        for (int i = 0; i < n; i++) {
            char f = dominoes.charAt(i);
            if (f != '.') {
                queue.offer(i);
                time[i] = 0;
                force[i].add(f);
            }
        }

        char[] res = new char[n];
        Arrays.fill(res, '.');
        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (force[i].size() == 1) {
                char f = force[i].get(0);
                res[i] = f;
                int ni = f == 'L' ? i - 1 : i + 1;
                if (ni >= 0 && ni < n) {
                    int t = time[i];
                    if (time[ni] == -1) {
                        queue.offer(ni);
                        time[ni] = t + 1;
                        force[ni].add(f);
                    } else if (time[ni] == t + 1) {
                        force[ni].add(f);
                    }
                }
            }
        }
        return new String(res);
    }

    /**
     * 1706. 球会落何处
     */
    public static int[] findBall(int[][] grid) {

        int n = grid[0].length;
        if (n < 1) {
            return new int[]{};
        }

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int t = i;  // 起始列
            for (int[] row : grid) {
                // 开始移动
                int direction = row[t];
                t += direction;
                // 判断移动后是否在盒子内或者被卡V字形
                if (t < 0 || t == n || row[t] != direction) {
                    t = -1;
                    break;
                }
            }
            res[i] = t; //移动完成
        }

        return res;
    }

    /**
     * 537.复数乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public String complexNumberMultiply(String num1, String num2) {

        String[] t1 = num1.split("\\+");
        String[] t2 = num2.split("\\+");
        int n1 = Integer.parseInt(t1[0]);
        int n2 = Integer.parseInt(t2[0]);
        int nI1 = toNum(t1[1]);
        int nI2 = toNum(t2[1]);

        return (n1 * n2 + (nI1 * nI2) * -1) + "+" + ((n1 * nI2) + (nI1 * n2)) + "i";
    }

    private int toNum(String t) {
        return Integer.valueOf(t.split("i")[0], 10);
    }

    public int searchInsert(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] > target ? 0 : 1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    /**
     * 189. 轮转数组
     */
    public void rotate(int[] nums, int k) {

        if (nums.length == 0 || nums.length == 1) return;
        int realMove = k % nums.length;
        if (realMove == 0) return;
        int i = 0, temp1 = 0, temp2, count = 0, length = nums.length;
        for (int j = 0; j < length; j++) {
            i = j;
            temp1 = nums[i];
            if (count == length) break;
            do {
                temp2 = nums[(i + realMove) % length];
                nums[(i + realMove) % length] = temp1;
                temp1 = temp2;
                i = (i + realMove) % length;
                count++;
            } while (j != i && count < length);
        }
    }

    /**
     * 167.两数之和Ⅱ
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                break;
            }
        }
        res[0] = left + 1;
        res[1] = right + 1;
        return res;
    }

    public Simple.ListNode removeNthFromEnd(Simple.ListNode head, int n) {

        Simple.ListNode dummy = new Simple.ListNode(0, head);
        Simple.ListNode first = head;
        Simple.ListNode second = dummy;

        // 快指针先走n步
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 删除倒数第n个节点
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 3.无重复字符的最长字串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_again(String s) {

        int left = 0, right = 0;
        int res = 0;
        while (left < s.length()) {

        }
        return 1;
    }

    /**
     * 2055.蜡烛之间的盘子：预处理+前缀和
     * 先获取每个位置前缀有多少盘子,获取到蜡烛位置x,y后即可得到答案preSum_y-preSumx(因为x是蜡烛所以不必写成preSum_y-preSum_x-1)
     * 如何获取蜡烛位置:先获取每个位置的左侧和右侧第一个蜡烛位置,则区间的蜡烛位置左端点在所在位置的右侧的第一个蜡烛位置,右端点在所在位置的左侧的一个蜡烛位置
     * @param s
     * @param queries
     * @return
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {

        int length = s.length();
        // 前缀和
        int[] preSum = new int[length];
        // 记录左侧第一个蜡烛位置
        int[] leftFirst = new int[length];
        // 记录右侧第一个蜡烛位置
        int[] rightFirst = new int[length];

        for (int i = 0, sum = 0; i < length; ++i) {
            if (s.charAt(i) == '*') {
                sum++;
            }
            preSum[i] = sum;
        }

        for (int i = 0, left = -1; i < length; ++i) {
            if (s.charAt(i) == '|') {
                left = i;
            }
            leftFirst[i] = left;
        }
        for (int i = length - 1, right = -1; i > -1; --i) {
            if (s.charAt(i) == '|') {
                right = i;
            }
            rightFirst[i] = right;
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] t = queries[i];
            int left = rightFirst[t[0]], right = leftFirst[t[1]];
            res[i] = left == -1 || right == -1 || left >= right ? 0 : preSum[right] - preSum[left];
        }

        return res;
    }

    /**
     * 2049.统计最高分的节点数目
     * @param parents
     * @return
     */
    public int countHighestScoreNodes(int[] parents) {
        return 0;
    }

    /**
     * 590.N叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {

        List<Integer> res = new ArrayList<>();
        postorderDsf(res, root);
        return res;
    }
    private void postorderDsf(List<Integer> res, Node root) {

        if (root == null) return;

        for (Node child : root.children) {
            postorderDsf(res, child);
        }
        res.add(root.val);
    }

    /**
     * 2044.统计按位或能得到最大值的子集数目
     * @param nums
     * @return
     */
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0, cnt = 0;
        for (int i = 0; i < 1 << nums.length; i++) {
            int orVal = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    orVal |= nums[j];
                }
            }
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 2038. 如果相邻两个颜色均相同则删除当前颜色
     * @param colors
     * @return
     */
    public boolean winnerOfGame(String colors) {
        int[] freq = {0, 0};
        char cur = 'C';
        int cnt = 0;
        for (int i = 0; i < colors.length(); i++) {
            char c = colors.charAt(i);
            if (c != cur) {
                cur = c;
                cnt = 1;
            } else {
                cnt += 1;
                if (cnt >= 3) {
                    freq[cur - 'A'] += 1;
                }
            }
        }
        return freq[0] > freq[1];
    }

    /**
     * n!尾随的0的个数
     * @param n
     * @return
     */
    /* 离谱 */
    public static int trailingZeroes(int n) {

        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    /**
     * 2024. 考试的最大困扰度
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * 386.字典序排数
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {

        return new ArrayList<>();
    }

    /*
    713.乘积小于k的子数组
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {


        return 1;
    }

    /*
    942.增减字符串匹配
     */
    public int[] diStringMatch(String s) {

        int n = s.length();
        return new int[0];
    }

    /*
    875.爱吃香蕉的珂珂


    珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。

珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。

   [3,6,7,11]

     */
    public int minEatingSpeed(int[] piles, int h) {

        return 1;
    }


    /*
    18.四数之和
    给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d< n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();


        return res;
    }
}
