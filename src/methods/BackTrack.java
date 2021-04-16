package methods;

import java.util.*;

public class BackTrack {
	/*
	 * ���ݷ���������ȱ��� + ״̬���� + ��֦
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
	
	
}
