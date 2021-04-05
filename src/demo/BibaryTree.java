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
		
		int preorderRoot = pleft;	//前序遍历的第一个元素就是根节点，记录其索引
		int inorderRoot = indexMap.get(preorder[preorderRoot]);	//找到中序遍历中根节点的位置
		TreeNode root = new TreeNode(preorder[preorderRoot]);	//建立根节点
		int sizeLeftSubtree = inorderRoot - ileft;	//得到左子树的节点数量
		//递归的构造左子树并连接到根节点
		//
		root.left = myBuildTree(preorder, inorder, pleft + 1, pleft + sizeLeftSubtree, ileft, inorderRoot - 1);
		root.right = myBuildTree(preorder, inorder, pleft + sizeLeftSubtree + 1, pright, inorderRoot + 1, iright);
		return root;			
	}
	public TreeNode buildTree(int[] preorder, int[] inorder)
	{
		/*
		 * 剑指 Offer 07. 重建二叉树
		 * 需求：输入某二叉树的前序遍历和中序遍历的结果，重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
		 * 
		 * 方法1：递归
		 * 思路：对于任何一棵树，前序遍历的形式总是[根节点 | 左子树 | 右子树]，
		 * 					中序遍历的形式总是[左子树 | 根节点 | 右子树]
		 * 根据前序遍历的第一个元素得到根节点，由于不含重复的数字，在中序遍历的找到根节点，则可以得到左子树和右子树。
		 * 分别将左子树和右子树看成一个全新的树，重复上述步骤，直到找到所有节点。
		 * 在中序遍历中找根节点时，采用哈希表定位，可以降低时间复杂度。对哈希映射的每一个键值对，键表示一个元素（节点的值），
		 * 值表示其在中序遍历中出现的位置。在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，
		 * 就可以构造出这个哈希映射。在此后构造二叉树的过程中，我们就只需要 O(1)的时间对根节点进行定位了。
		 * 步骤：
		 * 
		 * 复杂度分析：空间复杂度O(n)，时间复杂度O(n)
		 * */
		
		int n = preorder.length;
		//构造哈希映射
		indexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++)
			indexMap.put(inorder[i], i);
		return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
	}
	
	
	//--------------------------------------------------------------------------------------
	public TreeNode mirrorTree(TreeNode root)
	{
		/*
		 * 剑指 Offer 27. 二叉树的镜像
		 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
		 * 
		 * 思路：
		 * 方法1：递归
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
		 * 剑指 Offer 28. 对称的二叉树
		 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
		 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
		 * 
		 * 思路：
		 * 方法1：递归
		 * 二叉树对称则有：1，root.left.val = root.right.val
		 * 2, l.left.val = r.right.val
		 * 3, l.right.val = r.left.val
		 * 可用递归从顶至下，其是否对称
		 * 步骤：
		 * 输入边界值处理：root == null, return true
		 * 递归终止条件：1，L和R同时越过叶节点，此时整树对称
		 * 2，L或R有一个先越过叶节点
		 * 3，当前L节点值！=当前R节点值
		 * 递归工作：
		 * recur(l.left,r.right)
		 * recur(l.right, r.left)
		 * 返回值：recur(l.left,r.right) && recur(l.right, r.left)
		 * 复杂度分析：
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
		 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
		 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
		 * 
		 * 思路：
		 * 方法1：广度优先搜索
		 * 每一层打印一行可以通过广度优先搜索找到一层的所有节点。
		 * 广度优先搜索通常利用队列先进先出的特性实现
		 * 
		 * 步骤：
		 * 特例处理：root=null，返回空列表
		 * 初始化：
		 * BFS循环条件：
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
	
	public static void main(String[] args)
	{
		int[] pre = {3, 9, 20, 15, 7};
		int[] in = {9, 3, 15, 20, 7};
		BibaryTree bt = new BibaryTree();
		TreeNode tn = bt.buildTree(pre, in);		
	}
	
}
