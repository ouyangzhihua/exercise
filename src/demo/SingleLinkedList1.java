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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
