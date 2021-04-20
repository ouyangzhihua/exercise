package methods;

import java.util.*;


public class DoublePointer {
	/*
	 * 双指针：通常为了减少遍历，可以在具体情况中使用两个指针，如快慢指针，前后指针，首尾指针等
	 * 
	 * */
	
	public int[] exchange(int[] nums)
	{
		/*
		 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
		 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
		 */
		//方法1：快慢指针
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
		
		//方法2：首尾指针
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
		
		//方法3：使用一个新数组存储
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
		
		//方法4：提高扩展性
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
		 * 剑指 Offer 22. 链表中倒数第k个节点
		 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
		 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。
		 * 这个链表的倒数第 3 个节点是值为 4 的节点。
		 */
		//方法1：前后指针
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
	
	
	//-------------------------------------------------------------------------------------
	public String reverseWords(String s)
	{
		/*
		 * 剑指 Offer 58 - I. 翻转单词顺序
		 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
		 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
		 * 
		 * 思路：
		 * 方法1：前后指针
		 */
		s = s.trim();
		StringBuilder res = new StringBuilder();
		int former = s.length()-1;
		int latter = former;
		while(former >= 0)
		{
			while(former >= 0 && s.charAt(former) != ' ')
				former--;
			res.append(s.substring(former+1, latter+1) + ' ');	//latter+1的原因：子串开始于指定beginIndex并延伸到字符索引endIndex - 1
			while(former >=0 && s.charAt(former) == ' ')
			{
				former--;
			}
			latter = former;
		}
		return res.toString().trim();	//转化为字符串，删除尾部空格，并返回
	}
	
	
	//----------------------------------------------------------------------------------
	public int lengthOfLongestSubstring(String s)
	{
		/*
		 * 剑指 Offer 48. 最长不含重复字符的子字符串
		 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
		 * 
		 * 方法1：动态规划+哈希表
		 * 状态定义：dp(j)记为以s[j]结尾的最长不重复字符串的长度
		 * 状态转移：固定右边界j，字符s[j]左边最近的相同字符为s[i]，s[i]=s[j]
		 * 1，当i<0，即s[j]左边没有与其相同的字符
		 * 2，当dp(j-1)<j-i，说明s[i]在子字符串dp(j-1)的区间之外，dp(j)=dp(j-1)+1
		 * 3，当dp(j-1)>=j-i，说明s[i]在子字符串dp(j-1)的区间之中，dp(j)=j-i
		 * 返回值：max(dp)
		 * 
		 * 用哈希表统计个字符最后一次出现的位置。
		 * 
		 * 步骤：
		 * 
		 * 方法2：动态规划+线性遍历
		 * 从j-1位置倒序查找s[j]左边最近的相同字符
		 * 
		 * 方法3：双指针+哈希表
		 * */
		/*
		if(s == null || s.length() <= 0)
			return 0;
		Map<Character, Integer> map = new HashMap<>();
		int dp = 0, res = 0;
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			int i = map.getOrDefault(ch, -1);	//获取索引
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
		//方法2：动态规划+线性遍历
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
		//方法3：双指针+哈希表
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
	
	
	//---------------------------------------------------------------------------------------

	
}
