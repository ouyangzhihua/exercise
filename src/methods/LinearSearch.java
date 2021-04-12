package methods;

public class LinearSearch {
	/*
	 * 线性查找（顺序查找）：依次查找，从头到尾遍历一次。
	 * 
	 * */
	
	public boolean FindNumberIn2DArray(int[][] matrix, int target)
	{
		/*
		 * 剑指 Offer 04. 二维数组中的查找
		 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
		 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
		 * 
		 * 思路：此题实际是个二叉搜索树的结构，故这里用的线性查找，实际是二叉搜索
		 */
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)	//二维数组判断是否空，需要三项
			return false;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int row = 0;
		int col = cols - 1;
		while(row < rows && col >= 0)
		{
			if(matrix[row][col] < target)
				row++;
			else if(matrix[row][col] > target)
				col--;
			else
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
