package demo;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	public boolean exist(char[][] board, String word)
	{
		/*
		 * ��ָ Offer 12. �����е�·��
		 * �����һ�������������ж���һ���������Ƿ����һ������ĳ�ַ��������ַ���·����
		 * ·�����ԴӾ����е�����һ��ʼ��ÿһ�������ھ����������ҡ��ϡ����ƶ�һ��
		 * ���һ��·�������˾����ĳһ����ô��·�������ٴν���ø��ӡ�
		 * ���磬�������3��4�ľ����а���һ���ַ�����bfce����·����·���е���ĸ�üӴֱ������
		 * [["a","b","c","e"],
		 * ["s","f","c","s"],
		 * ["a","d","e","e"]]
		 * �������в������ַ�����abfb����·������Ϊ�ַ����ĵ�һ���ַ�bռ���˾����еĵ�һ�еڶ�������֮��·�������ٴν���������ӡ�
		 * 
		 * ˼·��
		 * ����1�������������DFS+��֦
		 * ��֦���������У���������·��������Ŀ���ַ���ƥ��ɹ�����Ӧ�������ء�
		 * �ݹ������board����������row,col����ǰĿ���ַ���word�е�����k
		 * ��ֹ������
		 * 1������false����������Խ�� �� ��ǰ����Ԫ����Ŀ���ַ���ͬ �� ��ǰ����Ԫ����3���ʹ�
		 * 2,����true�� k = word.length-1
		 * ���裺
		 * 1����ǵ�ǰ����Ԫ�أ������޸�Ϊ���ַ�
		 * 2����������������
		 * 3������ǰ����Ԫ���޸�Ϊԭ����ֵ����word[k]
		 * ���Ӷȷ�����
		 * 
		 * */
		
		//����1�������������DFS+��֦
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
		board[row][col] = '\0';	//���ַ�
		boolean res = deepFirstSearch(board, words, row-1, col, k+1) || deepFirstSearch(board, words, row+1, col, k+1) ||
			  deepFirstSearch(board, words, row, col-1, k+1) || deepFirstSearch(board, words, row, col+1, k+1);
		board[row][col] = words[k];
		return res;
	}
	
	
	public int movingCount(int m, int n, int k)
	{
		/*
		 * ��ָ Offer 13. �����˵��˶���Χ
		 * ������һ��m��n�еķ��񣬴����� [0,0] ������ [m-1,n-1] ��
		 * һ�������˴����� [0, 0] �ĸ��ӿ�ʼ�ƶ�����ÿ�ο��������ҡ��ϡ����ƶ�һ�񣨲����ƶ��������⣩��
		 * Ҳ���ܽ�������������������λ֮�ʹ���k�ĸ��ӡ�
		 * ���磬��kΪ18ʱ���������ܹ����뷽�� [35, 37] ����Ϊ3+5+3+7=18��
		 * �������ܽ��뷽�� [35, 38]����Ϊ3+5+3+8=19�����ʸû������ܹ�������ٸ�����
		 * 
		 * ˼·��
		 * ����1���������������
		 * Ҫ�ж�ĳһ���ܷ񵽴ֻ��һ��һ���߹�ȥ
		 * ͨ������ȡ����Լ�����λ֮��
		 * ���裺
		 * ���Ӷȷ�����
		 * ʱ�临�Ӷȣ�O(mn),�ռ临�Ӷ�O(mn)
		 * 
		 * ����2������
		 * ˼·�����������ķ���ֻ�����Һ����£���˿��ǵ���
		 * ��vis[row][col]��ʾ[row][col]�Ƿ�ɴ�ж�λ��[row][col]�ɴ�ǰ�����ж��Ƿ�������λ����
		 * ��������λ������[row][col]�ɴ��Ҫ[row-1][col]�ɴ��[row][col-1]�ɴ�
		 * ���裺
		 * ʱ�临�Ӷȣ�O(mn),�ռ临�Ӷ�O(mn)
		 * 
		 * ����3�������������
		 * ˼·��
		 * ���裺
		 * ���Ӷȷ�����
		 * */
		
		//����1���������������
		/*
		if(k == 0)
			return 1;	//��k=0ʱ�����������治��Ҫ�Ĳ�����������ܡ�������һ�жϽ��Ҳ��ȷ
		Queue<int[]> que = new LinkedList<int[]>();
		int[] dright = {1, 0};	//��������
		int[] ddown = {0, 1};	//��������
		boolean[][] vis = new boolean[m][n];
		que.offer(new int[] {0,0});	//��ָ����Ԫ�����Ϊ���б��β�������һ��Ԫ�أ���
		vis[0][0] = true;	//���������
		int count = 1;	//�ۼ��ܵ��ĸ���
		while(!que.isEmpty())
		{
			int[] cell = que.poll();	//������ɾ�����б��ͷ����һ��Ԫ�أ�
			int right = cell[0];
			int down = cell[1];
			for(int i = 0; i < 2; i++)
			{
				int nright = right + dright[i];
				int ndown =  down + ddown[i];
				int sumn = getValue(nright) + getValue(ndown);
				if(nright < 0 || nright >=m || ndown < 0 || ndown >= n || vis[nright][ndown] || sumn > k)
					continue;
				que.offer(new int[] {nright, ndown});	//���ܵ���ĸ��Ӽ�������
				vis[nright][ndown] = true;	//��ʾ�������ܵ���
				count++;	//���Ӽ�����1
			}
		}
		return count;
		*/
		
		//����2������
		/*
		boolean[][] vis = new boolean[m][n];
		int count = 0;
		vis[0][0] = true;
		//����
		for(int row = 0; row < m; row++)
		{
			for(int col = 0; col < n; col++)
			{
				if((row == 0 && col == 0) || getValue(row)+getValue(col) > k)
					continue;
				//�߽��ж�
				if(row-1 >= 0)
					vis[row][col] |= vis[row-1][col];
				if(col-1 >= 0)
					vis[row][col] |= vis[row][col-1];
				count += vis[row][col] ? 1 : 0;
			}
		}
		return count;	
		*/
		
		//����3�������������	
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
