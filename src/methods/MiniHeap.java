package methods;

public class MiniHeap {
	/*
	 * 最小堆：
	 * */
	//---------------------------------------------------------------------------------
	public int nthUglyNumber(int n)
	{
		/*
		 * 剑指 Offer 49. 丑数
		 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
		 * 
		 * 思路：
		 * 方法1：最小堆
		 * 
		 * 方法2：动态规划
		 * 使用方法1时会预先存储较多丑数，可以在进行优化
		 * */
		//方法1：最小堆
		/*
		if(n < 1)
			return -1;
		Set<Long> set = new HashSet<>();
		PriorityQueue<Long> heap = new PriorityQueue<>();
		int[] factor = {2, 3, 5};
		int urgly = 0;
		set.add(1L);
		heap.add(1L);
		for(int i = 0; i < n; i++)
		{
			long cur = heap.poll();
			urgly = (int) cur;
			for(int f:factor)
			{
				long next = f*cur;
				if(set.add(next))
				{
					heap.add(next);
				}
			}
		}
		return urgly;
		*/
		//方法2：动态规划
		if(n < 1)
			return -1;
		int p1 = 1, p2 = 1, p3 = 1;
		int[] dp = new int[n+1];
		dp[1] = 1;
		for(int i = 2; i < n+1; i++)
		{
			int num2 = 2*dp[p1];
			int num3 = 3*dp[p2];
			int num5 = 5*dp[p3];
			dp[i] = Math.min(Math.min(num2, num3), num5);
			if(num2 == dp[i])
			{
				p1++;
			}
			if(num3 == dp[i])
			{
				p2++;
			}
			if(num5 == dp[i])
			{
				p3++;
			}
		}
		return dp[n];
	}
}
