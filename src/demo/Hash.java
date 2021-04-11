package demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

