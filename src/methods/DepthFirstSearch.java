package methods;

import java.util.*;

import methods.BackTrack.TreeNode;


public class DepthFirstSearch {
	/*
	 * �������������
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
		  * ��ָ Offer 34. �������к�Ϊĳһֵ��·��
		  * ����һ�ö�������һ����������ӡ���������нڵ�ֵ�ĺ�Ϊ��������������·����
		  * �����ĸ��ڵ㿪ʼ����һֱ��Ҷ�ڵ��������Ľڵ��γ�һ��·����
		  * 
		  * ˼·��
		  * ����1�����ݷ�
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
			res.add(new LinkedList<Integer>(path));	//����1��path��res,���ֱ�ӽ�path����res,��path�ı�ʱ��res�е�Ҳ���
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
		 * ��ָ Offer 36. ������������˫������
		 * ����һ�ö��������������ö���������ת����һ�������ѭ��˫������Ҫ���ܴ����κ��µĽڵ㣬ֻ�ܵ������нڵ�ָ���ָ��
		 * 
		 * ˼·��
		 * ����1���������+������������ݹ�
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
	
	
	//----------------------------------------------------------------------------
}
