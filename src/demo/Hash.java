package demo;

import java.util.*;

import demo.SingleLinkedList1.Node;


public class Hash {
	
	public char firstUniqChar(String s)
	{
		/*
		 * ��ָ Offer 50. ��һ��ֻ����һ�ε��ַ�
		 * ���ַ��� s ���ҳ���һ��ֻ����һ�ε��ַ������û�У�����һ�����ո� s ֻ����Сд��ĸ��
		 * 
		 * ˼·��
		 * ����1��ʹ�ù�ϣ��洢Ƶ��
		 * 
		 * ����2��ʹ�ù�ϣ��洢����
		 * 
		 * 
		 * */
		
		//����1��ʹ�ù�ϣ��洢Ƶ��
		/*
		Map<Character, Integer> fre = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			fre.put(ch, fre.getOrDefault(ch, 0)+1);		//getOrDefault() ������ȡָ�� key ��Ӧ�� value������Ҳ��� key ���򷵻����õ�Ĭ��ֵ��
		}
		for(int i = 0; i < s.length(); i++)
		{
			if(fre.get(s.charAt(i)) == 1)
				return s.charAt(i);
		}
		return ' ' ;
		*/
		
		//����2��ʹ�ù�ϣ��洢����
		Map<Character, Integer> pos = new HashMap<>();
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(pos.containsKey(ch))
				pos.put(ch, -1);
			else
				pos.put(ch,i);
		}
		int first = s.length();
		for(Map.Entry<Character, Integer> entry:pos.entrySet())
		{
			int p = entry.getValue();
			if(p != -1 && p < first)		//�ҳ�����ֵ��С��
				first = p;
		}
		return first==s.length() ? ' ' : s.charAt(first);
		
	}

	//------------------------------------------------------------------------------------
	
	public boolean isStraight(int[] nums)
	{
		/*
		 * ��ָ Offer 61. �˿����е�˳��
		 * ���˿����������5���ƣ��ж��ǲ���һ��˳�ӣ�����5�����ǲ��������ġ�2��10Ϊ���ֱ���AΪ1��JΪ11��QΪ12��KΪ13��
		 * ����С��Ϊ 0 �����Կ����������֡�A ������Ϊ 14��
		 * 
		 * ˼·��
		 * ����1������+����
		 * 5�������������-��С<5����û���ظ����������ڴ�С������ʱ�κ��������Ȳ�ͳ��
		 * 
		 * ����2������+����
		 * */
		
		//����1������+����
		if(nums == null || nums.length < 5)
			return false;
		int max = 0;
		int min = 14;
		Set<Integer> repeat = new HashSet<>();
		for(int num:nums)
		{
			if(num == 0)
				continue;
			if(repeat.contains(num))
				return false;
			max = Math.max(max, num);
			min = Math.min(min, num);
			repeat.add(num);
		}
		if(max-min < 5)
			return true;
		return false;
	}
	

	//--------------------------------------------------------------------------------------
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
		*/
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
	}
	
	
	//----------------------------------------------------------------------------------
	public int lengthOfLongestSubstring(String s)
	{
		/*
		 * ��ָ Offer 48. ������ظ��ַ������ַ���
		 * ����ַ������ҳ�һ����Ĳ������ظ��ַ������ַ��������������ַ����ĳ��ȡ�
		 * 
		 * ����1����̬�滮+��ϣ��
		 * ״̬���壺dp(j)��Ϊ��s[j]��β������ظ��ַ����ĳ���
		 * ״̬ת�ƣ��̶��ұ߽�j���ַ�s[j]����������ͬ�ַ�Ϊs[i]��s[i]=s[j]
		 * 1����i<0����s[j]���û��������ͬ���ַ�
		 * 2����dp(j-1)<j-i��˵��s[i]�����ַ���dp(j-1)������֮�⣬dp(j)=dp(j-1)+1
		 * 3����dp(j-1)>=j-i��˵��s[i]�����ַ���dp(j-1)������֮�У�dp(j)=j-i
		 * ����ֵ��max(dp)
		 * 
		 * �ù�ϣ��ͳ�Ƹ��ַ����һ�γ��ֵ�λ�á�
		 * 
		 * ���裺
		 * 
		 * ����2����̬�滮+���Ա���
		 * ��j-1λ�õ������s[j]����������ͬ�ַ�
		 * 
		 * ����3��˫ָ��+��ϣ��
		 * */
		/*
		if(s == null || s.length() <= 0)
			return 0;
		Map<Character, Integer> map = new HashMap<>();
		int dp = 0, res = 0;
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			int i = map.getOrDefault(ch, -1);	//��ȡ����
			map.put(ch, j);
			if(dp < j - i)
			{
				dp = dp + 1;
			}
			else
			{
				dp = j - i;
			}
			res = Math.max(dp, res);
		}
		return res;
		*/
		//����2����̬�滮+���Ա���
		/*
		if(s == null || s.length() <= 0)
			return 0;
		int dp = 0, res = 0;
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			int i = j - 1;
			while(i >= 0 && s.charAt(i) != ch)
			{
				i--;
			}
			if(dp < j - i)
			{
				dp = dp + 1;
			}
			else
			{
				dp = j - i;
			}
			res = Math.max(dp, res);
		}
		return res;
		*/
		//����3��˫ָ��+��ϣ��
		if(s == null || s.length() <= 0)
			return 0;
		int i = -1, res = 0;
		Map<Character, Integer> map = new HashMap<>();
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			if(map.containsKey(ch))
			{
				i = Math.max(i, map.get(ch));
			}
			map.put(ch, j);
			res = Math.max(res, j-i);
		}
		return res;
	}
	
	
	//----------------------------------------------------------------------------------
	
}

