package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L78N27Subsets {
	/*
	 * 78. 子集
	 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
	 * 
	 * 1,回溯法
	 * */
	public List<List<Integer>> subsets(int[] nums)
	{
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		backtrack(0, nums, path, res);
		return res;
	}
	
	private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> res)
	{
		res.add(new ArrayList<Integer>(path));
		for(int i = start; i < nums.length; i++)
		{
			path.add(nums[i]);
			backtrack(i+1, nums, path, res);
			path.remove(path.size()-1);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		L78N27Subsets f = new L78N27Subsets();
		while(in.hasNext())
		{
			int n = in.nextInt();
			int[] nums = new int[n];
			for(int i = 0; i < n; i++)
			{
				nums[i] = in.nextInt();
			}
			List<List<Integer>> res = f.subsets(nums);
			System.out.println(res);
		}
		in.close();
	}
}
