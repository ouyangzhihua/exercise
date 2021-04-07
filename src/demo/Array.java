package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Array {

	public int[] spiralOrder(int[][] matrix)
	{
		/*
		 * ��ָ Offer 29. ˳ʱ���ӡ����
		 * ����һ�����󣬰��մ���������˳ʱ���˳�����δ�ӡ��ÿһ������
		 * 
		 * ˼·��
		 * ����1��ģ��
		 * ��ʼλ���Ǿ������Ͻǣ���Ҫ������ÿ�ε����˳ʱ��ת��
		 * �߹���Ԫ����Ҫ���λ�Ѿ��߹�
		 * �߽紦��matrix = null,matrix.length == 0,matrix[0].length == 0
		 * 
		 * ����2����ģ��
		 * ��ʼλ��(top,left),ÿһ�ζ�����һ����һ�飬��(top,left)���ҵ�(top,right),(top+1,right)��(bottom,right)
		 * (bottom,right-1)��(bottom,left),(bottom,left)��(top+1,left),Ȼ�����������һ��
		 * ѭ��������left < right  top<bottom
		 * 
		 * */
		
		//����1��ģʽ
		/*
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new int[0];	//���ܷ���null
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] vis = new boolean[rows][cols];
		int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};	//�������
		int dirIndex = 0;
		int total = rows * cols;	//�����Ԫ�ظ���
		int[] order = new int[total];	//�洢˳ʱ���ӡ�ľ���
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
			col += dir[dirIndex][1];	//��������
		}
		return order;
		*/
		
		//����2����ģ��
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new int[0];	//���ܷ���null
		int rows = matrix.length;
		int cols = matrix[0].length;
		int total = rows * cols;	//�����Ԫ�ظ���
		int[] order = new int[total];	//�洢˳ʱ���ӡ�ľ���
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
		 * ��ָ Offer 39. �����г��ִ�������һ�������
		 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡�����Լ��������Ƿǿյģ����Ҹ������������Ǵ��ڶ���Ԫ�ء�
		 * 
		 * ˼·��
		 * ����1�������㷨
		 * ���Ԫ��a�������������������ȷ�Ϊ���������֣���Ԫ��aҲ������Ҳ��ֵ����������Խ������Ϊ���������֣��ڶ�����������
		 * �ֱ���ж��֣��ݹ���⡣�����������������ȣ���Ϊ�������������������ȣ����������һ��Ϊ����
		 * ���裺
		 * ����������������費���ڣ���nums=null��
		 * �����鲻���ڶ���Ԫ��
		 * �ݹ�:
		 * 
		 * ����2������
		 * �����Ŀ�ض�Ҫ��������ִ�������һ������������ǲ����ڴ������ֳ���һ��
		 * �������򣬽����������λ����nums.length/2������Ϊ����
		 * 
		 * ����3��HashMap
		 * 
		 * ����4��Ħ��ͶƱ����Ʊ����������
		 * ������ڳ��ִ��������������ʼƱ��votes=0����votes=0ʱ���赱ǰ��Ϊ��������������+1����������-1��
		 * ֱ������������
		 * */
		
		//����1�������㷨
		/*
		return majority(nums, 0, nums.length-1);
		*/
		
		//����2������
		/*
		Arrays.sort(nums);
		return nums[nums.length/2];
		*/
		
		//����3��HashMap
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
		
		//����4��Ħ��ͶƱ��
		int votes = 0;	//Ʊ��
		int x = 0;
		int count = 0;
		for(int num:nums)
		{
			if(votes == 0)
				x = num;
			votes += num == x ? 1 : -1;
		}
		for(int num:nums)	//�жϵõ��������Ƿ��������г��ִ�������
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
