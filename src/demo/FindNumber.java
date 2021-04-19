/* 
任务：找到数组中的某个数
思路：
步骤：
*/
package demo;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class FindNumber 
{
	
	/*需求：
	 * 剑指offer03：找出数组中重复的数字。
	 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
	 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
	 * 
	 * 思路：
	 * 方法1：暴力遍历
	 * 只要找到最新重复2次的数
	 * 步骤：遍历数组，每次都将数与前面的数比较是否相等
	 * 复杂度分析：空间复杂度O(n)，时间复杂度O(n^2)
	 * ----------------------------
	 * 
	 * 方法2：哈希集合
	 * 哈希法（散列）：通过特定数学函数或其他方法，将本身的键值转换成对应的数据存储地址
	 * 思路：遍历数组，将数据存入哈希集合，若不能存入则说明集合中已经存入该数，即找到重复的数
	 * 空间复杂度O(n)，时间复杂度O(n)
	 * -----------------------------
	 * 
	 * 方法3：原地置换
	 * 思路：数组长度为n,值在0～n-1 的范围内，若没有重复的数，则每个数都和自己的索引值相等。
	 * 将数组排序之后，值与索引值不等的就是重复数
	 * 步骤：遍历数组，若值nums[i]与索引值i不等，就当前值nums[i]置换到索引值为nums[i]的位置
	 * 复杂度分析：空间复杂度O(1)，时间复杂度O(n)
	 * 
	 * */

	//找出数组中重复的数字
	public int findRepeatNumber(int[] nums)
	{
		/*
		 * 方法1：暴力遍历
		 * 
		int repeat = -1;
		OUT:
		while(nums !=null)
		{
			for(int i = 0; i < nums.length; i++)
			{
				for(int j = 0; j < i; j++)
				{
					if(nums[i] == nums[j])
					{
						repeat = nums[i];
						break OUT;
					}
				}
			}
			break;
		}
		return repeat;
		*/
		
		/*
		//方法2：哈希集合
		int repeat = -1;
		Set<Integer> set = new HashSet<Integer>();
		for(int num:nums)
		{
			if(!set.add(num))
			{
				repeat = num;
				break;
			}	
		}
		return repeat;
		*/
		
		//方法3：原地置换
		int repeat = -1;
		int temp;
		for(int i = 0; i < nums.length; i++)
		{
			while(nums[i] != i)
			{
				if(nums[i] == nums[nums[i]])
				{
					repeat = nums[i];
					break;
				}
				else
				{
					temp = nums[i];
					nums[i] = nums[temp];
					nums[temp] = temp;
				}
			}
				
		}
		return repeat;
	}
	
	/*
	 * 剑指 Offer 04. 二维数组中的查找
	 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 * 
	 * 思路：
	 * 方法1：二叉搜索树，
	 * 步骤：
	 * 复杂度分析：时间复杂度O(n+m)，空间复杂度O(1)
	 * 
	 * 方法2：暴力遍历：
	 * 步骤：
	 * 复杂度分析：时间复杂度O(nm)，空间复杂度O(1)
	 * */
	public boolean FindNumberIn2DArray(int[][] matrix, int target)
	{
		/*
		//方法1：二叉搜索树
		//注意首先要判断输入的合法性
		if(matrix == null || matrix.length == 0 || matrix[0].length==0)
            return false;
		int row = 0;
		int col = matrix[0].length - 1;
		while(row < matrix.length && col >= 0)
		{
			if(target == matrix[row][col])
				return true;
			else if(target > matrix[row][col])
				row++;
			else
				col--;
		}
		return false;
		*/
		
		//方法2：暴力遍历
		if(matrix == null || matrix.length == 0 || matrix[0].length==0)
            return false;
		for(int row = 0; row < matrix.length; row++)
			for(int col = 0; col < matrix[0].length; col++)
				if(matrix[row][col] == target)
					return true;
		return false;
	}
	
	public int minArray(int[] numbers)
	{
		/*
		 * 剑指 Offer 11. 旋转数组的最小数字
		 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
		 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
		 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
		 * 
		 * 思路：
		 * 方法1：二分法
		 * 输入的数组在未旋转之前是一个递增的数组，因此数组最大值与最小值的数值是唯一的，数量可能多个。数组开头总是大于等于结尾的值
		 * 旋转之后，将中位值numbers[pivot]与数组最后一个值numbers[high]比较，若numbers[pivot]<numbers[high],说明
		 * 最小值在pivot的左边，可将右侧区间去掉;若numbers[pivot]>numbers[high],说明最小值在pivot的右边，
		 * 可将左侧区间去掉，若numbers[pivot]=numbers[high]，numbers[high]的值无论是不是最小，可以用numbers[pivot]代表，
		 * 故可将最右边的值numbers[high]去掉。
		 * 复杂度分析：时间复杂度O(log n)，空间复杂度O(1)
		 * 
		 * 
		 * */
		
		//方法1：二分法
		int low = 0;
		int high = numbers.length - 1;
		while(low < high)
		{
			int pivot = (low + high)/2;
			if(numbers[pivot] < numbers[high])
				high = pivot;
			else if(numbers[pivot] > numbers[high])
				low = pivot + 1;
			else
				high--;
		}
		return numbers[low];
	}
	
	//-------------------------------------------------------------------------------
	
	public int[] getLeastNumbers(int[] arr, int k)
	{
		/*
		 * 剑指 Offer 40. 最小的k个数
		 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
		 * 
		 * 思路：
		 * 方法1：堆
		 * 利用堆后进先出的特性，依次先将前k个小的数压到堆栈中，先将第一个数压入，每压入一个就与前一个压入堆栈中数比较，若小则弹出前一个再压入
		 * 否则就直接压入，直至k个。后续在遍历数组剩余的数，维护堆栈即可
		 * 特例处理：arr=null，
		 * k<=0
		 * k>arr.length
		 * 
		 * 方法2：快排思想
		 * 
		 * */
		
		//方法1：堆
		/*
		int[] res = new int[k];
		if(arr == null || k <= 0)
			return res;
		if(k >= arr.length)
			return arr;
		
		PriorityQueue<Integer> que = new PriorityQueue<Integer>(new Comparator<Integer>()
		{
				public int compare(Integer num1, Integer num2)
				{
					return num2-num1;
				}
		});	//优先队列
		for(int i = 0; i < k; i++)
		{
			que.offer(arr[i]);
		}
		for(int i = k; i < arr.length; i++)
		{
			if(que.peek() > arr[i])
			{
				que.poll();
				que.offer(arr[i]);
			}
		}
		for(int i = 0; i < k; i++)
		{
			res[i] = que.poll();
		}
		return res;
		*/
		
		//方法2：快排思想
		int[] res = new int[k];
		if(arr == null || k <= 0)
			return res;
		if(k >= arr.length)
			return arr;
		randomizedSlect(arr, 0, arr.length-1, k);
		for(int i = 0; i < k; i++)
		{
			res[i] = arr[i];
		}
		return res;	
	}
	private void randomizedSlect(int[] arr, int left, int right, int k)
	{
		if(left >= right)
			return;		//结束
		int pos = randomizedPartition(arr, left, right);
		int num = pos -left + 1;
		if(k == num)
			return;
		else if(k < num)
			randomizedSlect(arr, left, pos-1, k);
		else
			randomizedSlect(arr, pos+1, right, k-num);
	}
	private int randomizedPartition(int[] arr, int left, int right)
	{
		int i = new Random().nextInt(right - left + 1) + left;
		swap(arr, i, right);
		return partition(arr, left, right);
	}
	private int partition(int[] arr, int left, int right)
	{
		int pivot = arr[right];
		int i = left - 1;
		for(int j = left; j < right; j++)
		{
			if(arr[j] <= pivot)
			{
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, right);
		return i+1;
	}
	private void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	//-----------------------------------------------------------------------------------
	
	public int search(int[] nums, int target)
	{
		/*
		 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
		 * 统计一个数字在排序数组中出现的次数。
		 * 
		 * 思路：
		 * 方法1：二分法
		 * 
		 * 步骤：
		 * 特例处理：nums=null, nums.length == 0
		 * */
		if(nums == null || nums.length == 0)
			return 0;
		int low  = 0;
		int high = nums.length - 1;
		int count = 0;
		while(low < high)
		{
			int pivot = (low + high)/2;
			if(target <= nums[pivot])
				high = pivot;
			else
				low = pivot + 1;
		}
		for(int i = low; i <nums.length; i++)
			if(nums[i] == target)
				count++;
		return count;
	}
	
	//----------------------------------------------------------------------------
	
	public int missingNumber(int[] nums) 
	{
		/*
		 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
		 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
		 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
		 * 
		 * 思路：
		 * 方法1：遍历
		 * 步骤：
		 * 特例处理：nums=null, nums.length == 0
		 * 方法2：二分法
		 * 
		 * */
		
		//方法1：遍历
		/*
		if(nums == null || nums.length == 0)
			return -1;
		for(int i = 0; i < nums.length; i++)
			if(nums[i] != i)
				return i;
		return -1;
		*/
		
		//方法2：二分法
		if(nums == null || nums.length == 0)
			return -1;
		int low = 0;
		int high = nums.length - 1;
		while(low <= high)
		{
			int pivot = (low + high)/2;
			if(nums[pivot] != pivot)
				high = pivot - 1;
			else
				low = pivot + 1;
		}
		return low;
	}
	

	//-----------------------------------------------------------------------------------
	public int findNthDigit(int n)
	{
		/*
		 * 剑指 Offer 44. 数字序列中某一位的数字
		 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，
		 * 第13位是1，第19位是4，等等。请写一个函数，求任意第n位对应的数字。
		 * 
		 * 思路：
		 * 方法1：迭代
		 * */
		int digit = 1;
		long start = 1;
		long count = 9;
		while(n > count)
		{
			n -= count;
			start *= 10;
			digit++;
			count = 9*start*digit;
		}
		long num = start + (n-1)/digit;
		String s = Long.toString(num);
		int res = s.charAt((n-1)%digit) - '0';
		return res;
	}
	
}


