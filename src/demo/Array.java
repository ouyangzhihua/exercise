package demo;

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
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
