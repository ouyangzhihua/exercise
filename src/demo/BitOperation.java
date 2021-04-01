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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
