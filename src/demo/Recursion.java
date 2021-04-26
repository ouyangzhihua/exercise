package demo;

import java.util.HashMap;
import java.util.Map;

import demo.BibaryTree.TreeNode;

public class Recursion {
	/*
	 * 递归
	 * 
	 * */
	public double myPow(double x, int n)
	{
		/*
		 * 剑指 Offer 16. 数值的整数次方
		 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
		 * 
		 * 思路：
		 * 方法1：快速幂法
		 * 快速幂法是二分法思想的一个体现，当n为偶数：x^n = (x^2)^(n/2)
		 * 当n为奇数：x^n = x(x^2)^(n/2)
		 * 位运算：位运算的效率比乘除和求余要高很多
		 * 向下整除n/2相当于，n>>1
		 * 取余数n%2相当于，n&1
		 * 步骤：
		 * 1，输入判断：若x=0,返回0； 若x=1, 返回1
		 * 			若n=0,返回1； 若n<0, 重新处理
		 * 2，初始化：
		 * 3，循环计算：
		 * int型：4字节，1字节=8位，int：32位，1位标识正负，31位表示数值，-2^31~2^31-1，当n<0，进行n=-n赋值时可能溢出
		 * 故用long性替代
		 * 
		 * 复杂度分析：时间复杂度O(log2 n)，空间复杂度O(1)
		 * */
		double res = 1.0;
		long nl = n;
		if(x==0)	//0的0次方无意义，这里返回的是0
			return 0;
		if(nl < 0)
		{
			x = 1/x;
			nl = -nl;
		}
		while(nl > 0)
		{
			if((nl&1) == 1)
				res = x * res;
			x *= x;
			nl >>= 1;	
		}
		return res;
	}
	
	//----------------------------------------------------------------------------------------
	
	public int lastRemaining(int n, int m)
	{
		/*
		 * 剑指 Offer 62. 圆圈中最后剩下的数字
		 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
		 * 求出这个圆圈里剩下的最后一个数字。例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
		 * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3
		 * 
		 * 思路：
		 * 方法1：递归
		 * 
		 * 方法2：将方法1写成迭代避免使用栈空间
		 * */
		
		//方法1：递归
		if(n < 1 || m < 1)
			return -1;
		//return f(n, m);
		
		//方法2：将方法1写成迭代避免使用栈空间
		int f = 0;
		for(int i = 2; i != n+1; i++)
		{
			f = (m+f) % i;
		}
		return f;
	}
	private int f(int n, int m)
	{
		if(n == 1)
			return 0;
		int x = f(n-1, m);
		return (m+x) % n;
	}
	
	
	//---------------------------------------------------------------------------------------
	public int sumNums(int n)
	{
		/*
		 * 剑指 Offer 64. 求1+2+…+n
		 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
		 * 
		 * 思路：
		 * 方法1：递归
		 * */
		boolean x = n > 1 && (n += sumNums(n-1)) > 0;
		return n;
	}
	
	
	//----------------------------------------------------------------------------
	class TreeNode 
	{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}	
	private Map<Integer, Integer> indexMap;
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
		 * 复杂度分析：空间复杂度O(n)，时间复杂度O(n)
		 * 
		 * 1,明确当前root节点应该做什么：构建左右子树
		 * 2，根据函数定义递归调用子节点，做相同事
		 * 递归：
		 * 1，递归函数的定义（即这个函数是干嘛的）：根据当前root节点重建左右子树
		 * 2，递归函数参数的变量：前序遍历中序遍历存储节点数组的索引，不变量：存储数组
		 * 3，得到函数的递归结果后要做的事：建立左右子节点
		 * 4，base case也是递归结束条件：left>right
		 * */
		
		int n = preorder.length;
		//构造哈希映射
		indexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++)
			indexMap.put(inorder[i], i);
		return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
	}
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
}
