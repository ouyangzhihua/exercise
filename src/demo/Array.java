package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Array {

	public int[] spiralOrder(int[][] matrix)
	{
		/*
		 * 剑指 Offer 29. 顺时针打印矩阵
		 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
		 * 
		 * 思路：
		 * 方法1：模拟
		 * 初始位置是矩阵左上角，需要控制其每次到界就顺时针转向，
		 * 走过的元素需要标记位已经走过
		 * 边界处理：matrix = null,matrix.length == 0,matrix[0].length == 0
		 * 
		 * 方法2：层模拟
		 * 初始位置(top,left),每一次都将这一层走一遍，从(top,left)往右到(top,right),(top+1,right)到(bottom,right)
		 * (bottom,right-1)到(bottom,left),(bottom,left)到(top+1,left),然后继续遍历下一层
		 * 循环条件：left < right  top<bottom
		 * 
		 * */
		
		//方法1：模式
		/*
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new int[0];	//不能返回null
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] vis = new boolean[rows][cols];
		int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};	//方向控制
		int dirIndex = 0;
		int total = rows * cols;	//获得总元素个数
		int[] order = new int[total];	//存储顺时针打印的矩阵
		int row = 0;
		int col = 0;
		for(int i = 0; i < total; i++)
		{
			order[i] = matrix[row][col];
			vis[row][col] = true;
			int nextrow = row + dir[dirIndex][0];
			int nextcol = col + dir[dirIndex][1];
			if(nextrow < 0 || nextrow >= rows || nextcol < 0 || nextcol >= cols || vis[nextrow][nextcol] == true)
			{
				dirIndex = (dirIndex + 1) % 4;
			}
			row += dir[dirIndex][0];
			col += dir[dirIndex][1];	//更新行列
		}
		return order;
		*/
		
		//方法2：层模拟
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new int[0];	//不能返回null
		int rows = matrix.length;
		int cols = matrix[0].length;
		int total = rows * cols;	//获得总元素个数
		int[] order = new int[total];	//存储顺时针打印的矩阵
		int left = 0;
		int right = cols - 1;
		int top = 0;
		int bottom = rows - 1;
		int index = 0;
		while(left <= right && top <= bottom)
		{
			for(int col = left; col <= right; col++)
				order[index++] = matrix[top][col];
			for(int row = top+1; row <= bottom; row++)
				order[index++] = matrix[row][right];
			if(left < right && top < bottom)
			{
				for(int col = right-1; col >=left; col--)
					order[index++] = matrix[bottom][col];
				for(int row = bottom - 1; row > top; row--)
					order[index++] = matrix[row][left];
			}
			top++;
			bottom--;
			left++;
			right--;
		}
		return order;
	}
	
	//---------------------------------------------------------------------------------
	
	public int majorityElement(int[] nums)
	{
		/*
		 * 剑指 Offer 39. 数组中出现次数超过一半的数字
		 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。你可以假设数组是非空的，并且给定的数组总是存在多数元素。
		 * 
		 * 思路：
		 * 方法1：分治算法
		 * 如果元素a是数组的众数，将数组等分为左右两部分，则元素a也是左或右部分的众数。可以将数组分为左右两部分，在对左右两部分
		 * 分别进行二分，递归求解。若左右两侧的众数相等，则为整个数组众数，若不等，则数量多的一个为众数
		 * 步骤：
		 * 特例处理：若题设假设不存在，即nums=null，
		 * 若数组不存在多数元素
		 * 递归:
		 * 
		 * 方法2：排序
		 * 针对题目特定要求，求出出现次数超过一半的数，不考虑不存在次数出现超过一半
		 * 利用排序，将数组排序后，位置在nums.length/2的数即为所求
		 * 
		 * 方法3：HashMap
		 * 
		 * 方法4：摩尔投票法：票数正负抵消
		 * 假设存在出现次数过半的数。初始票数votes=0，当votes=0时假设当前数为众数，遇到众数+1，不是众数-1，
		 * 直至遍历完数组
		 * */
		
		//方法1：分治算法
		/*
		return majority(nums, 0, nums.length-1);
		*/
		
		//方法2：排序
		/*
		Arrays.sort(nums);
		return nums[nums.length/2];
		*/
		
		//方法3：HashMap
		/*
		Map<Integer, Integer> map = new HashMap<>();
		int n = nums.length/2;
		for(int num:nums)
		{
			map.put(num, map.getOrDefault(num, 0)+1);
			if(map.get(num) > n)
				return num;
		}
		return 0;
		*/
		
		//方法4：摩尔投票法
		int votes = 0;	//票数
		int x = 0;
		int count = 0;
		for(int num:nums)
		{
			if(votes == 0)
				x = num;
			votes += num == x ? 1 : -1;
		}
		for(int num:nums)	//判断得到的众数是否在数组中出现次数过半
		{
			if(num == x)
				count++;
		}
		return count > nums.length/2 ? x : 0;
	}
	private int majority(int[] nums, int left, int right)
	{
		if(left == right)
			return nums[left];
		int mid = left + (right - left)/2;
		int leftNum = majority(nums, left, mid);
		int rightNum = majority(nums, mid+1, right);
		if(leftNum == rightNum)
			return leftNum;
		int countl = 0;
		int countr = 0;
		for(int i = left; i <= right; i++)
		{
			if(nums[i] == leftNum)
				countl++;
			if(nums[i] == rightNum)
				countr++;
		}
		if(countl > countr)
			return leftNum;
		else
			return rightNum;
	}
	
	//------------------------------------------------------------------
	
	public int[] twoSum(int[] nums, int target)
	{
		/*
		 * 剑指 Offer 57. 和为s的两个数字
		 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
		 * 
		 * 思路：
		 * 方法1：双指针
		 * 
		 * 步骤：
		 * 特例处理：
		 * */
		if(nums == null || nums.length == 0)
			return new int[0];
		int first = 0;
		int last = nums.length - 1;
		while(first < last)
		{
			if(nums[first]+nums[last] < target)
				first++;
			else if(nums[first]+nums[last] > target)
				last--;
			else
				return new int[] {nums[first], nums[last]};
		}
		return new int[0];
	}
	
	//------------------------------------------------------------------------------
	
	public int[][] findContinuousSequence(int target)
	{
		/*
		 * 剑指 Offer 57 - II. 和为s的连续正数序列
		 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
		 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
		 * 
		 * 思路：
		 * 方法1：暴力枚举
		 * 由于至少含有2个数，所以枚举的上界为target/2
		 * 
		 * 方法2：枚举+数学优化
		 * 如果知道起点x和终点y，则x到y之和可得(x+y)(y-x+1)/2，因此寻找y满足(x+y)(y-x+1)/2=target
		 * 转化为关于y的二元一次方程，套用求根公式求得y，并判断是否为整数解
		 * 
		 * 方法3：双指针
		 * 
		 * */
		
		//方法1：暴力枚举
		/*
		List<int[]> res = new ArrayList<int[]>();	//方便添加元素 使用链表存储结果。父类引用指向子类对象
		int sum = 0;
		int limit = target/2;
		for(int i = 1; i <= limit; i++)
		{
			int j = i;
			while(sum < target)
			{
				sum = sum + j;
				j++;
			}
			if(sum == target)
			{
				int[] arr = new int[j-i];
				for(int k = 0; k < j-i; k++)
				{
					arr[k] = k + i;
				}
				res.add(arr);
				sum = 0;
			}
			else
			{
				sum = 0;
			}
		}
		return res.toArray(new int[res.size()][]);
		*/
		
		//方法2：枚举+数学优化
		/*
		List<int[]> res = new ArrayList<int[]>();
		int limit = target/2;
		for(int i = 1; i <= limit; i++)
		{
			long delta = 1 - 4 * (i - (long)i * i - 2 * target);
			if(delta < 0)
				continue;
			int deltaSqrt = (int) Math.sqrt(delta);
			if((long)deltaSqrt * deltaSqrt == delta && (deltaSqrt-1)%2 == 0)
			{
				int y = (deltaSqrt - 1)/2;
				if(i < y)
				{
					int[] arr = new int[y-i+1];
					for(int k = 0; k < y-i+1; k++)
					{
						arr[k] = k + i;
					}
					res.add(arr);
				}
			}
		}
		return res.toArray(new int[res.size()][]);
		*/
		
		//方法3：双指针
		List<int[]> res = new ArrayList<int[]>();
		for(int left = 1, right = 2; left < right; )
		{
			int sum = (left + right) * (right - left + 1)/2;
			if(sum == target)
			{
				int[] arr = new int[right-left+1];
				for(int k = 0; k < right-left+1; k++)
				{
					arr[k] = k + left;
				}
				res.add(arr);
				left++;
			}
			else if(sum < target)
				right++;
			else
				left++;
		}
		return res.toArray(new int[res.size()][]);
	}
	
	//-------------------------------------------------------------------------------
	public String minNumber(int[] nums)
	{
		/*
		 * 剑指 Offer 45. 把数组排成最小的数
		 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
		 * 
		 * 思路：
		 * 方法1：自定义排序
		 * 自定义比较规则：若拼接字符串 x + y > y + x, 则x > y
		 * 其传递性可证明若 x + y < y + x, y + z < z + y,则x + z < z + x一定成立
		 * */
		//套用快排算法
		if(nums == null || nums.length == 0)
			return "";
		String[] s = new String[nums.length];
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < nums.length; i++)
		{
			s[i] = String.valueOf(nums[i]);
		}
		randomQuickSort(s, 0, s.length-1);
		for(String i:s)
		{
			res.append(i);
		}
		return res.toString();
	}
	private void randomQuickSort(String[] s, int left, int right)
	{
		if(left < right)
		{
			int pos = randomPartition(s, left, right);
			randomQuickSort(s, left, pos-1);
			randomQuickSort(s, pos+1, right);
		}
	}
	private int randomPartition(String[] s, int left, int right)
	{
		int i = new Random().nextInt(right - left + 1) + left;
		swap(s, i, right);
		return partition(s, left, right);
	}
	private int partition(String[] s, int left, int right)
	{
		String pivot = s[right];
		int i = left;
		for(int j = left; j < right; j++)
		{
			 if((s[j]+pivot).compareTo(pivot+s[j]) <= 0)
			 {
				 swap(s, i, j);
				 i++;
			 }	 
		}
		swap(s, i, right);
		return i;
	}
	private void swap(String[] s, int i, int j)
	{
		String temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}
	
	//不随机选择主元
	/*
	    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    void quickSort(String[] strs, int l, int r) {
        if(l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while(i < j) {
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }
	*/



}
