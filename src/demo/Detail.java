package demo;

public class Detail 
{
	
	StringBuilder res; //创建一个字符串集
	int n;	//数字位数
	int start;	//开始固定位
	int numberOf9 = 0;	//记录数字9的个数
	char[] num;	//字符数组存数字
	char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};	//循环的字符数组
	public String printNumbers(int n)
	{
		 /*
		  * 剑指 Offer 17. 打印从1到最大的n位数
		  * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
		  * 
		  * 思路：递归
		  * n与最大的打印数end: end = 10^3 - 1
		  * 大数越界问题：
		  * 1，当n很大时，int或long可能均会溢出，要用字符串String类型，一个字符表示一位数。
		  * 2，字符串的进位效率很低，生成的列表实际上是n位的0~9的全排列，可以用递归生成
		  * 
		  * 
		  * */
		this.n = n;
		res = new StringBuilder();
		num = new char[n];
		start = n - 1;
		fullPermu(0);
		res.deleteCharAt(res.length()-1);	//删除最后一个逗号,字符串集的长度需要在length后加()
		return res.toString();	//转换位字符串输出
	}
		
	private void fullPermu(int m)
	{
		if(m == n)
		{
			String s = String.valueOf(num).substring(start);
			if(!s.equals("0"))
				res.append(s + "\n");
			if(n-start == numberOf9)
				start--;
			return;		//退出该方法
		}
		
		for(char numChar:loop)
		{
			if(numChar == '9')
				numberOf9++;
			num[m] = numChar;	//每一位遍历0~9数字
			fullPermu(m+1);		//固定下一位，然后遍历
		}
		numberOf9--;	//在回溯前恢复numberOf9
	}
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Detail d = new Detail();
		String s = d.printNumbers(5);
		System.out.print(s);
		
	}
	 	 
}
