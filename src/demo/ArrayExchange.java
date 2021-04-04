package demo;

public class ArrayExchange {

	public int[] exchange(int[] nums)
	{
		/*
		 * ��ָ Offer 21. ��������˳��ʹ����λ��ż��ǰ��
		 * ����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ����������λ�������ǰ�벿�֣�����ż��λ������ĺ�벿�֡�
		 * 
		 * ˼·��
		 * ����1������ָ��
		 * 
		 * ����2����βָ��
		 * 
		 * ����3��ʹ��һ��������洢
		 * 
		 * ����4�������չ�ԣ����ж�����������ǰ���ֻ��Ǻ󲿷ֵ��߼��������
		 * */
		//����1
		/*
		for(int i = 0; i < nums.length-1; i++)
		{
			if((nums[i]&1) == 0)
			{
				for(int j = i+1; j < nums.length; j++)
				{
					if((nums[j]&1) == 1)
					{
						int temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
						break;
					}
				}
			}
		}
		return nums;
		*/
		
		
		//����2����βָ��
		/*
		int first = 0;
		int last = nums.length-1;
		while(first < last)
		{
			if((nums[first]&1) == 1)
			{
				first++;
				continue;
			}
			if((nums[last]&1) == 0)
			{
				last--;
				continue;
			}
			int temp = nums[first];
			nums[first] = nums[last];
			nums[last] = temp;
		}
		return nums;
		*/
		
		/*
		//����3��ʹ��һ��������洢
		int[] res = new int[nums.length];
		int left = 0;
		int right = nums.length-1;
		for(int i = 0; i < nums.length; i++)
		{
			if((nums[i]&1) == 0)
			{
				res[right] = nums[i];
				right--;
			}
			else
			{
				res[left] = nums[i];
				left++;
			}	
		}
		return res;
		*/
		
		//����4�������չ��
		int first = 0;
		int last = nums.length-1;
		while(first < last)
		{
			if(!isEven(nums[first]))
			{
				first++;
				continue;
			}
			if(isEven(nums[last]))
			{
				last--;
				continue;
			}
			int temp = nums[first];
			nums[first] = nums[last];
			nums[last] = temp;
		}
		return nums;	
	}
	
	private boolean isEven(int number)
	{
		return (number&1)==0;
	}
	
			
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,2,3,4,5,7};
		ArrayExchange ex = new ArrayExchange();
		arr = ex.exchange(arr);
		for(int x=0; x<arr.length; x++)
		{
			System.out.print(arr[x]+",");
		}
	}

}
