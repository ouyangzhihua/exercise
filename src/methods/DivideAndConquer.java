package methods;

import java.util.Random;

public class DivideAndConquer {
	/*
	 * ����˼�룺��һ������ֱ�ӽ���Ĵ����⣬�ָ��һЩ��ģ��С����ͬ���⣬�Ա�������ƣ��ֶ���֮��
	 * �������裺
	 * �ֽ⣺��ԭ����ֽ�Ϊ���ɸ���ģ��С���໥��������ԭ������ʽ��ͬ��������
	 * ��������������ģ��С�����ױ������ֱ�ӽ⣬����ݹ�ؽ����������
	 * �ϲ���������������Ľ�ϲ�Ϊԭ����Ľ�
	 * */
	
	public int[] quickSort(int[] nums)
	{
		/*
		 * ����
		 * */
		if(nums == null || nums.length == 0)
			return new int[0];
		randomQuickSort(nums, 0, nums.length-1);
		return nums;
	}
	private void randomQuickSort(int[] nums, int left, int right)
	{
		if(left < right)
		{
			int pos = randomPartition(nums, left, right);
			randomQuickSort(nums, left, pos-1);
			randomQuickSort(nums, pos+1, right);
		}
	}
	private int randomPartition(int[] nums, int left, int right)
	{
		int pos = new Random().nextInt(right-left+1) + left;
		swap(nums, pos, right);
		return partition(nums, left, right);
	}
	private int partition(int[] nums, int left, int right)
	{
		int pivot = nums[right];
		int i = left;
		for(int j = i; j < right; j++)
		{
			if(nums[j] < pivot)
			{
				swap(nums, i, j);
				i++;
			}	
		}
		swap(nums, i, right);
		return i;
	}
	private void swap(int[] nums, int i, int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;		
	}
}
