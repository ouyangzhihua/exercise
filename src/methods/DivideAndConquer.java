package methods;

import java.util.Random;

public class DivideAndConquer {
	/*
	 * 分治思想：将一个难以直接解决的大问题，分割成一些规模较小的相同问题，以便各个击破，分而治之。
	 * 基本步骤：
	 * 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
	 * 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
	 * 合并：将各个子问题的解合并为原问题的解 
	 * */
	
	public int[] quickSort(int[] nums)
	{
		/*
		 * 快排
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
	
	
	//----------------------------------------------------------------------------------
	public boolean verifyPostorder(int[] postorder)
	{
		/*
		 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
		 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
		 * 假设输入的数组的任意两个数字都互不相同。
		 * 
		 * 思路：
		 * 方法1：递归分治
		 * 后序遍历形式：[左子树 | 右子树 | 根节点]。
		 * 二叉搜索树满足：左子树所有节点值<根节点值<右子树所有节点值
		 * */
		return recur(postorder, 0, postorder.length-1);	
	}
	private boolean recur(int[] postorder, int left, int right)
	{
		if(left >= right)
			return true;
		int root = postorder[right];
		int temp = left;
		while(postorder[temp] < root)
			temp++;
		int m = temp;
		while(postorder[temp] > root)
			temp++;
		return temp == right && recur(postorder, left, m-1) && recur(postorder, m, right-1);
	}
}
