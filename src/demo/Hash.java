package demo;

import java.util.HashMap;
import java.util.Map;

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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

