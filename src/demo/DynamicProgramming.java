package demo;

import java.util.*;

public class DynamicProgramming 
{
	/*
	 * 动态规划：动态规划的一般问题形式就是求最值，求解动态规划的核心问题就是穷举。动态规划的穷举存在重叠子问题。
	 * 
	 * 
	 * */
	public int cuttingRope(int n)
	{
		/*
		 * 剑指 Offer 14- I. 剪绳子
		 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
		 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
		 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * 记f(n)为可能的最大乘积，可以将其分解为多个子问题求解
		 * 将所有子问题的最优解求出，得到整体最优解。
		 * f(2)=1, f(3)=2, 且可证明2(n-2)>n, 3(n-3)>n, 因此将绳子剪到剩下2或3即可。
		 * 步骤：
		 * 复杂度分析：
		 * 
		 * 方法2：贪婪算法
		 * 当n>=5时，可证明2(n-2)>n, 3(n-3)>n，因此我们每一步只需尽量多剪长度为3或2的
		 * 
		 * ----------------------------------------------------
		 * 明确状态：绳子的长度，剪掉多少长度
		 * 定义dp数组：dp[i]表示长度为i的绳子可剪成的最大乘积
		 * 明确选择：长度为i可以剪掉1~i/2长度
		 * base case: dp[2]=2;dp[3]=3; n<0,return -1; n=1,dp[1]=1;
		 * 状态转移：dp[i]=max(1*dp[i-1],2*dp[i-2],...)
		 * */
		//暴力穷举、递归
		if(n <= 1)
			return -1;	//输入检查
		if(n <= 3)
			return n-1;
		return cut(n);
		
		//方法1：动态规划
		/*
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;
		int[] products = new int[n+1];	//
		products[0] = 0;
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		int max = 0;
		for(int i = 4; i <= n; i++)
		{
			max = 0;
			for(int j = 1; j <= i/2; j++)
			{
				int product = products[j] * products[i-j];
				if(product > max)
					max = product;	
			}
			products[i] = max;	//将长度为i时的最大值存到products[i]
		}
		return max;
		*/
		
		//方法2：贪婪算法
		/*
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;	
		else if(n == 4)	//边界值判断
			return 4;
		long max;
		long times = n/3;
		if(n%3 == 1)
			max = (int) (Math.pow(3, times-1) * 4);
		else if(n%3 == 0)
			max = (int) (Math.pow(3, times));
		else
			max = (int) (Math.pow(3, times) * (n - 3 * times));
		return (int) max;
		*/
		
		
		//考虑大数求余问题，快速求余
		/*
		if(n <= 1)
			return -1;	//输入检查
		else if(n <= 3)	//f(2)=1, f(3)=2
			return n-1;	
		else if(n == 4)	//边界值判断
			return 4;
		int max;
		long times = n/3;
		long x = 3;
		long rem =1;
		int p = 1000000007;
		for(long a = times-1; a > 0; a/=2)
		{
			if(a%2 == 1)
				rem = (rem * x) % p;
			x = (x * x) % p;
		}
		if(n%3 == 1)
			max = (int) (rem * 4 % p);
		else if(n%3 == 0)
			max = (int) (rem * 3 % p);
		else
			max = (int) (rem * 3 * 2 % p);
		return max;
		*/
	}
	private int cut(int n)
	{
		if(n <= 4)
			return n;
		int res = 1;
		for(int i = 4; i <= n; i++)
		{
			for(int j = 1; j <= i/2; j++)
			{
				res = Math.max(res, j*cut(i-j));
			}
		}
		return res;
	}
	//-----------------------------------------------------------------------------
	
