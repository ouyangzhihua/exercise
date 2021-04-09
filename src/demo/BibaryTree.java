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
	
	
	//--------------------------------------------------------------------------------------
	public TreeNode mirrorTree(TreeNode root)
	{
		/*
		 * ��ָ Offer 27. �������ľ���
		 * �����һ������������һ�����������ú���������ľ���
		 * 
		 * ˼·��
		 * ����1���ݹ�
		 * 
		 * */
		if(root == null)
			return null;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		mirrorTree(root.left);
		mirrorTree(root.right);
		return root;
	}
	
	
	//--------------------------------------------------------------------------------
	
	public boolean isSymmetric(TreeNode root)
	{
		/*
		 * ��ָ Offer 28. �ԳƵĶ�����
		 * ��ʵ��һ�������������ж�һ�ö������ǲ��ǶԳƵġ����һ�ö����������ľ���һ������ô���ǶԳƵġ�
		 * ���磬������ [1,2,2,3,4,4,3] �ǶԳƵġ�
		 * 
		 * ˼·��
		 * ����1���ݹ�
		 * �������Գ����У�1��root.left.val = root.right.val
		 * 2, l.left.val = r.right.val
		 * 3, l.right.val = r.left.val
		 * ���õݹ�Ӷ����£����Ƿ�Գ�
		 * ���裺
		 * ����߽�ֵ����root == null, return true
		 * �ݹ���ֹ������1��L��RͬʱԽ��Ҷ�ڵ㣬��ʱ�����Գ�
		 * 2��L��R��һ����Խ��Ҷ�ڵ�
		 * 3����ǰL�ڵ�ֵ��=��ǰR�ڵ�ֵ
		 * �ݹ鹤����
		 * recur(l.left,r.right)
		 * recur(l.right, r.left)
		 * ����ֵ��recur(l.left,r.right) && recur(l.right, r.left)
		 * ���Ӷȷ�����
		 * */
		if(root == null)
			return true;
		else
			return recur(root.left, root.right);
	}
	private boolean recur(TreeNode l, TreeNode r)
	{
		if(l == null && r == null)
			return true;
		else if(l == null || r == null || l.val != r.val)
			return false;
		else
			return recur(l.left, r.right) && recur(l.right, r.left);
	}
	
	
	//-------------------------------------------------------------------------------
	
	public List<List<Integer>> levelOrder(TreeNode root) 
	{
		/*
		 * ��ָ Offer 32 - II. ���ϵ��´�ӡ������ II
		 * ���ϵ��°����ӡ��������ͬһ��Ľڵ㰴�����ҵ�˳���ӡ��ÿһ���ӡ��һ�С�
		 * 
		 * ˼·��
		 * ����1�������������
		 * ÿһ���ӡһ�п���ͨ��������������ҵ�һ������нڵ㡣
		 * �����������ͨ�����ö����Ƚ��ȳ�������ʵ��
		 * 
		 * ���裺
		 * ��������root=null�����ؿ��б�
		 * ��ʼ����
		 * BFSѭ��������
		 * 
		 * */
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> que = new LinkedList<>();
		if(root != null)
			que.add(root);
		while(!que.isEmpty())
		{
			List<Integer> temp = new ArrayList<>();
			for(int i = que.size(); i > 0; i--)
			{
				TreeNode node = que.poll();
				temp.add(node.val);
				if(node.left != null)
					que.add(node.left);
				if(node.right != null)
					que.add(node.right);
			}
			res.add(temp);
		}
		return res;
	}
	
	//------------------------------------------------------------------------------
	
	int k;
	int res;
	public int kthLargest(TreeNode root, int k)
	{
		/*
		 * ��ָ Offer 54. �����������ĵ�k��ڵ�
		 * ����һ�ö��������������ҳ����е�k��Ľڵ㡣
		 * 
		 * ˼·��
		 * ����1�������������
		 * �������������������Ϊ��������
		 * ���裺
		 * ��������
		 * 
		 * */
		if(root == null || k < 1)
			return -1;
		this.k = k;
		deepFirstSearch(root);
		return res;
	}
	private void deepFirstSearch(TreeNode root)
	{
		if(root == null || k==0)
			return;
		deepFirstSearch(root.right);
		if(--k == 0)
			res = root.val;
		deepFirstSearch(root.left);
	}
	
	//---------------------------------------------------------------------------
	
	public int maxDepth(TreeNode root)
	{
		/*
		 * ��ָ Offer 55 - I. �����������
		 * ����һ�ö������ĸ��ڵ㣬���������ȡ��Ӹ��ڵ㵽Ҷ�ڵ����ξ����Ľڵ㣨������Ҷ�ڵ㣩�γ�����һ��·�����·���ĳ���Ϊ������ȡ�
		 * 
		 * ˼·�����ı��������Ϊ���ࣺ�������������DFS�����������������BFS��
		 * ������DFS��ǰ���������������� �������
		 * ������BFS���������
		 * ����1����������
		 * 
		 * ����2���������
		 * */
		//����1����������
		/*
		if(root == null)
			return 0;
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
		*/
		
		//����2���������
		if(root == null)
			return 0;
		Queue<TreeNode> que = new LinkedList<>();
		Queue<TreeNode> temp;
		int res = 0;
		que.add(root);
		while(!que.isEmpty())
		{
			temp = new LinkedList<>();
			for(TreeNode node:que)
			{
				if(node.left != null)
					temp.add(node.left);
				if(node.right != null)
					temp.add(node.right);
			}
			que = temp;
			res++;
		}
		return res;
	}
	
	public static void main(String[] args)
	{
		int[] pre = {3, 9, 20, 15, 7};
		int[] in = {9, 3, 15, 20, 7};
		BibaryTree bt = new BibaryTree();
		TreeNode tn = bt.buildTree(pre, in);		
	}
	
}
