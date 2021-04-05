package demo;

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
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
