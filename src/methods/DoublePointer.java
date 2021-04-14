package methods;

import demo.SingleLinkedList1.ListNode;

public class DoublePointer {
	/*
	 * ˫ָ�룺ͨ��Ϊ�˼��ٱ����������ھ��������ʹ������ָ�룬�����ָ�룬ǰ��ָ�룬��βָ���
	 * 
	 * */
	
	public int[] exchange(int[] nums)
	{
		/*
		 * ��ָ Offer 21. ��������˳��ʹ����λ��ż��ǰ��
		 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ����������λ�������ǰ�벿�֣�����ż��λ������ĺ�벿�֡�
		 */
		//����1������ָ��
		/*
		if(nums == null || nums.length == 0)
			return new int[0];
		int slow = 0;
		for(int fast = 0; fast < nums.length; fast++)
		{
			if((nums[fast]&1) == 1)
			{
				swap(nums, fast, slow);
				slow++;
			}
		}
		return nums;
		*/
		
		//����2����βָ��
		/*
		if(nums == null || nums.length == 0)
			return new int[0];
		int first = 0;
		int last = nums.length - 1;
		while(first < last)
		{
			if((nums[first]&1) == 1)
			{
				first++;
				continue;
			}	
			if((nums[last]&1) == 0)
			{
				last--;
				continue;
			}
			swap(nums, first, last);	
		}
		return nums;
		*/
		
		//����3��ʹ��һ��������洢
		/*
		int[] res = new int[nums.length];
		int left = 0;
		int right = nums.length-1;
		for(int i = 0; i < nums.length; i++)
		{
			if((nums[i]&1) == 0)
			{
				res[right] = nums[i];
				right--;
			}
			else
			{
				res[left] = nums[i];
				left++;
			}	
		}
		return res;
		*/
		
		//����4�������չ��
		int first = 0;
		int last = nums.length-1;
		while(first < last)
		{
			if(!isEven(nums[first]))
			{
				first++;
				continue;
			}
			if(isEven(nums[last]))
			{
				last--;
				continue;
			}
			int temp = nums[first];
			nums[first] = nums[last];
			nums[last] = temp;
		}
		return nums;
	}
	private boolean isEven(int number)
	{
		return (number&1)==0;
	}
	private void swap(int[] nums, int i, int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;		
	}
	
	
	//------------------------------------------------------------------------------------
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x;}
	} 
	public ListNode getKthFromEnd(ListNode head, int k)
	{
		/*
		 * ��ָ Offer 22. �����е�����k���ڵ�
		 * ����һ����������������е�����k���ڵ㡣Ϊ�˷��ϴ�����˵�ϰ�ߣ������1��ʼ�������������β�ڵ��ǵ�����1���ڵ㡣
		 * ���磬һ�������� 6 ���ڵ㣬��ͷ�ڵ㿪ʼ�����ǵ�ֵ������ 1��2��3��4��5��6��
		 * �������ĵ����� 3 ���ڵ���ֵΪ 4 �Ľڵ㡣
		 */
		//����1��ǰ��ָ��
		if(head == null || k < 1)
			return null;
		ListNode former = head;
		ListNode latter = head;
		for( ; k > 0; k--)
		{
			if(former == null)
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
	
	
	//------------------------------------------------------------------------------------
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
	
	
	//-------------------------------------------------------------------------------------
	public String reverseWords(String s)
	{
		/*
		 * ��ָ Offer 58 - I. ��ת����˳��
		 * ����һ��Ӣ�ľ��ӣ���ת�����е��ʵ�˳�򣬵��������ַ���˳�򲻱䡣Ϊ������������ź���ͨ��ĸһ������
		 * ���������ַ���"I am a student. "�������"student. a am I"��
		 * 
		 * ˼·��
		 * ����1��ǰ��ָ��
		 */
		s = s.trim();
		StringBuilder res = new StringBuilder();
		int former = s.length()-1;
		int latter = former;
		while(former >= 0)
		{
			while(former >= 0 && s.charAt(former) != ' ')
				former--;
			res.append(s.substring(former+1, latter+1) + ' ');	//latter+1��ԭ���Ӵ���ʼ��ָ��beginIndex�����쵽�ַ�����endIndex - 1
			while(former >=0 && s.charAt(former) == ' ')
			{
				former--;
			}
			latter = former;
		}
		return res.toString().trim();	//ת��Ϊ�ַ�����ɾ��β���ո񣬲�����
	}
	
	
	
}
