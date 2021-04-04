package demo;

public class Detail 
{
	
	StringBuilder res; //����һ���ַ�����
	int n;	//����λ��
	int start;	//��ʼ�̶�λ
	int numberOf9 = 0;	//��¼����9�ĸ���
	char[] num;	//�ַ����������
	char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};	//ѭ�����ַ�����
	public String printNumbers(int n)
	{
		 /*
		  * ��ָ Offer 17. ��ӡ��1������nλ��
		  * �������� n����˳���ӡ���� 1 ������ n λʮ���������������� 3�����ӡ�� 1��2��3 һֱ������ 3 λ�� 999��
		  * 
		  * ˼·���ݹ�
		  * n�����Ĵ�ӡ��end: end = 10^3 - 1
		  * ����Խ�����⣺
		  * 1����n�ܴ�ʱ��int��long���ܾ��������Ҫ���ַ���String���ͣ�һ���ַ���ʾһλ����
		  * 2���ַ����Ľ�λЧ�ʺܵͣ����ɵ��б�ʵ������nλ��0~9��ȫ���У������õݹ�����
		  * 
		  * 
		  * */
		this.n = n;
		res = new StringBuilder();
		num = new char[n];
		start = n - 1;
		fullPermu(0);
		res.deleteCharAt(res.length()-1);	//ɾ�����һ������,�ַ������ĳ�����Ҫ��length���()
		return res.toString();	//ת��λ�ַ������
	}
		
	private void fullPermu(int m)
	{
		if(m == n)
		{
			String s = String.valueOf(num).substring(start);
			if(!s.equals("0"))
				res.append(s + "\n");
			if(n-start == numberOf9)
				start--;
			return;		//�˳��÷���
		}
		
		for(char numChar:loop)
		{
			if(numChar == '9')
				numberOf9++;
			num[m] = numChar;	//ÿһλ����0~9����
			fullPermu(m+1);		//�̶���һλ��Ȼ�����
		}
		numberOf9--;	//�ڻ���ǰ�ָ�numberOf9
	}
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Detail d = new Detail();
		String s = d.printNumbers(5);
		System.out.print(s);
		
	}
	 	 
}
