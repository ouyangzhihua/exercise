/*
 ������������
 ˼·��ѡ������ð�����򣬿������򣬲�������ϣ������
 ���裺
*/
package demo;

class ArraySort
{
	//ѡ������:��ѭ������һ�Σ���ֵ����ͷ�Ǳ�λ����
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
	
	//ð���������ڵ�����Ԫ�ؽ��бȽϣ��Ƚ�һȦ��ֵ�ŵ����
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
	
	//�������򣺷ֶ���֮˼��.��ѵ����򷽷�
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
	
	//��������
	public int[] insertSort(int[] arr)
	{
		for(int i = 1; i < arr.length; i++)
		{
			int temp = arr[i];
			int j = i - 1;
			while(j >= 0 && temp < arr[j])
			{
				arr[j+1] = arr[j];	//��ǰ��       ����Ԫ��������һ��λ��
				j--;
			}
			arr[j+1] = temp;
		}
		return arr;
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
