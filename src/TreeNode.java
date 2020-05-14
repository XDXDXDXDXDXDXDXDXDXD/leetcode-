import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//二叉树
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    List<List<Integer>> res = new ArrayList<>();                  //接收层次遍历的结果集
    private int depth = 0;  //二叉树的最大深度
    List<Integer> out = new ArrayList<Integer>();               //接收中序遍历的结果集
    TreeNode(int x){
        val = x;
    }

    //层次遍历二叉树（队列）
    public List<List<Integer>> levelOrder(TreeNode root){
        if(root == null) return res;
        Queue<TreeNode> link = new LinkedList<>();                //使用队列进行层次遍历
        link.add(root);
        while(!link.isEmpty()){
            int count = link.size();
            List<Integer> list = new ArrayList<>();             //每次出队完成（即同一层的节点全部出队）产生一个新的ArrayList来存放下一层的节点
            while(count > 0){                                     //同一层节点出队并将下一层的节点加入link队列
                TreeNode temp = link.poll();
                list.add(temp.val);                             //出队并将值加入这一层的ArrayList中
                if(temp.left != null) link.add(temp.left);
                if(temp.right != null) link.add(temp.right);    //若下一层还有节点则入队
                count--;
            }
            res.add(list);                                      //同一层全部出队后存入结果集res
        }
        return res;
    }

    //中序遍历二叉树（递归）--前序后序同理
    public List<Integer> inorder(TreeNode root){
        if(root == null) return out;
        inorder(root.left);
        out.add(root.val);
        inorder(root.right);
        return out;
    }

    //求二叉树的最大深度（递归）
    // 法一：自底向上（可以看做后序遍历）
    public int max_depth(TreeNode root){

        if (root == null) return 0;
        int left = max_depth(root.left);
        int right = max_depth(root.right);
        return Math.max(left, right) + 1;
    }
    //法二：自顶向下（可以看做前序遍历）
    public void max_depth(TreeNode root, int current_depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            depth = Math.max(depth, current_depth);
        }
        max_depth(root.left, current_depth + 1);
        max_depth(root.right, current_depth + 1);
    }
}
