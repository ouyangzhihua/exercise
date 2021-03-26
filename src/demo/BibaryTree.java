package demo;
import java.util.*;

public class BibaryTree {
	
	class TreeNode 
	{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	private Map<Integer, Integer> indexMap;
	public TreeNode myBuildTree(int[] preorder, int[] inorder, int pleft, int pright, int ileft, int iright)
	{
		if(pleft > pright)
			return null;
		
		int preorderRoot = pleft;	//ǰ������ĵ�һ��Ԫ�ؾ��Ǹ��ڵ㣬��¼������
		int inorderRoot = indexMap.get(preorder[preorderRoot]);	//�ҵ���������и��ڵ��λ��
		TreeNode root = new TreeNode(preorder[preorderRoot]);	//�������ڵ�
		int sizeLeftSubtree = inorderRoot - ileft;	//�õ��������Ľڵ�����
		//�ݹ�Ĺ��������������ӵ����ڵ�
		//
		root.left = myBuildTree(preorder, inorder, pleft + 1, pleft + sizeLeftSubtree, ileft, inorderRoot - 1);
		root.right = myBuildTree(preorder, inorder, pleft + sizeLeftSubtree + 1, pright, inorderRoot + 1, iright);
		return root;			
	}
	public TreeNode buildTree(int[] preorder, int[] inorder)
	{
		/*
		 * ��ָ Offer 07. �ؽ�������
		 * ��������ĳ��������ǰ���������������Ľ�����ؽ��ö����������������ǰ���������������Ľ���ж������ظ������֡� 
		 * 
		 * ����1���ݹ�
		 * ˼·�������κ�һ������ǰ���������ʽ����[���ڵ� | ������ | ������]��
		 * 					�����������ʽ����[������ | ���ڵ� | ������]
		 * ����ǰ������ĵ�һ��Ԫ�صõ����ڵ㣬���ڲ����ظ������֣�������������ҵ����ڵ㣬����Եõ�����������������
		 * �ֱ�������������������һ��ȫ�µ������ظ��������裬ֱ���ҵ����нڵ㡣
		 * ������������Ҹ��ڵ�ʱ�����ù�ϣ��λ�����Խ���ʱ�临�Ӷȡ��Թ�ϣӳ���ÿһ����ֵ�ԣ�����ʾһ��Ԫ�أ��ڵ��ֵ����
		 * ֵ��ʾ������������г��ֵ�λ�á��ڹ���������Ĺ���֮ǰ�����ǿ��Զ�����������б����һ��ɨ�裬
		 * �Ϳ��Թ���������ϣӳ�䡣�ڴ˺���������Ĺ����У����Ǿ�ֻ��Ҫ O(1)��ʱ��Ը��ڵ���ж�λ�ˡ�
		 * ���裺
		 * 
		 * ���Ӷȷ������ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n)
		 * */
		
		int n = preorder.length;
		//�����ϣӳ��
		indexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++)
			indexMap.put(inorder[i], i);
		return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
	}
	
	public static void main(String[] args)
	{
		int[] pre = {3, 9, 20, 15, 7};
		int[] in = {9, 3, 15, 20, 7};
		BibaryTree bt = new BibaryTree();
		TreeNode tn = bt.buildTree(pre, in);		
	}
	
}