	public int maxSubArray(int[] nums)
	{
		/*
		 * 剑指 Offer 42. 连续子数组的最大和
		 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * dp[i]记为以num[i]为结尾的和最大的子数组。
		 * dp[i]=dp[i-1]+num[i], 若dp[i-1]<=0,则说明dp[i-1]对dp[i]没有增大的贡献，此时dp[i]=num[i]
		 * 若dp[i-1]>0, 此时dp[i]=dp[i-1]+max(num[i],0)。
		 *
		 * 步骤：
		 * 特例处理：nums=null
		 * */
		if(nums == null)
			return 0;
		int dp = nums[0];
		for(int i = 1; i < nums.length; i++)
		{
			nums[i] += Math.max(nums[i-1], 0);
			dp = Math.max(dp, nums[i]);
		}
		return dp;
	}
	
	
	//--------------------------------------------------------------------------------------
	public int translateNum(int num)
	{
		/*
		 * 剑指 Offer 46. 把数字翻译成字符串
		 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，
		 * 11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * 翻译的方式有两种，1，单独数字翻译；2，两个数字连起来翻译
		 * 记第i个数字结尾的翻译方法有f(i),则f(i)=单独翻译f(i-1)+两个数字连起来翻译f(i-2)
		 * 
		 * 步骤：
		 * 特例处理：num<0;
		 * 将数转换为字符串
		 * */
		//方法1：动态规划
		/*
		if(num < 0)
			return 0;
		String s = String.valueOf(num);
		int f1 = 0, f2 = 0, f = 1;
		for(int i = 0; i < s.length(); i++)
		{
			f2 = f1;
			f1 = f;
			f = 0;
			f += f1;
			if(i == 0)
				continue;
			String pre = s.substring(i-1, i+1);	//截取i-1~i
			if(pre.compareTo("25") <= 0 && pre.compareTo("10")>=0)
			{
				f += f2;
			}
		}
		return f;
		*/
		/*
		if(num < 0)
			return 0;
		String s = String.valueOf(num);
		int f1 = 1, f2 = 1, f = 1;
		for(int i = 1; i < s.length(); i++)
		{
			String pre = s.substring(i-1, i+1);
			if(pre.compareTo("25") <= 0 && pre.compareTo("10")>=0)
				f = f1 + f2;
			f2 = f1;
			f1 = f;
		}
		return f;
		*/
		//数字求余，使用字符串存储数字时仍会占用一定空间，利用取余将各数位求出来
		if(num < 0)
			return 0;
		int f1 = 1, f2 = 1, f = 1;
		int x = num % 10;
		int y;
		while(num != 0)
		{
			num /= 10;
			y = num % 10;
			int temp = y*10 + x;
			x = y;
			if(temp >=10 && temp <= 25)
				f = f1+f2;
			f2 = f1;
			f1 = f;
		}
		return f;
	}
	

