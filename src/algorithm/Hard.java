package algorithm;

import java.util.*;

/**
 * @author YHZ
 * @date 2020/4/29
 */
public class Hard implements Question {

    /*山脉数组中查找目标值.1095
    * 寻找在此数组中目标值出现的最小索引，没有出现返回-1
    * 山脉数组即有且仅有一个峰值，且左小于峰值
    * 对MountainArray.get发起至多100次调用。
    *   3 <= mountain_arr.length() <= 10000
        0 <= target <= 10^9
        0 <= mountain_arr.get(index) <= 10^9
    *
    * 思路：二分查找。首先将数组分为两个有序数组（根据峰值），然后在这两个数组中查找 */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peek = findPeekIndex(mountainArr);      //寻找峰顶元素，在其左边为升序数组，右边为降序数组
        if (mountainArr.get(peek) == target) return peek;   //如果峰顶元素就是target则返回

        //由于需要最小索引，先找左边
        int res = findASC(mountainArr, 0, peek - 1, target);
        if (res != -1) {
            return res;
        }
        //找右边
        return findDESC(mountainArr, peek + 1, mountainArr.length() - 1, target);
    }
    //寻找峰顶元素下标
    public int findPeekIndex(MountainArray mountainArray) {
        int left = 0, right = mountainArray.length() - 1;
        while (left < right) {
            int mid = left + (right -left) / 2;
            if (mountainArray.get(mid) < mountainArray.get(mid + 1)) {
                //下一轮： [mid + 1, right]
                left = mid + 1;
            } else {
                //下一轮： [left, mid]
                right = mid;
            }
        }
        return left;
    }
    //在左边升序数组查找
    public int findASC(MountainArray mountainArray, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArray.get(mid) < target) {
                //下一轮： [mid + 1,right]
                left = mid + 1;
            } else {
                //下一轮： [left, mid]
                right = mid;
            }
        }
        if (mountainArray.get(left) == target) return left;
        return -1;
    }
    //在右边降序数组查找
    public int findDESC(MountainArray mountainArray, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left + 1) / 2;    //向上取整，否则由于left=mid会导致死循环
            if (mountainArray.get(mid) < target) {
                //下一轮： [left, mid - 1]
                right = mid - 1;
            } else {
                //下一轮： [mid, right]
                left = mid;
            }
        }
        if (mountainArray.get(left) == target) return left;
        return -1;
    }

    /*跳跃游戏二.45
    *   给定一个非负整数数组，你最初位于数组的第一个位置。
        数组中的每个元素代表你在该位置可以跳跃的最大长度。
        你的目标是使用最少的跳跃次数到达数组的最后一个位置.假设你总是可以到达数组的最后一个位置
        * 输入: [2,3,1,1,4]   输出: 2*/
    public int jump(int[] nums) {
        //贪心算法
        int end = 0, maxPos = 0, step = 0;  //maxPos记录能够到达的最远位置
        for (int i = 0; i < nums.length - 1; ++i) {
            maxPos = Math.max(maxPos, nums[i] + i);
            if (end == i) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    /*二叉树的序列化和反序列化.297
    * eg：一个二叉树可以序列化为 "[1,2,3,null,null,4,5]"，并可以根据此字符串反序列化为一个二叉树*/
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        List<Integer> res = new LinkedList<>();  //接受出队元素(使用LinkedList而非ArrayList因为前者可以存放null值)
        //层次遍历二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);    //根节点入队
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                res.add(curNode.val);
                queue.add(curNode.left);    //出队的同时将左右子节点入队
                queue.add(curNode.right);
            } else {
                res.add(null);
            }
        }

        //对生成的数组进行优化。末尾的null可以去掉，对二叉树结构没有影响
        while (true) {
            if (res.get(res.size() - 1) == null) {
                res.remove(res.size() - 1);
            } else {
                break;
            }
        }

        return res.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data.length() == 0) return null;
        //由于序列化后的字符串格式为:"[1,2,3]",所以需截取字符串
        String[] valuesS = data.substring(1, data.length() - 1).split(", ");    //分割时注意按", "分割，因为数组格式为[10, 5, n]
        //将字符串数组还原为数值数组
        Integer[] values = new Integer[valuesS.length];
        for (int i = 0; i < valuesS.length; ++i) {
            if (valuesS[i].equals("null")) {
                values[i] = null;
            } else {
                values[i] = Integer.parseInt(valuesS[i]);
            }
        }

        Queue<TreeNode> queue = new LinkedList<>();
        //初始化根节点
        TreeNode root = new TreeNode(values[0]);
        queue.add(root);
        //开始生成二叉树
        for (int i = 1; i < values.length; ++i) {
            TreeNode parent = queue.poll();
            if (values[i] != null) {
                TreeNode leftChild = new TreeNode(values[i]);
                parent.left = leftChild;
                queue.add(leftChild);
            }
            //防止越界(考虑[1,2]这类情况)
            if (i + 1 > values.length - 1) return root;
            if (values[++i] != null) {
                TreeNode rightChild = new TreeNode(values[i]);
                parent.right = rightChild;
                queue.add(rightChild);
            }
        }
        return root;
    }
}
