/* 
�����ҵ������е�ĳ����
˼·��
���裺
*/
package demo;
import java.util.*;

public class FindNumber 
{
	
	/*����
	 * ��ָoffer03���ҳ��������ظ������֡�
	 * ��һ������Ϊ n ������ nums ����������ֶ��� 0��n-1 �ķ�Χ�ڡ�
	 * ������ĳЩ�������ظ��ģ�����֪���м��������ظ��ˣ�Ҳ��֪��ÿ�������ظ��˼��Ρ����ҳ�����������һ���ظ������֡�
	 * 
	 * ˼·��
	 * ����1����������
	 * ֻҪ�ҵ������ظ�2�ε���
	 * ���裺�������飬ÿ�ζ�������ǰ������Ƚ��Ƿ����
	 * ���Ӷȷ������ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n^2)
	 * ----------------------------
	 * 
	 * ����2����ϣ����
	 * ��ϣ����ɢ�У���ͨ���ض���ѧ����������������������ļ�ֵת���ɶ�Ӧ�����ݴ洢��ַ
	 * ˼·���������飬�����ݴ����ϣ���ϣ������ܴ�����˵���������Ѿ�������������ҵ��ظ�����
	 * �ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n)
	 * -----------------------------
	 * 
	 * ����3��ԭ���û�
	 * ˼·�����鳤��Ϊn,ֵ��0��n-1 �ķ�Χ�ڣ���û���ظ���������ÿ���������Լ�������ֵ��ȡ�
	 * ����������֮��ֵ������ֵ���ȵľ����ظ���
	 * ���裺�������飬��ֵnums[i]������ֵi���ȣ��͵�ǰֵnums[i]�û�������ֵΪnums[i]��λ��
	 * ���Ӷȷ������ռ临�Ӷ�O(1)��ʱ�临�Ӷ�O(n)
	 * 
	 * */

	//�ҳ��������ظ�������
	public int findRepeatNumber(int[] nums)
	{
		/*
		 * ����1����������
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
		//����2����ϣ����
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
		
		//����3��ԭ���û�
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
	 * ��ָ Offer 04. ��ά�����еĲ���
	 * ��һ�� n * m �Ķ�ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳������
	 * �����һ����Ч�ĺ���������������һ����ά�����һ���������ж��������Ƿ��и�������
	 * 
	 * ˼·��
	 * ����1��������������
	 * ���裺
	 * ���Ӷȷ�����ʱ�临�Ӷ�O(n+m)���ռ临�Ӷ�O(1)
	 * 
	 * ����2������������
	 * ���裺
	 * ���Ӷȷ�����ʱ�临�Ӷ�O(nm)���ռ临�Ӷ�O(1)
	 * */
	public boolean FindNumberIn2DArray(int[][] matrix, int target)
	{
		/*
		//����1������������
		//ע������Ҫ�ж�����ĺϷ���
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
		
		//����2����������
		if(matrix == null || matrix.length == 0 || matrix[0].length==0)
            return false;
		for(int row = 0; row < matrix.length; row++)
			for(int col = 0; col < matrix[0].length; col++)
				if(matrix[row][col] == target)
					return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		int[][] arr = {{1,4,7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24}};
		//int[][] arr = null;
		int a = 10;
		FindNumber matrix = new FindNumber();
		boolean n = matrix.FindNumberIn2DArray(arr, a);
		System.out.println(n);
		
	}
	
}


