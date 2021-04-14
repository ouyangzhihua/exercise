package methods;

import java.util.*;

public class BreathFirstSearch {
	/*
	 * ����������أ����Ĳ������ͨ����Ҫ�ù���������أ���Ҫ���ö��н���һ�������Ԫ���ȴ洢������Ȼ���ڶ�ÿ���ڵ�����
	 * BFS ͨ������ ���� �������ȳ�������ʵ�֡�
	 * */
	
	class TreeNode 
	{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public int[] levelOrder(TreeNode root)
	{
		/*
		 * ��ָ Offer 32 - I. ���ϵ��´�ӡ������
		 * ���ϵ��´�ӡ����������ÿ���ڵ㣬ͬһ��Ľڵ㰴�մ����ҵ�˳���ӡ��
		 * 
		 * */
		if(root == null)
			return new int[0];
		List<Integer> res = new ArrayList<>();
		Queue<TreeNode> que = new LinkedList<>();
		que.offer(root);
		while(!que.isEmpty())
		{
			for(int i = que.size(); i > 0; i--)
			{
				TreeNode node = que.poll();
				res.add(node.val);
				if(node.left != null)
					que.offer(node.left);
				if(node.right != null)
					que.offer(node.right);
			}
		}
		int[] arr = new int[res.size()];
		for(int i = 0; i < res.size(); i++)
		{
			arr[i] = res.get(i);
		}
		return arr;
	}
	
	
	//-----------------------------------------------------------------------------------
	public List<List<Integer>> levelOrder2(TreeNode root) 
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
		if(root == null)
			que.offer(root);
		while(!que.isEmpty())
		{
			List<Integer> temp = new ArrayList<>();
			for(int i = que.size(); i > 0; i--)
			{
				TreeNode node = que.poll();
				temp.add(node.val);
				if(node.left != null)
					que.offer(node.left);
				if(node.right != null)
					que.offer(node.right);
			}
			res.add(temp);
		}
		return res;
	}
	
	
	//----------------------------------------------------------------------------------------
	public List<List<Integer>> levelOrder3(TreeNode root)
	{
		/*
		 * ��ָ Offer 32 - III. ���ϵ��´�ӡ������ III
		 * ��ʵ��һ����������֮����˳���ӡ������������һ�а��մ����ҵ�˳���ӡ���ڶ��㰴�մ��ҵ����˳���ӡ��
		 * �������ٰ��մ����ҵ�˳���ӡ���������Դ����ơ�
		 * 
		 * ˼·��
		 * ����1���������+˫�˶���
		 * 
		 * ����2��������� + ˫�˶��У���ż���߼����룩
		 * 
		 * ����3��������� + ����
		 * */
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> que = new LinkedList<>();
		if(root != null)
			que.offer(root);
		while(!que.isEmpty())
		{
			LinkedList<Integer> temp = new LinkedList<>();
			for(int i = que.size(); i > 0; i--)
			{
				TreeNode node = que.poll();
				if((res.size() & 1) == 0)
					temp.addLast(node.val);
				else
					temp.addFirst(node.val);
				if(node.left != null)
					que.offer(node.left);
				if(node.right != null)
					que.offer(node.right);
			}
			res.add(temp);
		}
		return res;
		
		//����2��������� + ˫�˶��У���ż���߼����룩
		/*
		Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) deque.add(root);
        while(!deque.isEmpty()) 
        {
            // ��ӡ������
            List<Integer> tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) 
            {
                // �������Ҵ�ӡ
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // ������Ҽ����²�ڵ�
                if(node.left != null) 
                	deque.addLast(node.left);
                if(node.right != null) 
                	deque.addLast(node.right);
            }
            res.add(tmp);
            if(deque.isEmpty()) break; // ��Ϊ������ǰ����
            // ��ӡż����
            tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) 
            {
                // ���������ӡ
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // ���Һ�������²�ڵ�
                if(node.right != null) 
                	deque.addFirst(node.right);
                if(node.left != null) 
                	deque.addFirst(node.left);
            }
            res.add(tmp);
        }
        return res;
		*/
		
		//����3��������� + ����
		/*
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) 
        {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) 
            {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) 
                	queue.add(node.left);
                if(node.right != null) 
                	queue.add(node.right);
            }
            if(res.size() % 2 == 1) 
            	Collections.reverse(tmp);
            res.add(tmp);
        }
        return res;
		*/
	}
}
