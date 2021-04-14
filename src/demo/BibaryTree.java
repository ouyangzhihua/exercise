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
	
	//------------------------------------------------------------------------------
	
	int k;
	int res;
	public int kthLargest(TreeNode root, int k)
	{
		/*
		 * 剑指 Offer 54. 二叉搜索树的第k大节点
		 * 给定一棵二叉搜索树，请找出其中第k大的节点。
		 * 
		 * 思路：
		 * 方法1：中序遍历倒序
		 * 二叉搜索树的中序遍历为递增序列
		 * 步骤：
		 * 特例处理：
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
		 * 剑指 Offer 55 - I. 二叉树的深度
		 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
		 * 
		 * 思路：树的遍历总体分为两类：深度优先搜索（DFS），广度优先搜索（BFS）
		 * 常见的DFS：前序遍历，中序遍历， 后序遍历
		 * 常见的BFS：层序遍历
		 * 方法1：后续遍历
		 * 
		 * 方法2：层序遍历
		 * */
		//方法1：后续遍历
		/*
		if(root == null)
			return 0;
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
		*/
		
		//方法2：层序遍历
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
	
	//-------------------------------------------------------------------------------
	
	
	
	public boolean isBalanced(TreeNode root)
	{
		/*
		 * 剑指 Offer 55 - II. 平衡二叉树
		 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
		 * 
		 * 思路：
		 * 方法1：后序遍历+剪枝
		 * 对二叉树做后序，从底至顶返回子树深度，若判定某子树不是平衡树，则剪枝，直接向上返回
		 * recur(root)
		 * 返回值：
		 * 1，当前节点root的左或右子树的深度差<=1，则返回当前子树的深度，即左/右子树深度的最大值+1，max(left,right)+1
		 * 2，当前节点root的左或右子树的深度差<=1，则返回-1，代表此子树不是平衡树。
		 * 终止条件：
		 * 1，root为空：说明越过叶节点
		 * 2，当左/右子树的深度为-1，剪枝
		 * 
		 * 特例处理，假定输入root为空时，返回true
		 * */
		
		return recur(root) != -1;
	}
	private int recur(TreeNode root)
	{
		if(root == null)
			return 0;
		int left = recur(root.left);
		if(left == -1)
			return -1;
		int right = recur(root.right);
		if(right == -1)
			return -1;
		return Math.abs(left-right)<2 ? Math.max(left, right)+1 : -1;
	}
	
	//-------------------------------------------------------------------------------
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		/*
		 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
		 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
		 * 
		 * 思路:
		 * 方法1：两次遍历
		 * 
		 * 方法2：一次遍历
		 * */
		
		//方法1：两次遍历
		/*
		if(root == null || p == null || q == null)
			return null;
		List<TreeNode> pathp = getPath(root, p);
		List<TreeNode> pathq = getPath(root, q);
		TreeNode ancestor = null;
		for(int i = 0; i < pathp.size() && i < pathq.size(); i++)
		{
			if(pathp.get(i) == pathq.get(i))
				ancestor = pathp.get(i);
			else
				break;
		}
		return ancestor;
		*/
		
		//方法2：一次遍历
		if(root == null || p == null || q == null)
			return null;
		TreeNode ancestor = root;
		while(true)
		{
			if(p.val < ancestor.val && q.val < ancestor.val)
				ancestor = ancestor.left;
			else if(p.val > ancestor.val && q.val > ancestor.val)
				ancestor = ancestor.right;
			else
				break;
		}
		return ancestor;
	}
	private List<TreeNode> getPath(TreeNode root, TreeNode target)
	{
		List<TreeNode> path = new ArrayList<>();
		TreeNode node = root;
		while(node != target)
		{
			path.add(node);
			if(target.val > node.val)
				node = node.right;
			else 
				node = node.left;
		}
		path.add(node);
		return path;
	}
	
	
	//------------------------------------------------------------------------------
	public boolean isSubStructure(TreeNode A, TreeNode B)
	{
		/*
		 * 剑指 Offer 26. 树的子结构
		 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
		 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
		 * 
		 * 思路：
		 * 方法1：递归
		 * */
		
		if(A == null || B == null)
			return false;
		if(recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B))
			return true;
		return false;
	} 
	/*private boolean recur(TreeNode A, TreeNode B)
	{
		if(B == null)
			return true;
		if(A == null || A.val != B.val)
			return false;
		return recur(A.left, B.left) && recur(A.right, B.right);
	}*/

	
	//----------------------------------------------------------------------------------
	public boolean validateStackSequences(int[] pushed, int[] popped)
	{
		/*
		 * 剑指 Offer 31. 栈的压入、弹出序列
		 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
		 * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 
		 * 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
		 * 
		 * 思路：
		 * 方法1：模拟
		 * 给定一个压入序列 pushed 和弹出序列 popped，则压入 / 弹出操作的顺序（即排列）是 唯一确定 的
		 * 借用一个辅助栈 stackstack ，模拟 压入 / 弹出操作的排列。根据是否模拟成功，即可得到结果
		 * 
		 * */
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		for(int num : pushed)
		{
			stack.push(num);
			while(!stack.isEmpty() && stack.peek() == popped[i]) 
			{ 
                stack.pop();
                i++;
			}
		}
		return stack.isEmpty();
	}
	
}
