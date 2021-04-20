package methods;

public class MiniHeap {
	/*
	 * ��С�ѣ�
	 * */
	//---------------------------------------------------------------------------------
	public int nthUglyNumber(int n)
	{
		/*
		 * ��ָ Offer 49. ����
		 * ���ǰ�ֻ���������� 2��3 �� 5 ��������������Ugly Number�����󰴴�С�����˳��ĵ� n ��������
		 * 
		 * ˼·��
		 * ����1����С��
		 * 
		 * ����2����̬�滮
		 * ʹ�÷���1ʱ��Ԥ�ȴ洢�϶�����������ڽ����Ż�
		 * */
		//����1����С��
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
		//����2����̬�滮
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
