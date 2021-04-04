package demo;

public class ArrayExchange {

	public int[] exchange(int[] nums)
	{
		/*
		 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
		 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
		 * 
		 * 思路：
		 * 方法1：快慢指针
		 * 
		 * 方法2：首尾指针
		 * 
		 * 方法3：使用一个新数组存储
		 * 
		 * 方法4：提高扩展性：将判断是属于数组前部分还是后部分的逻辑抽象出来
		 * */
		//方法1
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
		
		
		//方法2：首尾指针
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
		//方法3：使用一个新数组存储
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
		
		//方法4：提高扩展性
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
