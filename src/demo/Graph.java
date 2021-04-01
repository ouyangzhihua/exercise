package demo;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	public boolean exist(char[][] board, String word)
	{
		/*
		 * 剑指 Offer 12. 矩阵中的路径
		 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
		 * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
		 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
		 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
		 * [["a","b","c","e"],
		 * ["s","f","c","s"],
		 * ["a","d","e","e"]]
		 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
		 * 
		 * 思路：
		 * 方法1：深度优先搜索DFS+剪枝
		 * 剪枝：在搜索中，遇到这条路不可能与目标字符串匹配成功，则应立即返回。
		 * 递归参数：board的行列索引row,col，当前目标字符在word中的索引k
		 * 终止条件：
		 * 1，返回false：行列索引越界 或 当前矩阵元素与目标字符不同 或 当前矩阵元素已3访问过
		 * 2,返回true： k = word.length-1
		 * 步骤：
		 * 1，标记当前矩阵元素，将其修改为空字符
		 * 2，向上下左右搜索
		 * 3，将当前矩阵元素修改为原来的值，即word[k]
		 * 复杂度分析：
		 * 
		 * */
		
		//方法1：深度优先搜索DFS+剪枝
		char[] words = word.toCharArray();
		for(int row = 0; row < board.length; row++)
			for(int col = 0; col < board[0].length; col++)
				if(deepFirstSearch(board, words, row, col, 0))
					return true;
		return false;
	}
	
	public boolean deepFirstSearch(char[][] board, char[] words, int row, int col, int k)
	{
		if(row < 0 || row >=board.length || col < 0 || col >=board[0].length || board[row][col] != words[k])
			return false;
		if(k == words.length-1)
			return true;
		board[row][col] = '\0';	//空字符
		boolean res = deepFirstSearch(board, words, row-1, col, k+1) || deepFirstSearch(board, words, row+1, col, k+1) ||
			  deepFirstSearch(board, words, row, col-1, k+1) || deepFirstSearch(board, words, row, col+1, k+1);
		board[row][col] = words[k];
		return res;
	}
	
	
	public int movingCount(int m, int n, int k)
	{
		/*
		 * 剑指 Offer 13. 机器人的运动范围
		 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
		 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
		 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
		 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
		 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子
		 * 
		 * 思路：
		 * 方法1：广度优先搜索法
		 * 要判断某一格能否到达，只能一格一格走过去
		 * 通过对数取余可以计算数位之和
		 * 步骤：
		 * 复杂度分析：
		 * 时间复杂度：O(mn),空间复杂度O(mn)
		 * 
		 * 方法2：递推
		 * 思路：由于搜索的方向只有向右和向下，因此考虑递推
		 * 用vis[row][col]表示[row][col]是否可达，判断位置[row][col]可达前，先判断是否满足数位条件
		 * 若满足数位条件，[row][col]可达，需要[row-1][col]可达或[row][col-1]可达
		 * 步骤：
		 * 时间复杂度：O(mn),空间复杂度O(mn)
		 * 
		 * 方法3：深度优先搜索
		 * 思路：
		 * 步骤：
		 * 复杂度分析：
		 * */
		
		//方法1：广度优先搜索法
		/*
		if(k == 0)
			return 1;	//当k=0时，避免进入后面不必要的操作，提高性能。不加这一判断结果也正确
		Queue<int[]> que = new LinkedList<int[]>();
		int[] dright = {1, 0};	//向右数组
		int[] ddown = {0, 1};	//向下数组
		boolean[][] vis = new boolean[m][n];
		que.offer(new int[] {0,0});	//将指定的元素添加为此列表的尾部（最后一个元素）。
		vis[0][0] = true;	//机器人起点
		int count = 1;	//累计能到的格子
		while(!que.isEmpty())
		{
			int[] cell = que.poll();	//检索并删除此列表的头（第一个元素）
			int right = cell[0];
			int down = cell[1];
			for(int i = 0; i < 2; i++)
			{
				int nright = right + dright[i];
				int ndown =  down + ddown[i];
				int sumn = getValue(nright) + getValue(ndown);
				if(nright < 0 || nright >=m || ndown < 0 || ndown >= n || vis[nright][ndown] || sumn > k)
					continue;
				que.offer(new int[] {nright, ndown});	//将能到达的格子加入链表
				vis[nright][ndown] = true;	//表示此坐标能到达
				count++;	//格子记数加1
			}
		}
		return count;
		*/
		
		//方法2：递推
		/*
		boolean[][] vis = new boolean[m][n];
		int count = 0;
		vis[0][0] = true;
		//遍历
		for(int row = 0; row < m; row++)
		{
			for(int col = 0; col < n; col++)
			{
				if((row == 0 && col == 0) || getValue(row)+getValue(col) > k)
					continue;
				//边界判断
				if(row-1 >= 0)
					vis[row][col] |= vis[row-1][col];
				if(col-1 >= 0)
					vis[row][col] |= vis[row][col-1];
				count += vis[row][col] ? 1 : 0;
			}
		}
		return count;	
		*/
		
		//方法3：深度优先搜索	
		this.vis = new boolean[m][n];
		this.m = m;
		this.n = n;
		this.k = k;
		return deepFirstSearch(0, 0);
	}
	int m;
	int n;
	int k;
	boolean[][] vis;
	public int deepFirstSearch(int row, int col)
	{
		
		int sumn = getValue(row) + getValue(col);
		if(row < 0 || row >= m || col < 0 || col >= n || vis[row][col] || sumn > k)
			return 0;
		vis[row][col] = true;
		int res = 1 + deepFirstSearch(row+1, col) + deepFirstSearch(row, col+1);
		return res;
		
	}
	
	
	
	
	private int getValue(int number)
	{
		int res = 0;
		while(number != 0)
		{
			res += number % 10;
			number /= 10;
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] ca = {{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}};
		String word = "abcced";
		Graph dfs = new Graph();
		
		System.out.println(dfs.movingCount(1, 1, 0));
	
	}

}
