package demo;

import java.util.*;

public class SingleLinkedList1 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; next = null;}
	}
	public ListNode getKthFromEnd(ListNode head, int k)
	{
		/*
		 * ��ָ Offer 22. �����е�����k���ڵ�
		 * ����һ����������������е�����k���ڵ㡣Ϊ�˷��ϴ�����˵�ϰ�ߣ������1��ʼ�������������β�ڵ��ǵ�����1���ڵ㡣
		 * ���磬һ�������� 6 ���ڵ㣬��ͷ�ڵ㿪ʼ�����ǵ�ֵ������ 1��2��3��4��5��6��
		 * �������ĵ����� 3 ���ڵ���ֵΪ 4 �Ľڵ㡣
		 * 
		 * ˼·��
		 * ����1��˫ָ��
		 * ��������ָ�룬ǰָ������k����Ȼ����һ���ƶ���ֱ��ǰָ���߹�����β�ڵ㣬��ʱ��ָ�뼴���˵�����k��
		 * ���裺
		 * ע������³����
		 * */
		if(head == null || k <= 0)	//�߽��жϣ��������벻�Ϸ����³������
			return null;
		ListNode former = head;
		ListNode latter = head;
		for( ; k > 0; k--)
		{
			if(former == null)	//��k>����ڵ�����Ӧ���ش���
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
		 * ��ָ Offer 25. �ϲ��������������
		 * ����������������������ϲ�����������ʹ�������еĽڵ���Ȼ�ǵ�������ġ�
		 * 
		 * ˼·��
		 * 
		 * */
		if(l1 == null && l2 == null)	//�ж�����Ϸ���
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
		 * ��ָ Offer 52. ��������ĵ�һ�������ڵ�
		 * �������������ҳ����ǵĵ�һ�������ڵ�
		 * 
		 * ˼·��
		 * ����1��˫ָ��
		 * ��������Ľ���֮�����нڵ㶼��һ���ģ�ֻ�н���֮ǰ�Ľڵ㲻ͬ��
		 * ������ָ��ֱ��headA��headB�����������������������β��������һ����ͷ�ڵ��������������ָ������ʱ��Ϊ�ཻ�ڵ�
		 * 
		 * ���裺
		 * ������������Ϊ��
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
	

	//------------------------------------------------------------------------------------
	/*
	class Node {
	    int val;
	    Node next;
	    Node random;

	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}
	public Node copyRandomList(Node head)
	{
		/*
		 * ��ָ Offer 35. ��������ĸ���
		 * ��ʵ�� copyRandomList ����������һ�����������ڸ��������У�ÿ���ڵ������һ�� next ָ��ָ����һ���ڵ㣬
		 * ����һ�� random ָ��ָ�������е�����ڵ���� null
		 * 
		 * ˼·��
		 * ����1����ϣ��
		 * 
		 * ����2��ƴ�� + ���
		 * 
		 * */
		//����1����ϣ��
		/*
		if(head == null)
			return null;
		Map<Node, Node> map = new HashMap<>();
		Node cur = head;
		while(cur != null)
		{
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}
		cur = head;
		while(cur != null)
		{
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head);
		
		//����2��ƴ�� + ���
		if(head == null)
			return null;
		Node cur = head;
		while(cur != null)
		{
			Node temp = new Node(cur.val);
			temp.next = cur.next;
			cur.next = temp;
			cur = temp.next;	
		}
		cur = head;
		while(cur != null)
		{
			if(cur.random != null)
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}
		cur = head.next;
		Node pre = head, res = head.next;
		while(cur.next != null)
		{
			pre.next = pre.next.next;
			cur.next = cur.next.next;
			pre = pre.next;
			cur = cur.next;
		}
		pre.next = null;
		return res;
	}*/
	
	
	//--------------------------------------------------------------------------------------
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node() {}
	    public Node(int _val) {
	        val = _val;
	    }
	    public Node(int _val,Node _left,Node _right) {
	        val = _val;
	        left = _left;
	        right = _right;
	    }
	}
	Node pre, head;
	public Node treeToDoublyList(Node root)
	{
		/*
		 * ��ָ Offer 36. ������������˫������
		 * ����һ�ö��������������ö���������ת����һ�������ѭ��˫������Ҫ���ܴ����κ��µĽڵ㣬ֻ�ܵ������нڵ�ָ���ָ��
		 * 
		 * ˼·��
		 * ����1���������+������������ݹ�
		 * 
		 * */
		if(root == null)
			return null;
		depthFirstSearch(root);
		head.left = pre;
		pre.right = head;
		return head;
	}
	private void depthFirstSearch(Node cur)
	{
		if(cur == null)
			return;
		depthFirstSearch(cur.left);
		if(pre != null)
			pre.right = cur;
		else
			head = cur;
		cur.left = pre;
		pre = cur;
		depthFirstSearch(cur.right);
	}
}
