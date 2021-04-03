package demo;

public class Recursion {
	
	public double myPow(double x, int n)
	{
		/*
		 * 剑指 Offer 16. 数值的整数次方
		 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
		 * 
		 * 思路：
		 * 方法1：快速幂法
		 * 快速幂法是二分法思想的一个体现，当n为偶数：x^n = (x^2)^(n/2)
		 * 当n为奇数：x^n = x(x^2)^(n/2)
		 * 位运算：位运算的效率比乘除和求余要高很多
		 * 向下整除n/2相当于，n>>1
		 * 取余数n%2相当于，n&1
		 * 步骤：
		 * 1，输入判断：若x=0,返回0； 若x=1, 返回1
		 * 			若n=0,返回1； 若n<0, 重新处理
		 * 2，初始化：
		 * 3，循环计算：
		 * int型：4字节，1字节=8位，int：32位，1位标识正负，31位表示数值，-2^31~2^31-1，当n<0，进行n=-n赋值时可能溢出
		 * 故用long性替代
		 * 
		 * 复杂度分析：时间复杂度O(log2 n)，空间复杂度O(1)
		 * */
		double res = 1.0;
		long nl = n;
		if(x==0)	//0的0次方无意义，这里返回的是0
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