	//----------------------------------------------------------------------------------
	public int maxValue(int[][] grid)
	{
		/*
		 * 剑指 Offer 47. 礼物的最大价值
		 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
		 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
		 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * */
		/*
		if(grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(i == 0 && j == 0)
					continue;
				else if(i == 0)
					grid[i][j] += grid[i][j-1];
				else if(j == 0)
					grid[i][j] += grid[i-1][j];
				else
					grid[i][j] += Math.max(grid[i-1][j], grid[i][j-1]);
			}
		}
		return grid[grid.length-1][grid[0].length-1];
		*/
		//优化
		if(grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		for(int i = 1; i < grid.length; i++)
			grid[i][0] += grid[i-1][0];
		for(int j = 1; j < grid[0].length; j++)
			grid[0][j] += grid[0][j-1];
		for(int i = 1; i < grid.length; i++)
		{
			for(int j = 1; j < grid[0].length; j++)
			{
				grid[i][j] += Math.max(grid[i-1][j], grid[i][j-1]);
			}
		}
		return grid[grid.length-1][grid[0].length-1];
	}
	
	
	//----------------------------------------------------------------------------------
	public int lengthOfLongestSubstring(String s)
	{
		/*
		 * 剑指 Offer 48. 最长不含重复字符的子字符串
		 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
		 * 
		 * 方法1：动态规划+哈希表
		 * 状态定义：dp(j)记为以s[j]结尾的最长不重复字符串的长度
		 * 状态转移：固定右边界j，字符s[j]左边最近的相同字符为s[i]，s[i]=s[j]
		 * 1，当i<0，即s[j]左边没有与其相同的字符
		 * 2，当dp(j-1)<j-i，说明s[i]在子字符串dp(j-1)的区间之外，dp(j)=dp(j-1)+1
		 * 3，当dp(j-1)>=j-i，说明s[i]在子字符串dp(j-1)的区间之中，dp(j)=j-i
		 * 返回值：max(dp)
		 * 
		 * 用哈希表统计个字符最后一次出现的位置。
		 * 
		 * 步骤：
		 * 
		 * 方法2：动态规划+线性遍历
		 * 从j-1位置倒序查找s[j]左边最近的相同字符
		 * 
		 * 方法3：双指针+哈希表
		 * */
		/*
		if(s == null || s.length() <= 0)
			return 0;
		Map<Character, Integer> map = new HashMap<>();
		int dp = 0, res = 0;
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			int i = map.getOrDefault(ch, -1);	//获取索引
			map.put(ch, j);
			if(dp < j - i)
			{
				dp = dp + 1;
			}
			else
			{
				dp = j - i;
			}
			res = Math.max(dp, res);
		}
		return res;
		*/
		//方法2：动态规划+线性遍历
		/*
		if(s == null || s.length() <= 0)
			return 0;
		int dp = 0, res = 0;
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			int i = j - 1;
			while(i >= 0 && s.charAt(i) != ch)
			{
				i--;
			}
			if(dp < j - i)
			{
				dp = dp + 1;
			}
			else
			{
				dp = j - i;
			}
			res = Math.max(dp, res);
		}
		return res;
		*/
		//方法3：双指针+哈希表
		if(s == null || s.length() <= 0)
			return 0;
		int i = -1, res = 0;
		Map<Character, Integer> map = new HashMap<>();
		for(int j = 0; j < s.length(); j++)
		{
			char ch = s.charAt(j);
			if(map.containsKey(ch))
			{
				i = Math.max(i, map.get(ch));
			}
			map.put(ch, j);
			res = Math.max(res, j-i);
		}
		return res;
	}
	
	
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
	
	
	//-------------------------------------------------------------------------------------
	public int maxProfit(int[] prices)
	{
		/*
		 * 剑指 Offer 63. 股票的最大利润
		 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
		 * 
		 * 思路：
		 * 方法1：动态规划
		 * 状态定义：dp(i)记为第i天能获得的最大利润
		 * 状态转移：初始化指针buy
		 * 1，若prices[i]-prices[buy]>dp(i-1)，dp(i)=prices[i]-prices[buy]
		 * 2，若prices[i]-prices[buy]<=dp(i-1)，dp(i)=dp(i-1)
		 * 返回值：dp
		 * 
		 * 方法2：暴力
		 * 
		 * 方法3：一次遍历
		 * 
		 * */
		/*
		if(prices == null || prices.length == 0)
			return 0;
		int buy = 0;
		int dp = 0;
		for(int i = 0; i < prices.length; i++)
		{
			int profit = prices[i]-prices[buy];
			if(prices[i] < prices[buy])
			{
				buy = i;
			}
			if(profit > dp)
			{
				dp = profit;
			}
		}
		return dp;
		*/
		//优化
		/*
		if(prices == null || prices.length == 0)
			return 0;
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price:prices)
        {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price-cost);
        }
        return profit;
        */
        //方法2：暴力法
		/*
		if(prices == null || prices.length == 0)
			return 0;
		int profit = 0;
		for(int i = 0; i < prices.length-1; i++)
		{
			for(int j = i+1; j < prices.length; j++)
			{
				profit = Math.max(profit, prices[j]-prices[i]);
			}
		}
		return profit;
		*/
		//方法3：一次遍历
		if(prices == null || prices.length == 0)
			return 0;
		int profit = 0;
		int buy = prices[0];
		for(int i = 1; i < prices.length; i++)
		{
			if(prices[i] < buy)
			{
				buy = prices[i];
			}
			else if(prices[i]-buy > profit)
			{
				profit = prices[i]-buy;
			}
		}
		return profit;
	}
	
	
	//----------------------------------------------------------------------------------------
	public int jumpFloorII(int target)
	{
		/*
		 * 跳台阶扩展问题
		 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
		 * 
		 * 思路：
		 * 明确状态：目标台阶数、当前所在位置
		 * 定义dp函数含义：dp[i]表示跳上i级台阶最多的跳法
		 * 确定选择：当前位置可以选择跳1级~n级
		 * 状态转移：dp[i]=dp[i-1]+dp[i-2]+...+dp[0];
		 * base case: 当n=0，则0种跳法，n<0,无解返回-1
		 * 
		 * 第一步：暴力穷举、递归
		 * 第二步：dp数组记录
		 * 第三步：优化：
		 * 发现：dp[i]=dp[i-1]+dp[i-2]+...+dp[0]
		 *     dp[i-1]=dp[i-2]+dp[i-3]+...+dp[0]
		 *  有：dp[i]=2*dp[i-1]
		 * */
		//暴力穷举
		/*
        if(target < 0)
            return -1;
        if(target == 0 || target == 1)
        	return 1;
        int res = 0;
        for(int i = 2; i <= target; i++)
        {
        	res = 0;
            for(int j = 0; j < i; j++)
            {
            	res += jumpFloorII(j);
            } 
        }
        return res;
        */
        //dp数组
		/*
        if(target < 0)
            return -1;
        int[] dp = new int[target+1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= target; i++)
        {
            for(int j = 0; j < i; j++)
            {
            	dp[i] += dp[j];
            }           
        }
        return dp[target];
        */
        //优化：
        if(target < 0)
            return -1;
        if(target == 0 || target == 1)
        	return 1;
        int res = 1;
        for(int i = 1; i < target; i++)
        {
        	res = res << 1;
        }
        return res;
        //return (int) Math.pow(2, target-1);
	}
}
