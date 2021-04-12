package methods;

public class LinearSearch {
	/*
	 * ���Բ��ң�˳����ң������β��ң���ͷ��β����һ�Ρ�
	 * 
	 * */
	
	public boolean FindNumberIn2DArray(int[][] matrix, int target)
	{
		/*
		 * ��ָ Offer 04. ��ά�����еĲ���
		 * ��һ�� n * m �Ķ�ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳������
		 * �����һ����Ч�ĺ���������������һ����ά�����һ���������ж��������Ƿ��и�������
		 * 
		 * ˼·������ʵ���Ǹ������������Ľṹ���������õ����Բ��ң�ʵ���Ƕ�������
		 */
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)	//��ά�����ж��Ƿ�գ���Ҫ����
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
