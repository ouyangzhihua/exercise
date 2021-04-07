package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
