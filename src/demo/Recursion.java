package demo;

public class Recursion {
	
	public double myPow(double x, int n)
	{
		/*
		 * ��ָ Offer 16. ��ֵ�������η�
		 * ʵ�� pow(x, n) �������� x �� n ���ݺ���������x^n��������ʹ�ÿ⺯����ͬʱ����Ҫ���Ǵ������⡣
		 * 
		 * ˼·��
		 * ����1�������ݷ�
		 * �����ݷ��Ƕ��ַ�˼���һ�����֣���nΪż����x^n = (x^2)^(n/2)
		 * ��nΪ������x^n = x(x^2)^(n/2)
		 * λ���㣺λ�����Ч�ʱȳ˳�������Ҫ�ߺܶ�
		 * ��������n/2�൱�ڣ�n>>1
		 * ȡ����n%2�൱�ڣ�n&1
		 * ���裺
		 * 1�������жϣ���x=0,����0�� ��x=1, ����1
		 * 			��n=0,����1�� ��n<0, ���´���
		 * 2����ʼ����
		 * 3��ѭ�����㣺
		 * int�ͣ�4�ֽڣ�1�ֽ�=8λ��int��32λ��1λ��ʶ������31λ��ʾ��ֵ��-2^31~2^31-1����n<0������n=-n��ֵʱ�������
		 * ����long�����
		 * 
		 * ���Ӷȷ�����ʱ�临�Ӷ�O(log2 n)���ռ临�Ӷ�O(1)
		 * */
		double res = 1.0;
		long nl = n;
		if(x==0)	//0��0�η������壬���ﷵ�ص���0
			return 0;
		if(nl < 0)
		{
			x = 1/x;
			nl = -nl;
		}
		while(nl > 0)
		{
			if((nl&1) == 1)
				res = x * res;
			x *= x;
			nl >>= 1;	
		}
		return res;
	}
}