package methods;

import java.util.*;

public class BreathFirstSearch {
	/*
	 * 广度优先搜素：树的层序遍历通常需要用广度优先搜素，需要利用队列将下一层的所有元素先存储起来，然后在对每个节点搜索
	 * BFS 通常借助 队列 的先入先出特性来实现。
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
		 * 剑指 Offer 32 - I. 从上到下打印二叉树
		 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
		 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
		 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
		 * 第三行再按照从左到右的顺序打印，其他行以此类推。
		 * 
		 * 思路：
		 * 方法1：层序遍历+双端队列
		 * 
		 * 方法2：层序遍历 + 双端队列（奇偶层逻辑分离）
		 * 
		 * 方法3：层序遍历 + 倒序
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
		
		//方法2：层序遍历 + 双端队列（奇偶层逻辑分离）
		/*
		Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) deque.add(root);
        while(!deque.isEmpty()) 
        {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) 
            {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if(node.left != null) 
                	deque.addLast(node.left);
                if(node.right != null) 
                	deque.addLast(node.right);
            }
            res.add(tmp);
            if(deque.isEmpty()) break; // 若为空则提前跳出
            // 打印偶数层
            tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) 
            {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if(node.right != null) 
                	deque.addFirst(node.right);
                if(node.left != null) 
                	deque.addFirst(node.left);
            }
            res.add(tmp);
        }
        return res;
		*/
		
		//方法3：层序遍历 + 倒序
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
	
	
	//---------------------------------------------------------------------------------------
	/*
	 * 剑指 Offer 37. 序列化二叉树
	 * 请实现两个函数，分别用来序列化和反序列化二叉树。
	 * */
	public String serialize(TreeNode root)
	{
		if(root == null)
			return "[]";
		Queue<TreeNode> que = new LinkedList<>();
		StringBuilder res = new StringBuilder("[");
		que.add(root);
		while(!que.isEmpty())
		{
			TreeNode node = que.poll();
			if(node != null)
			{
				res.append(node.val + ",");
				que.add(node.left);
				que.add(node.right);
			}
			else
				res.append("null,");
		}
		res.deleteCharAt(res.length()-1);
		res.append("]");
		return res.toString();	
	}
	public TreeNode deserialize(String data)
	{
		if(data.equals("[]")) 
			return null;
	    String[] vals = data.substring(1, data.length() - 1).split(",");
	    TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.add(root);
	    int i = 1;
	    while(!queue.isEmpty()) 
	    {
	    	TreeNode node = queue.poll();
	    	if(!vals[i].equals("null")) 
	    	{
	    		node.left = new TreeNode(Integer.parseInt(vals[i]));
	    		queue.add(node.left);
	    	}
	    	i++;
	    	if(!vals[i].equals("null")) 
	    	{
	    		node.right = new TreeNode(Integer.parseInt(vals[i]));
	    		queue.add(node.right);
	    	}
	    	i++;
	    }
	    return root;
	}



}
