package demo;

public class SingleLinkedList1 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; next = null;}
	}
	public ListNode getKthFromEnd(ListNode head, int k)
	{
		/*
		 * 剑指 Offer 22. 链表中倒数第k个节点
		 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
		 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。
		 * 这个链表的倒数第 3 个节点是值为 4 的节点。
		 * 
		 * 思路：
		 * 方法1：双指针
		 * 定义两个指针，前指针先走k步，然后在一起移动，直到前指针走过链表尾节点，此时后指针即到了倒数第k个
		 * 步骤：
		 * 注意代码的鲁棒性
		 * */
		if(head == null || k <= 0)	//边界判断，避免输入不合法导致程序崩溃
			return null;
		ListNode former = head;
		ListNode latter = head;
		for( ; k > 0; k--)
		{
			if(former == null)	//若k>链表节点数，应返回错误
				return null;
			former = former.next;	
		}
		while(former != null)
		{
			former = former.next;
			latter = latter.next;
		}
		return latter;
	}
	
	//-----------------------------------------------------------------------------------
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2)
	{
		/*
		 * 剑指 Offer 25. 合并两个排序的链表
		 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
		 * 
		 * 思路：
		 * 
		 * */
		if(l1 == null && l2 == null)	//判断输入合法性
			return null;
		ListNode m = new ListNode(0);
		ListNode cur = m;
		while(l1 != null && l2 != null)
		{
			if(l1.val > l2.val)
			{
				cur.next = l2;
				l2 = l2.next;
			}
			else
			{
				cur.next = l1;
				l1 = l1.next;
			}
			cur = cur.next;
		}
		if(l1 != null)
			cur.next = l1;
		else
			cur.next = l2;
		return m.next;
	}
	
	//-------------------------------------------------------------------------------
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB)
	{
		/*
		 * 剑指 Offer 52. 两个链表的第一个公共节点
		 * 输入两个链表，找出它们的第一个公共节点
		 * 
		 * 思路：
		 * 方法1：双指针
		 * 两个链表的交点之后所有节点都是一样的，只有交点之前的节点不同。
		 * 用两个指针分别从headA和headB出发遍历链表，如果到达链表尾则跳到另一链表头节点继续遍历，当两指针相遇时则为相交节点
		 * 
		 * 步骤：
		 * 特例处理：输入为空
		 * */
		if(headA == null || headB == null)
			return null;
		ListNode ha = headA;
		ListNode hb = headB;
		while(ha != hb)
		{
			if(ha != null)
				ha = ha.next;
			else
				ha = headB;
			if(hb != null)
				hb = hb.next;
			else
				hb = headA;
		}
		return ha;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
