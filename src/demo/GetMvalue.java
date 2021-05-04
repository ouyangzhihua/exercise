/* 
任务：找到数组中的最值
思路：
步骤：
*/
package demo;

public class GetMvalue
{
	public int getMax(int[] arr)
	{
		/*
		 * 获取最大值
		 */
		int max = arr[0];
		for(int i = 1; i < arr.length; i++)
		{
			if(arr[i] > max)
				max = arr[i];
		}
		return max;
	}
	
	/*
	 * 获取最值的另一种方式：初始化数组角标，此时可以将临时变量初始化为0
	 */
	public int getMax_2(int[] arr)
	{
		int MaxIndex = 0;
		for(int i = 1;i < arr.length; i++)
		{
			if(arr[i] > arr[MaxIndex])
				MaxIndex = i;
		}
		return arr[MaxIndex];
	}

	public int getMin(int[] arr)
	{
		/*
		 * 获取最小值
		 */
		int min = arr[0];
		for(int i = 1; i < arr.length; i++)
		{
			if(arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	
	/*二维数组查找,与前面一维数组查找方法重载*/
	public int getMax(int[][] arr)
	{
		int max = arr[0][0];
		for(int row = 0; row < arr.length; row++)
		{
			for(int col = 0; col < arr[0].length; col++)
			{
				if(arr[row][col] > max)
					max = arr[row][col];
			}
		}
		return max;
	}

	
	public static void main(String[] args)
	{
		int[] arr = {13,8,4,7,-9,2,0};
		int[][] arr2 = { {1,8,4},{10,5,3} };
		GetMvalue matrix = new GetMvalue();
		int max = matrix.getMax(arr);
		int max_2 = matrix.getMax_2(arr);
		int max2d = matrix.getMax(arr2);
		int min = matrix.getMin(arr);
		System.out.println("maxvalue="+max);
		System.out.println("maxvalue="+max_2);
		System.out.println("minvalue="+min);
		System.out.println("maxvalue="+max2d);
	}

}