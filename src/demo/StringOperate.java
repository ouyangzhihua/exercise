package demo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StringOperate {

	public String reverseWords(String s)
	{
		/*
		 * 剑指 Offer 58 - I. 翻转单词顺序
		 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
		 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
		 * 
		 * 思路：
		 * 方法1：双指针
		 * 
		 * 方法2：
		 * */
		
		//方法1：双指针
		s = s.trim();	//删除首尾空格
		StringBuilder res = new StringBuilder();
		int former = s.length() - 1;
		int latter = former;
		while(former >= 0)
		{
			while(former >=0 && s.charAt(former) != ' ')
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
	
	//-------------------------------------------------------------------------
	
	public String reverseLeftWords(String s, int n)
	{
		/*
		 * 剑指 Offer 58 - II. 左旋转字符串
		 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
		 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
		 * 
		 * 思路：
		 * 方法1：字符串切片
		 * 
		 * 方法2：列表遍历拼接
		 * 不能使用切片函数时则用此方法
		 * 
		 * 方法3：字符串遍历拼接
		 * 只能用String时，用此方法
		 * 
		 * */
		
		//方法1：字符串切片
		//return s.substring(n, s.length()) + s.substring(0, n);
		
		//方法2：列表遍历拼接
		/*
		StringBuilder res = new StringBuilder();
		for(int i = n; i < s.length(); i++)
			res.append(s.charAt(i));
		for(int i = 0; i < n; i++)
			res.append(s.charAt(i));
		return res.toString();
		*/
		//利用取余优化代码
		/*
		StringBuilder res = new StringBuilder();
		for(int i = n; i < n+s.length(); i++)
			res.append(s.charAt(i % s.length()));
		return res.toString();
		*/
		
		//方法3：字符串遍历拼接
		String res = "";
		for(int i = n; i < n+s.length(); i++)
			res += s.charAt(i % s.length());
		return res;
	}
	

	//------------------------------------------------------------------------------------------
	char[] c;
	List<String> res1 = new LinkedList<>();
	public String[] permutation(String s)
	{
		/*
		 * 剑指 Offer 38. 字符串的排列
		 * 输入一个字符串，打印出该字符串中字符的所有排列.你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
		 * 
		 * 思路：
		 * 方法1：回溯法、深度优先搜索、剪枝
		 * */
		c = s.toCharArray();
		depthFirstSearch(0);
		return res1.toArray(new String[res1.size()]);
	}
	private void depthFirstSearch(int x)
	{
		if(x == c.length-1)
		{
			res1.add(String.valueOf(c));
			return;
		}
		Set<Character> set = new HashSet<>();
		for(int i = x; i < c.length; i++)
		{
			if(set.contains(c[i]))
				continue;
			set.add(c[i]);
			swap(i, x);
			depthFirstSearch(x+1);
			swap(i, x);
		}
	}
	private void swap(int i, int j)
	{
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}

}
