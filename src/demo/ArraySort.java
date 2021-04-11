/*
 需求：数组排序
 思路：选择排序，冒泡排序，快速排序，插入排序，希尔排序
 
 步骤：
*/
package demo;

import java.util.Random;

public class ArraySort
{
	/*
	 * 选择排序:从数组中选择最小元素，将它与数组的第一个元素交换位置。
	 * 再从数组剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。
	 * 不断进行这样的操作，直到将整个数组排序。
	 * 
	 * 复杂度分析：时间复杂度O(n^2)，空间复杂度O(1)
	 * 无论最好、最坏、平均情况：需比较n(n-1)/2
	 * 适用于数据量小或有部分数据已经排序。选择排序需要 ~N2/2 次比较和 ~N 次交换，
	 * 它的运行时间与输入无关，这个特点使得它对一个已经排序的数组也需要这么多的比较和交换操作。
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
	
	/*冒泡排序：从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧
	 * 		 在一轮循环中，如果没有发生交换，那么说明数组已经是有序的，此时可以直接退出
	 * 复杂度分析：时间复杂度O(n^2)，空间复杂度O(1)
	 * 最坏和平均情况：需比较n(n-1)/2，最好情况：已经排好序，只需一次扫描 比较n-1次
	 * 适用于数据量小或有部分数据已经排序
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
	 * 插入排序:每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
	 * 复杂度分析：时间复杂度O(n^2)，空间复杂度O(1)
	 * 最坏和平均情况：需比较n(n-1)/2，最好情况：已经排好序，只需一次扫描 比较n-1次
	 * 插入排序会造成数据的大量搬移，所以建议在链表上使用
	 * */
	public int[] insertSort(int[] arr)
	{
		for(int i = 1; i < arr.length; i++)
		{
			int temp = arr[i];
			int j = i - 1;
			while(j >= 0 && temp < arr[j])
			{
				arr[j+1] = arr[j];	//把前面元素往后推一个位置
				j--;
			}
			arr[j+1] = temp;
		}
		return arr;
	}
	
	/*
	 * 快速排序：分而治之思想.最佳的排序方法
	 * 通过划分将待排序的序列分成前后两部分，其中前一部分的数据都比后一部分的数据要小，
	 * 然后再递归调用函数对两部分的序列分别进行快速排序，以此使整个序列达到有序。
	 * 基于随机选取主元的快速排序时间复杂度为期望 O(nlog n)，其中 n为数组的长度
	 * 空间复杂度：O(h)，其中 h 为快速排序递归调用的层数.最坏情况下需 O(n) 的空间，
	 * 最优情况下每次都平衡，此时整个递归树高度为 log n，空间复杂度为 O(log n)
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
        int i = new Random().nextInt(r - l + 1) + l; // 随机选一个作为我们的主元
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


	
	//希尔排序：将数据区分成特定间隔的几个小区快，然后用插入排序排完每个小区快，再逐渐缩小间隔
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
	
	
	
	//交换位置
	public void swap(int[] arr,int a,int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	//打印输出
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
