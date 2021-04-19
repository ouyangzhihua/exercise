package demo;

import java.util.*;

import demo.SingleLinkedList1.Node;


public class Hash {
	
	public char firstUniqChar(String s)
	{
		/*
		 * 剑指 Offer 50. 第一个只出现一次的字符
		 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
		 * 
		 * 思路：
		 * 方法1：使用哈希表存储频数
		 * 
		 * 方法2：使用哈希表存储索引
		 * 
		 * 
		 * */
		
		//方法1：使用哈希表存储频数
		/*
		Map<Character, Integer> fre = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			fre.put(ch, fre.getOrDefault(ch, 0)+1);		//getOrDefault() 方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
		}
		for(int i = 0; i < s.length(); i++)
		{
			if(fre.get(s.charAt(i)) == 1)
				return s.charAt(i);
		}
		return ' ' ;
		*/
		
		//方法2：使用哈希表存储索引
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
			if(p != -1 && p < first)		//找出索引值最小的
				first = p;
		}
		return first==s.length() ? ' ' : s.charAt(first);
		
	}

	//------------------------------------------------------------------------------------
	
	public boolean isStraight(int[] nums)
	{
		/*
		 * 剑指 Offer 61. 扑克牌中的顺子
		 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，
		 * 而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
		 * 
		 * 思路：
		 * 方法1：集合+遍历
		 * 5张牌连续则最大-最小<5，且没有重复的数。由于大小王可以时任何数，可先不统计
		 * 
		 * 方法2：排序+遍历
		 * */
		
		//方法1：集合+遍历
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
		 * 剑指 Offer 35. 复杂链表的复制
		 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
		 * 还有一个 random 指针指向链表中的任意节点或者 null
		 * 
		 * 思路：
		 * 方法1：哈希表
		 * 
		 * 方法2：拼接 + 拆分
		 * 
		 * */
		//方法1：哈希表
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
		//方法2：拼接 + 拆分
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
		 * 剑指 Offer 48. 最长不含重复字符的子字符串
		 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
		 * 
		 * */
		if(s == null || s.length() <= 0)
			return 0;
		Map<Character, Integer> map = new HashMap<>();
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(map.containsKey(ch))
				return i;
			map.put(ch, i+1);
		}
		return 0;
	}
}

