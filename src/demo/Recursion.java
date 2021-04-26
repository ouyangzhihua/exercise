package demo;

import java.util.HashMap;
import java.util.Map;

import demo.BibaryTree.TreeNode;

public class Recursion {
	/*
	 * �ݹ�
	 * 
	 * */
	public double myPow(double x, int n)
	{
		/*
		 * ��ָ Offer 16. ��ֵ�������η�
		 * ʵ�� pow(x, n) �������� x �� n ���ݺ���������x^n��������ʹ�ÿ⺯����ͬʱ����Ҫ���Ǵ������⡣
		 * 
		 * ˼·��
		 * ����1�������ݷ�
		 * �����ݷ��Ƕ��ַ�˼���һ�����֣���nΪż����x^n = (x^2)^(n/2)
		 * ��nΪ������x^n = x(x^2)^(n/2)
		 * λ���㣺λ�����Ч�ʱȳ˳�������Ҫ�ߺܶ�
		 * ��������n/2�൱�ڣ�n>>1
		 * ȡ����n%2�൱�ڣ�n&1
		 * ���裺
		 * 1�������жϣ���x=0,����0�� ��x=1, ����1
		 * 			��n=0,����1�� ��n<0, ���´���
		 * 2����ʼ����
		 * 3��ѭ�����㣺
		 * int�ͣ�4�ֽڣ�1�ֽ�=8λ��int��32λ��1λ��ʶ������31λ��ʾ��ֵ��-2^31~2^31-1����n<0������n=-n��ֵʱ�������
		 * ����long�����
		 * 
		 * ���Ӷȷ�����ʱ�临�Ӷ�O(log2 n)���ռ临�Ӷ�O(1)
		 * */
		double res = 1.0;
		long nl = n;
		if(x==0)	//0��0�η������壬���ﷵ�ص���0
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
		 * ��ָ Offer 62. ԲȦ�����ʣ�µ�����
		 * 0,1,������,n-1��n�������ų�һ��ԲȦ��������0��ʼ��ÿ�δ����ԲȦ��ɾ����m�����֣�ɾ�������һ�����ֿ�ʼ��������
		 * ������ԲȦ��ʣ�µ����һ�����֡����磬0��1��2��3��4��5���������һ��ԲȦ��������0��ʼÿ��ɾ����3�����֣�
		 * ��ɾ����ǰ4������������2��0��4��1��������ʣ�µ�������3
		 * 
		 * ˼·��
		 * ����1���ݹ�
		 * 
		 * ����2��������1д�ɵ�������ʹ��ջ�ռ�
		 * */
		
		//����1���ݹ�
		if(n < 1 || m < 1)
			return -1;
		//return f(n, m);
		
		//����2��������1д�ɵ�������ʹ��ջ�ռ�
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
		 * ��ָ Offer 64. ��1+2+��+n
		 * �� 1+2+...+n ��Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ��ּ������ж���䣨A?B:C����
		 * 
		 * ˼·��
		 * ����1���ݹ�
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
		 * ���Ӷȷ������ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n)
		 * 
		 * 1,��ȷ��ǰroot�ڵ�Ӧ����ʲô��������������
		 * 2�����ݺ�������ݹ�����ӽڵ㣬����ͬ��
		 * �ݹ飺
		 * 1���ݹ麯���Ķ��壨����������Ǹ���ģ������ݵ�ǰroot�ڵ��ؽ���������
		 * 2���ݹ麯�������ı�����ǰ�������������洢�ڵ���������������������洢����
		 * 3���õ������ĵݹ�����Ҫ�����£����������ӽڵ�
		 * 4��base caseҲ�ǵݹ����������left>right
		 * */
		
		int n = preorder.length;
		//�����ϣӳ��
		indexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++)
			indexMap.put(inorder[i], i);
		return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
	}
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
}
