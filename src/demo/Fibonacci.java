package demo;

public class Fibonacci {

	public int fib(int n)
	{
		/*
		 * 剑指 Offer 10- I. 斐波那契数列
		 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
		 * F(0) = 0,   F(1) = 1
		 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
		 * 
		 * 思路：
		 * 方法1：循环法
		 * 复杂度分析：空间复杂度O(1)，时间复杂度O(n)
		 * */
		
		//方法1：动态规划
		int fi;
		int fib1 = 1;
		int fib0 = 0;
		for(int i = 0; i < n; i++)
		{
			fi = (fib1 + fib0) % 1000000007;
			fib0 = fib1;
			fib1 = fi;
		}
		return fib0;

	}
	
	 public int numWays(int n) 
	 {
		 /*
		  * 剑指 Offer 10- II. 青蛙跳台阶问题
		  * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
		  * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
		  * 
		  * 思路：
		  * 方法1：斐波拉契数列
		  * 思路：
		  * 到达台阶n的最后一步，只有2种方式:从n-1到n,或从n-2到n
		  * 递推，跳上n阶台阶的方式：f(n)=f(n-1)+f(n-2), n>2
		  * f(1)=1, f(2)=2
		  * 复杂度分析：空间复杂度O(1)，时间复杂度O(n)
		  * */
		 
		 int fi;
		 int f1 = 1;
		 int f2 = 2;
		 for(int i = 1; i < n; i++)
		 {
			 fi = (f1 + f2) % 1000000007;
			 f1 = f2;
			 f2 = fi;
		 }
		 return f1;
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fibonacci f = new Fibonacci();
		System.out.println(f.fib(6));
		System.out.println(f.numWays(2));
	}

}
