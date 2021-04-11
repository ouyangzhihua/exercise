package demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

