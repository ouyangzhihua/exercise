package methods;

import java.util.*;

import methods.BackTrack.TreeNode;


public class DepthFirstSearch {
	/*
	 * 深度优先搜索：
	 * */ 
	
	class TreeNode 
	{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) 
		{
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	List<List<Integer>> res = new LinkedList<>();
	LinkedList<Integer> path = new LinkedList<>();
	public List<List<Integer>> pathSum(TreeNode root, int target)
	{
		 /*
		  * 剑指 Offer 34. 二叉树中和为某一值的路径
		  * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
		  * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
		  * 
		  * 思路：
		  * 方法1：回溯法
		  * */
		recur(root, target);
		return res;				
	}
	private void recur(TreeNode root, int target)
	{
		if(root == null)
			return;
		path.add(root.val);
		target -= root.val;
		if(target == 0 && root.left == null && root.right == null)
			res.add(new LinkedList<Integer>(path));	//复制1个path到res,如果直接将path加入res,则当path改变时，res中的也会变
		recur(root.left, target);
		recur(root.right, target);
		path.removeLast();
	}
	
	
	//--------------------------------------------------------------------------------------
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node() {}
	    public Node(int _val) {
	        val = _val;
	    }
	    public Node(int _val,Node _left,Node _right) {
	        val = _val;
	        left = _left;
	        right = _right;
	    }
	}
	Node pre, head;
	public Node treeToDoublyList(Node root)
	{
		/*
		 * 剑指 Offer 36. 二叉搜索树与双向链表
		 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
		 * 
		 * 思路：
		 * 方法1：中序遍历+深度优先搜索递归
		 * 
		 * */
		if(root == null)
			return null;
		depthFirstSearch(root);
		head.left = pre;
		pre.right = head;
		return head;
	}
	private void depthFirstSearch(Node cur)
	{
		if(cur == null)
			return;
		depthFirstSearch(cur.left);
		if(pre != null)
			pre.right = cur;
		else
			head = cur;
		cur.left = pre;
		pre = cur;
		depthFirstSearch(cur.right);
	}
	
	
	//------------------------------------------------------------------------------------------
	char[] c;
	List<String> res1 = new LinkedList<>();
	public String[] permutation(String s)
	{
		/*
		 * 剑指 Offer 38. 字符串的排列
		 * 输入一个字符串，打印出该字符串中字符的所有排列.你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
		 * 
		 * 思路：
		 * 方法1：回溯法、深度优先搜索、剪枝
		 * */
		c = s.toCharArray();
		depthFirstSearch(0);
		return res.toArray(new String[res.size()]);
	}
	private void depthFirstSearch(int x)
	{
		if(x == c.length-1)
		{
			res1.add(String.valueOf(c));
			return;
		}
		Set<Character> set = new HashSet<>();
		for(int i = x; i < c.length; i++)
		{
			if(set.contains(c[i]))
				continue;
			set.add(c[i]);
			swap(i, x);
			depthFirstSearch(x+1);
			swap(i, x);
		}
	}
	private void swap(int i, int j)
	{
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
	
}
