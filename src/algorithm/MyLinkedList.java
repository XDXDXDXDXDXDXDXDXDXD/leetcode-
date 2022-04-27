package algorithm;

public class MyLinkedList {
    class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
    }
    //环形链表  给定一个链表，判断链表中是否有环
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow = head;       //慢指针
        ListNode fast = head.next;  //快指针
        while(slow!=fast){
            if(fast==null||fast.next==null){
                return false;
            }
            else{
                slow = slow.next;       //向后一步
                fast = fast.next.next;  //向后两步
            }
        }
        return true;       //如果快慢指针相遇说明有环
    }

    //环形链表② 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
    public ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(true){
            if(fast==null||fast.next==null) return null;    //无环直接返回
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) break;       //循环结束找到快慢指针相遇的节点
        }
        fast = head;        //第二次循环找入口
        while(fast!=slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;        //两指针相遇节点即为入口
        /*tip
        * 第一次相遇时有f = 2s,f = s+nb（fast比slow多走n圈）,得出f = 2nb，s = nb
        * 第二次新的指针从头走环外的a步，slow同时走，两指针相遇节点即为入口节点（走a步后slow = a+nb）
        * */
    }

    //相交链表 找到两个链表的相交节点，如果没有相交则返回null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if(headA==null||headB==null) return null;
        ListNode pa = headA,pb = headB;
        while(pa!=pb){
            pa = pa==null?headB:pa.next;
            pb = pb==null?headA:pb.next;
        }
        return pa;
        /*如+A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。 由于 B.length (=4) < A.length (=6)，pB比pA 少经过2个结点，会先到达尾部。
        将pB重定向到 A 的头结点，pA 重定向到 B 的头结点后，pB 要比 pA 多走 2 个结点。因此，它们会同时到达交点。*/
    }

    //删除链表的倒数第n个节点 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);       //定义哑结点便于处理特殊情况（如只有一个节点）以及返回头结点
        dummy.next = head;
        ListNode pa = dummy;
        ListNode pb = dummy;
        while(n-->0){
            pa = pa.next;       //第一个指针先走n+1步
        }
        while(pa.next!=null){
            pa = pa.next;
            pb = pb.next;
        }
        pb.next = pb.next.next;
        return dummy.next;
    }

    //反转链表
    public ListNode reverseList(ListNode head){
        ListNode pre = null;    //当前节点的前一个节点
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;   //暂存当前节点的下一个节点
            cur.next = pre;     //反转当前节点和前一个节点
            pre = cur;      //进行下一节点操作
            cur = next;
        }
        return pre;
    }

    //移除链表元素 删除链表中等于给定值 val 的所有节点
    public ListNode removeElements(ListNode head, int val){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur!=null){
            if(cur.val==val){
                pre.next = cur.next;
            }
            else pre = cur;     //值为val的节点删除后pre不变
            cur = cur.next;
        }
        return dummy.next;
    }

    //奇偶链表 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    //请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数
    public ListNode oddEvenList(ListNode head){
        if(head == null) return null;
        ListNode odd = head,even = head.next,evenHead = even;      //head、odd奇数链表头尾指针，evenHead、even偶数链表头尾指针
        while(even!=null&&even.next!=null){
            odd.next = even.next;
            odd = odd.next;         //奇数链表
            even.next = odd.next;
            even = even.next;       //偶数链表
        }
        odd.next = evenHead;        //偶数链表连接到奇数链表后
        return head;
    }

    //回文链表 判断一个链表是否为回文链表
    public boolean isPalindrome(ListNode head){
        if(head==null) return false;
        return true;
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode l3 = new ListNode(0),p1 = l1,p2 = l2;
        while(p1!=null||p1.next!=null||p2!=null||p2.next!=null){
            if(p1!=null&&p1.next!=null&&l2.val>=l1.val){
                l3 = p1;
                l3 = l3.next;
                p1 = p1.next;
            }
            else if(p2!=null&&p2.next!=null&&l2.val<l1.val){
                l3 = p2;
                l3 = l3.next;
                p2 = p2.next;
            }
        }
        return l3;
    }
}
