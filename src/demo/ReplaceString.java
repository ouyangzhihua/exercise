package demo;

public class ReplaceString
{
	/*
	 * ��ָ Offer 05. �滻�ո�
	 * ʵ��һ�����������ַ��� s �е�ÿ���ո��滻��"%20"��
	 * 
	 * ˼·��
	 * ����1��ʹ��Java�ڲ�����
	 * ���裺
	 * �����Է�����
	 * 
	 * ����2���������
	 * ˼·��Java���ַ��������Ϊ���ɱ�����ͣ��޷�ֱ���޸�ĳһλ�ַ�����Ҫ�½��ַ���
	 * ���裺��ʼ��StringBuilder res
	 * �����ַ���ÿ���ַ�c�� ��cΪ�ո� ���%20����Ϊ�ո����c
	 * 
	 * �����Է�����ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
	 * 
	 * ˼·��
	 * ����3��ʹ���ַ�����
	 * ���裺
	 * �����Է�����ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
	 * */
	
	
	public String replaceSpace(String s)
	{
		/*
		//����1��ʹ��Java�ڲ�����
		String regex = " ";
		String res = s.replaceAll(regex, "%20");
		return res;
		*/
		
		/*
		//����2���������
		StringBuilder res = new StringBuilder();
		for(Character c:s.toCharArray())
		{
			if(c == ' ' )
				res.append("%20");
			else
				res.append(c);		
		}
		return res.toString();
		*/
		
		//����3��ʹ���ַ�����
		int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;

	}
	
	public static void main(String[] args)
	{
		String s = "We are happy.";
		ReplaceString str = new ReplaceString();
		String s1 = str.replaceSpace(s);
		System.out.println(s1);
	}
}
