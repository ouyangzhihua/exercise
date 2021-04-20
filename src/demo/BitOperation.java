package demo;

public class BitOperation 
{

	public int hammingWeight(int n)
	{
		/*
		 * 剑指 Offer 15. 二进制中1的个数
		 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
		 * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
		 * 
		 * 思路：
		 * 方法1：与运算
		 * 将输入的数从右向左，每一位与01进行与运算
		 * 步骤：
		 * 复杂度分析：
		 * 
		 * 方法2：利用n&(n-1)
		 * n&(n-1) 可以将二进制数字n最右边的 1 变成 0 ，其余不变
		 * 步骤：
		 * 复杂度分析：
		 * */
		
		//方法1：与运算
		/*
		int count = 0;
		for(int temp = n; temp != 0; temp >>>= 1)	//>>>:无符号右移，>>：有符号
		{
			count += temp & 1;
		}
		return count;
		*/
		
		//方法2：利用n&(n-1)
		int count = 0;
		for(int temp = n; temp != 0; temp &= temp-1)	//>>>:无符号右移，>>：有符号
		{
			count++;
		}
		return count;	
	}
	
	//-------------------------------------------------------------------------------
	
	public int add(int a, int b)
	{
		/*
		 * 剑指 Offer 65. 不用加减乘除做加法
		 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
		 * 
		 * 思路：
		 * 方法1：位运算
		 * 无进位和n = a^b
		 * 进位 c = a&b
		 * */
		while(b != 0)
		{
			int c = (a&b) <<1;
			a = a^b;
			b = c;
		}
		return a;
	}
	
	
	//-----------------------------------------------------------------------------------------
	public int[] singleNumbers(int[] nums)
	{
		/*
		 * 剑指 Offer 56 - I. 数组中数字出现的次数
		 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
		 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
		 * 
		 * 思路：
		 * 方法1：分组异或
		 * */
		if(nums == null || nums.length == 0)
			return new int[0];
		int ret = 0;
		for(int num:nums)
		{
			ret ^= num;
		}
		int div = 1;
		while((div&ret) == 0)
		{
			div <<= 1;
		}
		int a = 0, b = 0;
		for(int num:nums)
		{
			if((div&num) != 0)
			{
				a ^= num;
			}
			else
			{
				b ^= num;
			}
		}
		return new int[] {a,b};
	}
	
	
	//--------------------------------------------------------------------------------
	public int singleNumber(int[] nums)
	{
		/*
		 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
		 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
		 * 
		 * 思路：
		 * 方法1：有限状态自动机
		 * */
		int ones = 0, twos = 0;
		for(int num:nums)
		{
			ones = ones ^ num & ~twos;
			twos = twos ^ num & ~ones;
		}
		return ones;
	}

}
