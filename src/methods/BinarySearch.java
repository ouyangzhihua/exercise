package methods;

public class BinarySearch {
	/*
	 * 二分法查找：主要针对数据已经排好序。首先将数据分为两半，将目标值target与中间值nums[pivot]比较，pivot = low + (high-low)/2,
	 * 若比中间值小则在二分查找左边，否则查找右边。
	 * 
	 * 步骤：
	 * 1，判断输入矩阵是否空
	 * 2，初始化low=0, high=nums.length-1
	 * 3，循环：
	 * 条件：low <= high
	 * 执行：
	 * 中间值索引pivot = low + (high-low)/2
	 * 若target < nums[pivot], high = pivot - 1
	 * 若target > nums[pivot], low = pivot + 1
	 * 若target = nums[pivot],返回true
	 * 
	 * 复杂度分析：
	 * */
	
	public int minArray(int[] nums) 
	{
		/*
		 * 剑指 Offer 11. 旋转数组的最小数字
		 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
		 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
		 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
		 */
		
		if(nums == null || nums.length == 0)
			return 0;
		int low = 0;
		int high = nums.length - 1;
		while(low < high)
		{
			int pivot = low + (high - low)/2;	//防止出现溢出不用pivot = l(high + low)/2
			if(nums[pivot] < nums[high])
				high = pivot;
			else if(nums[pivot] > nums[high])
				low = pivot + 1;
			else
				high--;
		}
		return nums[low];
	}
	
	//---------------------------------------------------------------------------------------
	
	public int search(int[] nums, int target)
	{
		/*
		 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
		 * 统计一个数字在排序数组中出现的次数。
		 */
		
		if(nums == null || nums.length == 0)
			return 0;
		int low = 0;
		int high = nums.length - 1;
		while(low < high)
		{
			int pivot = low + (high - low)/2;
			if(nums[pivot] >= target)
				high = pivot;
			else
				low = pivot + 1;	
		}
		int count = 0;
		for(int i = low; i <= high; i++)
		{
			if(nums[i] == target)
				count++;
		}
		return count;
	}
	
	//---------------------------------------------------------------------------------
	
	public int missingNumber(int[] nums)
	{
		/*
		 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
		 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
		 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
		 */
		if(nums == null || nums.length == 0)
			return -1;
		int low = 0;
		int high = nums.length - 1;
		while(low < high)
		{
			int pivot = low + (high - low)/2;
			if(nums[pivot] != pivot)
				high = pivot;
			else
				low = pivot + 1;
		}
		return low;
	}
	
	//-----------------------------------------------------------------------------------
	
	public double myPow(double x, int n)
	{
		/*
		 * 二分法拓展：快速幂法
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
		 */
		if(x == 0)
			return 0;
		if(x == 1 || n == 0)
			return 1;
		long nl = n;
		double res = 1.0;
		if(nl < 0)
		{
			x = 1/x;
			nl = -nl;
		}
		while(nl > 0)
		{
			if((nl&1) == 1)
			{
				res = x * res;
			}
			x = x * x;
			nl = nl>>1;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
