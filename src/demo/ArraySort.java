/*
 ������������
 ˼·��ѡ������ð�����򣬿������򣬲�������ϣ������
 
 ���裺
*/
package demo;

import java.util.Random;

class ArraySort
{
	/*
	 * ѡ������:��������ѡ����СԪ�أ�����������ĵ�һ��Ԫ�ؽ���λ�á�
	 * �ٴ�����ʣ�µ�Ԫ����ѡ�����С��Ԫ�أ�����������ĵڶ���Ԫ�ؽ���λ�á�
	 * ���Ͻ��������Ĳ�����ֱ����������������
	 * 
	 * ���Ӷȷ�����ʱ�临�Ӷ�O(n^2)���ռ临�Ӷ�O(1)
	 * ������á����ƽ���������Ƚ�n(n-1)/2
	 * ������������С���в��������Ѿ�����ѡ��������Ҫ ~N2/2 �αȽϺ� ~N �ν�����
	 * ��������ʱ���������޹أ�����ص�ʹ������һ���Ѿ����������Ҳ��Ҫ��ô��ıȽϺͽ���������
	 * */
	
	public int[] choseSort(int[] arr)
	{
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = i; j < arr.length; j++)
			{
				if(arr[i] > arr[j])
				{
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	/*ð�����򣺴����Ҳ��Ͻ������������Ԫ�أ���һ�ֵ�ѭ��֮�󣬿�����δ��������Ԫ���ϸ����Ҳ�
	 * 		 ��һ��ѭ���У����û�з�����������ô˵�������Ѿ�������ģ���ʱ����ֱ���˳�
	 * ���Ӷȷ�����ʱ�临�Ӷ�O(n^2)���ռ临�Ӷ�O(1)
	 * ���ƽ���������Ƚ�n(n-1)/2�����������Ѿ��ź���ֻ��һ��ɨ�� �Ƚ�n-1��
	 * ������������С���в��������Ѿ�����
	**/		
	public int[] bubbleSort(int[] arr)
	{
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr.length-i-1; j++)
			{
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		return arr;
	}
	
	/*
	 * ��������:ÿ�ζ�����ǰԪ�ز��뵽����Ѿ�����������У�ʹ�ò���֮�����������Ȼ����
	 * ���Ӷȷ�����ʱ�临�Ӷ�O(n^2)���ռ临�Ӷ�O(1)
	 * ���ƽ���������Ƚ�n(n-1)/2�����������Ѿ��ź���ֻ��һ��ɨ�� �Ƚ�n-1��
	 * ���������������ݵĴ������ƣ����Խ�����������ʹ��
	 * */
	public int[] insertSort(int[] arr)
	{
		for(int i = 1; i < arr.length; i++)
		{
			int temp = arr[i];
			int j = i - 1;
			while(j >= 0 && temp < arr[j])
			{
				arr[j+1] = arr[j];	//��ǰ��Ԫ��������һ��λ��
				j--;
			}
			arr[j+1] = temp;
		}
		return arr;
	}
	
	/*
	 * �������򣺷ֶ���֮˼��.��ѵ����򷽷�
	 * ͨ�����ֽ�����������зֳ�ǰ�������֣�����ǰһ���ֵ����ݶ��Ⱥ�һ���ֵ�����ҪС��
	 * Ȼ���ٵݹ���ú����������ֵ����зֱ���п��������Դ�ʹ�������дﵽ����
	 * �������ѡȡ��Ԫ�Ŀ�������ʱ�临�Ӷ�Ϊ���� O(nlog n)������ nΪ����ĳ���
	 * �ռ临�Ӷȣ�O(h)������ h Ϊ��������ݹ���õĲ���.�������� O(n) �Ŀռ䣬
	 * ���������ÿ�ζ�ƽ�⣬��ʱ�����ݹ����߶�Ϊ log n���ռ临�Ӷ�Ϊ O(log n)
	 * */
	/*
	public int[] quickSort(int[] arr, int l, int r)
	{
		if(l < r)
		{
			int l_idx = l + 1;
			int r_idx = r;
			
			while(true)
			{
				for(int i = l+1; i <= r; i++)
				{
					if(arr[i] >= arr[l])
					{	
						l_idx = i;
						break;
					}
					l_idx++;
				}
				
				for(int j = r; j >= l+1; j--)
				{
					if(arr[j] <= arr[l])
					{
						r_idx = j;
						break;
					}
					r_idx--;
				}
				
				if(l_idx < r_idx)	
					swap(arr,l_idx,r_idx);
				else
					break;
			}
			
			if(l_idx >= r_idx)
			{
				swap(arr,l,r_idx);
				quickSort(arr,l,r_idx-1);
				quickSort(arr,r_idx+1,r);
			}
		}
		
		return arr;
	}
	*/
	public int[] sortArray(int[] nums) {
        randomizedQuicksort(nums, 0, nums.length - 1);
        return nums;
    }

    public void randomizedQuicksort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomizedQuicksort(nums, l, pos - 1);
            randomizedQuicksort(nums, pos + 1, r);
        }
    }

    public int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l; // ���ѡһ����Ϊ���ǵ���Ԫ
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    public int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }


	
	//ϣ�����򣺽��������ֳ��ض�����ļ���С���죬Ȼ���ò�����������ÿ��С���죬������С���
	public int[] shellSort(int[] arr)
	{
		int jmp = arr.length / 2;
		while(jmp != 0)
		{
			for(int i = jmp; i < arr.length; i++)
			{
				int temp = arr[i];
				int j = i - jmp;
				while(j >= 0 && temp < arr[j])
				{
					arr[j+jmp] = arr[j];
					j = j - jmp;
				}
				arr[jmp+j] = temp;
			}
			jmp = jmp / 2;
		}
		return arr;
	}
	
	
	
	
	public static void main(String[] args)
	{
		int[] arr = {13,8,4,7,-9,2,0,5};
		ArraySort matrix = new ArraySort();
		matrix.shellSort(arr);
		matrix.printArray(arr);
	}
	
	
	
	//����λ��
	public void swap(int[] arr,int a,int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	//��ӡ���
	public void printArray(int[] arr)
	{
		System.out.print("[");
		for(int x=0; x<arr.length; x++)
		{
			if(x!=arr.length-1)
				System.out.print(arr[x]+", ");
			else
				System.out.println(arr[x]+"]");
		}		
	}

}
