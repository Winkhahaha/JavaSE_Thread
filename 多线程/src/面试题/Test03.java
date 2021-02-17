package 面试题;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2020/09/11/ 16:44
 * @Description
 */
public class Test03 {
    public static void main(String[] args) {

    }

    public static ListNode deleteDuplicates(ListNode pHead) {


        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode duNode = new ListNode();
        duNode.next = pHead;
        ListNode first = duNode;
        while (pHead != null && pHead.next != null) {
            if (pHead.val == pHead.next.val) {
                int val = pHead.val;
                while (pHead != null && pHead.val == val) {
                    pHead = pHead.next;
                }
                first.next = pHead;
            } else {
                first = pHead;
                pHead = pHead.next;
            }
        }
        return duNode.next;
    }
}

