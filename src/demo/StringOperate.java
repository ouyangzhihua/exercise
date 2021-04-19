package demo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StringOperate {

	public String reverseWords(String s)
	{
		/*
		 * ��ָ Offer 58 - I. ��ת����˳��
		 * ����һ��Ӣ�ľ��ӣ���ת�����е��ʵ�˳�򣬵��������ַ���˳�򲻱䡣Ϊ������������ź���ͨ��ĸһ������
		 * ���������ַ���"I am a student. "�������"student. a am I"��
		 * 
		 * ˼·��
		 * ����1��˫ָ��
		 * 
		 * ����2��
		 * */
		
		//����1��˫ָ��
		s = s.trim();	//ɾ����β�ո�
		StringBuilder res = new StringBuilder();
		int former = s.length() - 1;
		int latter = former;
		while(former >= 0)
		{
			while(former >=0 && s.charAt(former) != ' ')
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
	
	//-------------------------------------------------------------------------
	
	public String reverseLeftWords(String s, int n)
	{
		/*
		 * ��ָ Offer 58 - II. ����ת�ַ���
		 * �ַ���������ת�����ǰ��ַ���ǰ������ɸ��ַ�ת�Ƶ��ַ�����β�����붨��һ������ʵ���ַ�������ת�����Ĺ��ܡ�
		 * ���磬�����ַ���"abcdefg"������2���ú�������������ת��λ�õ��Ľ��"cdefgab"��
		 * 
		 * ˼·��
		 * ����1���ַ�����Ƭ
		 * 
		 * ����2���б����ƴ��
		 * ����ʹ����Ƭ����ʱ���ô˷���
		 * 
		 * ����3���ַ�������ƴ��
		 * ֻ����Stringʱ���ô˷���
		 * 
		 * */
		
		//����1���ַ�����Ƭ
		//return s.substring(n, s.length()) + s.substring(0, n);
		
		//����2���б����ƴ��
		/*
		StringBuilder res = new StringBuilder();
		for(int i = n; i < s.length(); i++)
			res.append(s.charAt(i));
		for(int i = 0; i < n; i++)
			res.append(s.charAt(i));
		return res.toString();
		*/
		//����ȡ���Ż�����
		/*
		StringBuilder res = new StringBuilder();
		for(int i = n; i < n+s.length(); i++)
			res.append(s.charAt(i % s.length()));
		return res.toString();
		*/
		
		//����3���ַ�������ƴ��
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
		 * ��ָ Offer 38. �ַ���������
		 * ����һ���ַ�������ӡ�����ַ������ַ�����������.�����������˳�򷵻�����ַ������飬�����治�����ظ�Ԫ�ء�
		 * 
		 * ˼·��
		 * ����1�����ݷ������������������֦
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
