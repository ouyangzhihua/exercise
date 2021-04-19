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
	
	
	//------------------------------------------------------------------------------------------
	char[] c;
	List<String> res1 = new LinkedList<>();
	public String[] permutation(String s)
	{
		/*
		 * ��ָ Offer 38. �ַ���������
		 * ����һ���ַ�������ӡ�����ַ������ַ�����������.�����������˳�򷵻�����ַ������飬�����治�����ظ�Ԫ�ء�
		 * 
		 * ˼·��
		 * ����1�����ݷ������������������֦
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
