package demo;

import java.util.HashMap;
import java.util.Map;

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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

