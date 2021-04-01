package demo;

public class DynamicProgramming 
{
	
	public int cuttingRope(int n)
	{
		/*
		 * 剑指 Offer 14- I. 剪绳子
		 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
		 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
		 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * 记f(n)为可能的最大乘积，可以将其分解为多个子问题求解
		 * 将所有子问题的最优解求出，得到整体最优解。
		 * f(2)=1, f(3)=2, 且可证明2(n-2)>n, 3(n-3)>n, 因此将绳子剪到剩下2或3即可。
		 * 步骤：
		 * 复杂度分析：
		 * 
		 * 方法2：贪婪算法
		 * 当n>=5时，可证明2(n-2)>n, 3(n-3)>n，因此我们每一步只需尽量多剪长度为3或2的
		 * */
		
		//方法1：动态规划
		/*
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;
		int[] products = new int[n+1];	//
		products[0] = 0;
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		int max = 0;
		for(int i = 4; i <= n; i++)
		{
			max = 0;
			for(int j = 1; j <= i/2; j++)
			{
				int product = products[j] * products[i-j];
				if(product > max)
					max = product;	
			}
			products[i] = max;	//将长度为i时的最大值存到products[i]
		}
		return max;
		*/
		
		//方法2：贪婪算法
		/*
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;	
		else if(n == 4)	//边界值判断
			return 4;
		long max;
		long times = n/3;
		if(n%3 == 1)
			max = (int) (Math.pow(3, times-1) * 4);
		else if(n%3 == 0)
			max = (int) (Math.pow(3, times));
		else
			max = (int) (Math.pow(3, times) * (n - 3 * times));
		return (int) max;
		*/
		
		
		//考虑大数求余问题，快速求余
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;	
		else if(n == 4)	//边界值判断
			return 4;
		int max;
		long times = n/3;
		long x = 3;
		long rem =1;
		int p = 1000000007;
		for(long a = times-1; a > 0; a/=2)
		{
			if(a%2 == 1)
				rem = (rem * x) % p;
			x = (x * x) % p;
		}
		if(n%3 == 1)
			max = (int) (rem * 4 % p);
		else if(n%3 == 0)
			max = (int) (rem * 3 % p);
		else
			max = (int) (rem * 3 * 2 % p);
		return max;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DynamicProgramming  dp = new DynamicProgramming ();
		
		System.out.println(dp.cuttingRope(120));
	
	}

	 
}
