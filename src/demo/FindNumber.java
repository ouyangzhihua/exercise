/* 
任务：找到数组中的某个数
思路：
步骤：
*/
package demo;
import java.util.*;

/*需求：
 * 剑指offer03：找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 
 * 思路：
 * 方法1：暴力遍历
 * 只要找到最新重复2次的数
 * 遍历数组，每次都将数与前面的数比较是否相等
 * 空间复杂度O(n)，时间复杂度O(n^2)
 * ----------------------------
 * 
 * 方法2：哈希集合
 * 哈希法（散列）：通过特定数学函数或其他方法，将本身的键值转换成对应的数据存储地址
 * 思路：遍历数组，将数据存入哈希集合，若不能存入则说明集合中已经存入该数，即找到重复的数
 * 空间复杂度O(n)，时间复杂度O(n)
 * -----------------------------
 * 
 * 方法3：原地置换
 * 思路：数组长度为n,值在0～n-1 的范围内，若没有重复的数，则每个数都和自己的索引值相等。
 * 将数组排序之后，值与索引值不等的就是重复数
 * 步骤：遍历数组，若值与索引值不等，就当前值与
 * 
 * 
 * */

public class FindNumber 
{
	//找出数组中重复的数字
	public int findRepeatNumber(int[] nums)
	{
		/*
		 * 方法1：暴力遍历
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
		
		//方法2：哈希集合
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
	}
	

	
	public static void main(String[] args)
	{
		int[] arr = {13,2,-9,2,1,5,4};
		FindNumber matrix = new FindNumber();
		int num = matrix.findRepeatNumber(arr);
		System.out.println(num);
	}
	
}


