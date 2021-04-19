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
	
	//------------------------------------------------------------------
	
	public int[] twoSum(int[] nums, int target)
	{
		/*
		 * ��ָ Offer 57. ��Ϊs����������
		 * ����һ����������������һ������s���������в�����������ʹ�����ǵĺ�������s������ж�����ֵĺ͵���s�����������һ�Լ��ɡ�
		 * 
		 * ˼·��
		 * ����1��˫ָ��
		 * 
		 * ���裺
		 * ��������
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
		 * ��ָ Offer 57 - II. ��Ϊs��������������
		 * ����һ�������� target ��������к�Ϊ target ���������������У����ٺ�������������
		 * �����ڵ�������С�������У���ͬ���а����׸����ִ�С�������С�
		 * 
		 * ˼·��
		 * ����1������ö��
		 * �������ٺ���2����������ö�ٵ��Ͻ�Ϊtarget/2
		 * 
		 * ����2��ö��+��ѧ�Ż�
		 * ���֪�����x���յ�y����x��y֮�Ϳɵ�(x+y)(y-x+1)/2�����Ѱ��y����(x+y)(y-x+1)/2=target
		 * ת��Ϊ����y�Ķ�Ԫһ�η��̣����������ʽ���y�����ж��Ƿ�Ϊ������
		 * 
		 * ����3��˫ָ��
		 * 
		 * */
		
		//����1������ö��
		/*
		List<int[]> res = new ArrayList<int[]>();	//�������Ԫ�� ʹ������洢�������������ָ���������
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
		
		//����2��ö��+��ѧ�Ż�
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
		
		//����3��˫ָ��
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
		 * ��ָ Offer 45. �������ų���С����
		 * ����һ���Ǹ��������飬����������������ƴ�������ų�һ��������ӡ��ƴ�ӳ���������������С��һ����
		 * 
		 * ˼·��
		 * ����1���Զ�������
		 * �Զ���ȽϹ�����ƴ���ַ��� x + y > y + x, ��x > y
		 * �䴫���Կ�֤���� x + y < y + x, y + z < z + y,��x + z < z + xһ������
		 * */
		//���ÿ����㷨
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
	
	//�����ѡ����Ԫ
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
