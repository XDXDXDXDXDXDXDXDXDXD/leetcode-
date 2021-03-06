
import java.util.*;

import static java.lang.Math.abs;

/**
 * @author YHZ
 * @date 2020/4/19
 */
public class Simple {

    //给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数
    public int findNumbers(int[] nums) {

        int height = 1, count = 0;
        for (int num : nums) {
            while (num / 10 > 1) {
                height++;
                num /= 10;
            }
            if (height % 2 == 0) {
                count++;
            }
            height = 1;
        }
        return count;
    }

    //有多少小于当前数字的数字。给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目，以数组形式返回.1365
    //数值范围0~100
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] bucket = new int[101];    //索引即num，值即num出现的频率
        for (int num : nums) {
            bucket[num]++;  //每出现一次频率加一
        }
        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i-1];   //索引仍然是num，值变为比num小的数的个数
        }
        int[] res  = new int[nums.length]; //输出结果
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > 0) res[j] = bucket[nums[j]-1];     //比其小应该是下标为nums[j]-1的值,判断条件不可少，否则nums[j]-1为负
        }
        return res;
    }

    /*
    *解压缩编码列表。.1313
    *输入：nums = [1,2,3,4]
    *输出：[2,4,4,4]
    *解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
    *第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
    *最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]
    * */
    public int[] decompressRLElist(int[] nums) {

        int length = 0; //应该生成新数组的长度
        for (int i = 0; i < nums.length; i += 2) {
            length += nums[i];
        }
        int[] res = new int[length];
        int index = 0; //新数组索引
        for (int i = 0; i < nums.length; i += 2) {
            int count = nums[i];    //偶数索引代表个数
            while (count > 0) {
                res[index] = nums[i+1];
                count--;
                index++;
            }
        }
        return res;
    }

    /*
    * 按既定顺序创建目标数组。.1389
    * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
        目标数组 target 最初为空。
        按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
        重复上一步，直到在 nums 和 index 中都没有要读取的元素。
        请你返回目标数组。
        题目保证数字插入位置总是存在
        * 实例：
        *   输入：nums = [0,1,2,3,4], index = [0,1,2,2,1]
            输出：[0,4,1,3,2]
            解释：
            nums       index     target
            0            0        [0]
            1            1        [0,1]
            2            2        [0,1,2]
            3            2        [0,1,3,2]
            4            1        [0,4,1,3,2]
    * */
    public int[] createTargetArray(int[] nums, int[] index) {

         int [] target = new int[nums.length];
         for(int i = 0; i < nums.length; i++){
             if (index[i] < i) {    //当插入位置已经有值时，其后的元素一次后移
                 for(int j = i; j > index[i]; j--){
                     target[j]=target[j-1];
                 }
             }
             target[index[i]]=nums[i];
         }
         return target;
    }

    /*IP地址无效化 .1108
    给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
    所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."*/
    public String defangIPaddr(String address) {

        char[] cs = address.toCharArray();
        char[] res = new char[address.length()+6];
        int j = 0;
        for (int i = 0; i < cs.length; i++, j++){
            if (cs[i] == '.') {
                res[j++] = '[';
                res[j++] = '.';
                res[j] = ']';
            }
            else res[j] = cs[i];
        }
        return String.valueOf(res);
    }

    /*访问所有点的最小时间。.1266
    *   平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
        你可以按照下面的规则在平面上移动：
        每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。
        必须按照数组中出现的顺序来访问这些点*/
    public int minTimeToVisitAllPoints(int[][] points) {
        int x0 = points[0][0], x1 = points[0][1];   //x0,y0为横坐标；x1,y1为纵坐标
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int y0 = points[i][0], y1 = points[i][1];
            ans += Math.max(Math.abs(x0 - y0), Math.abs(x1 - y1));
            x0 = y0;    //下两个点的距离
            x1 = y1;
        }
        return ans;
    }

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*删除中间节点。.面02.03
    * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点*/
    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;     //因为不会是最后一个节点，所以不用判断是否为空
    }

    /*二进制链表转整数。.1290
    * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
      请你返回该链表所表示数字的 十进制值*/
    public int getDecimalValue(ListNode head) {

        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            res = res * 2 + cur.val;    //反向运算
            cur = cur.next;
        }
        return res;
    }

    //返回倒数第k个节点
    public int kthToLast(ListNode head, int k) {

        ListNode res = head, ref = head;
        while (k-- > 0){
            ref = ref.next;     //参考点先走k步
        }
        while (ref != null) {
            res = res.next;
            ref = ref.next;
        }
        return res.val;
    }

    /*重新格式化字符串。.1417
    *给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
     请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
     请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串
    */
    public String reformat(String s) {
        Stack<Character> number = new Stack<>();
        Stack<Character> letter = new Stack<>();
        Stack<Character> temp = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c <= 57) {  //0~9的ascii码为48~57
                number.add(c);
            } else {    //a~z为97~122
                letter.add(c);
            }
        }
        if (Math.abs(number.size() - letter.size()) > 1) {      //数字字母数量相差大于1则不能完成格式化
            return res.toString();
        }
        if (number.size() > letter.size()) {    //数量多的总是排在第一个，否则无法完成
            temp = number;
            number = letter;
            letter = temp;
        }
        while (letter.size() > 0) {
            res.append(String.valueOf(letter.pop()));
            if (number.isEmpty()) {
                continue;
            } else {
                res.append(String.valueOf(number.pop()));
            }
        }
        return res.toString();
    }

    /*打印从1到最大的n位数。.面17
    * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999*/
    public int[] printNumbers(int n) {

        int l = (int) Math.pow(10,n) - 1;
        int[] res = new int[l];
        for (int i = 0, k = 1; i < l; i++) {
            res[i] = k++;
        }
        return res;
    }

    /*最小高度树。.面04.02
    * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树*/
    public TreeNode sortedArrayToBST(int[] nums) {

        return BSTHelper(nums, 0, nums.length -1);
    }
    public TreeNode BSTHelper(int[] nums, int left, int right) {

        if (left > right) return null;
        int mid = (left + right) >> 1;
        TreeNode n = new TreeNode(nums[mid]);   //由于是升序数组，高度最小即根节点为中间元素
        n.left = BSTHelper(nums, left, mid - 1);    //左子树
        n.right = BSTHelper(nums, mid + 1, right);      //右子树
        return n;
    }

    /*二叉树的深度。.面55 - 1*/
    public int maxDepth(TreeNode root) {

        return root == null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

    /*有效的括号.20
    * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
    * 有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
    * 空字符串可以认为是有效字符串*/
    public boolean isValid(String s) {

        /*if (s.length() % 2 != 0) return false;
        if (s.equals("")) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')') {
                if (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
                else stack.push(c);
            }
            else if (c == ']') {
                if (!stack.isEmpty()) {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
                else stack.push(c);
            }
            else if (c == '}') {
                if (!stack.isEmpty()) {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
                else stack.push(c);
            }
        }
        return stack.size() == 0;*/
        Map<Character,Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                char top = stack.empty() ? '#' : stack.pop();   //和栈顶元素匹配成功继续，且匹配的pop出栈
                if (top != map.get(c)) {    //不匹配则无效
                    return false;
                }
            } else {
                stack.push(c);  //是括号开始则压栈
            }
        }
        return stack.isEmpty(); //最后如果栈中无元素即全部匹配，不为空则未完全匹配

    }

    /*最大子序和.53
    * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和*/
    public int maxSubArray(int[] nums) {
        //动态规划
        //将节点以结束位置作为子数组，则后一个节点的最大和总是与前一个相关
        int res = nums[0], sum = 0;
        for (int num : nums) {
            //if(sum + num > num)
            if (sum > 0) {
                sum = sum + num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /*合并两个有序链表
    * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode l3 = new ListNode(-1);
        ListNode pre = l3;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return l3.next;

        /*
        递归
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }*/

    }

    /*斐波那契数列,需递归记忆化实现*/
    public int fib(int N) {
        Map<Integer, Integer> map = new HashMap<>(6);
        int res;
        if (N < 2) return N;
        if (map.containsKey(N)) {
            return map.get(N);
        } else {
            res = fib(N - 1) + fib(N - 2);
        }
        map.put(N, res);
        return res;
    }

    /*爬楼梯。需递归记忆化实现
    *   假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
        每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢*/
    public int climbStairs(int n) {
        //动态规划
        if (n == 1) return 1;
        int[] dp = new int[n + 1];  //由于台阶由1开始所以大小为n+1.dp[i]表示上i阶台阶的方法总数
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*分割平衡字符串
    * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的，给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串
    * 返回可以通过分割得到的平衡字符串的最大数量
    * 输入：s = "RLLLLRRRLR"
    * 输出：3*/
    public int balancedStringSplit(String s) {
        int num = 0,res = 0;
        for (int i = 0; i < s.length(); ++i) {
            num = s.charAt(i) == 'R' ? num+1 : num-1;
            if (num == 0) res++;    //尽可能多，贪心，即只要有L和R数量相等的情况就分割
        }
        return res;
    }

    /*统计有序矩阵中的负数.1351
    * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列
    * 请你统计并返回 grid 中 负数 的数目
    * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]],输出：8*/
    public int countNegatives(int[][] grid) {

        int res = 0;
        for (int i = grid.length - 1; i >= 0; --i) {

            for (int j = grid[0].length - 1; j >= 0; --j){
                if (grid[i][j] < 0) {
                    res++;
                }

                if (grid[i][j] >= 0)   break;   //其之前的数都为正数
            }
        }
        return res;
    }

    /*
    * 删除最外层的括号.1021
    * */
    public String removeOuterParentheses(String S) {

        //妙啊妙啊
        StringBuilder res = new StringBuilder();
        int pointer = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') pointer--;
            if (pointer >= 1) res.append(c);
            if (c == '(') pointer++;
        }
        return res.toString();
    }

    /*
    * 将每个元素替换为右侧最大元素.1299
    * 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换
    * 输入：arr = [17,18,5,4,6,1],输出：[18,6,6,6,1,-1]
    * */
    public int[] replaceElements(int[] arr) {

//        方法一 暴力从前往后
//        for (int i = 0; i < arr.length - 1; ++i) {
//            int max = arr[i + 1];
//            for (int j = i + 1; j < arr.length; ++j) {
//                if (max <= arr[j]) {
//                    max = arr[j];
//                }
//            }
//            arr[i] = max;
//        }
//        arr[arr.length - 1] = -1;
//        return arr;

//        方法二 使用额外空间
//        int [] help = new int[arr.length];
//        if (arr.length == 1) {
//            help[0] = -1;
//        }
//        else if (arr.length == 2) {
//            help[0] = arr[1];
//            help[1] = -1;
//        }
//        else {
//            help[arr.length - 2] = arr[arr.length - 1];
//            help[arr.length - 1] = -1;
//            for (int i = arr.length - 3; i >= 0; --i) {
//                help[i] = Math.max(arr[i + 1], help[i + 1]);
//            }
//        }
//        return help;

        //方法三 从后往前遍历
        int rightMax = arr[arr.length-1];
        arr[arr.length-1] = -1;
        for(int i = arr.length-2; i>=0; i--){
            int t = arr[i];
            arr[i] = rightMax;
            if(t>rightMax){
                rightMax = t;
            }
        }
        return arr;
    }

    /*
    * 合并二叉树.617
    * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠
    * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
    * 否则不为 NULL 的节点将直接作为新二叉树的节点*/
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;

        //递归遍历左右子树
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /*
    * 对称二叉树
    * 给定一个二叉树，检查是否镜像对称*/
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    public boolean isMirror(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) return true;
        else if (t1 == null || t2 == null) return false;    //左右子树结构不一样肯定不对称
        //互为镜像有两个条件：
        // 1.根节点值相同
        // 2.左右子树互为镜像
        return (t1.val == t2.val) && isMirror(t1.left, t2. right) && isMirror(t1.right, t2.left);
    }

    /*
    * 路径总和
    * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和*/
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if (root.left == null && root.right == null){   //如果root为叶子节点则判断目标和是否为0
            return sum == 0;
        }
        //否则继续遍历左右子树
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    /*
    * 汉明距离
    * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
    * 给出两个整数 x 和 y，计算它们之间的汉明距离*/
    public int hammingDistance(int x, int y) {
        /*String xs = Integer.toBinaryString(x);
        String ys = Integer.toBinaryString(y);
        int length = Math.max(xs.length(), ys.length());
        int minLength = Math.min(xs.length(), ys.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - minLength; ++i) {
            sb.append(0);
        }
        String s = sb.append(xs.length() < ys.length() ? xs : ys).toString();
        String sl = xs.length() < ys.length() ? ys : xs;
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (((int)s.charAt(i) ^ (int)sl.charAt(i)) == 1) {
                res += 1;
            }
        }
        return res;*/

        /*return Integer.bitCount(x ^ y);*/
        //布赖恩·克尼根算法
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'
            xor = xor & (xor - 1); //（一步排除两个1之间的0）
        }
        return distance;

    }

    /*
    * 从尾到头打印链表
    * 输入链表头结点，从尾到头返回每个节点的值*/
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode root = head;
        int length = 0;
        while (root != null) {
            stack.push(root);
            root = root.next;
            length++;
        }
        int[] res = new int[length];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop().val;
        }
        return res;
    }

}
