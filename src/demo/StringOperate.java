package demo;

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
	
	public static void main(java.lang.String[] args) {
		// TODO Auto-generated method stub

	}

}
