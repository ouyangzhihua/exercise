package demo;

public class ReplaceString
{
	/*
	 * 剑指 Offer 05. 替换空格
	 * 实现一个函数，把字符串 s 中的每个空格替换成"%20"。
	 * 
	 * 思路：
	 * 方法1：使用Java内部函数
	 * 步骤：
	 * 复杂性分析：
	 * 
	 * 方法2：遍历添加
	 * 思路：Java中字符串被设计为不可变的类型，无法直接修改某一位字符，需要新建字符串
	 * 步骤：初始化StringBuilder res
	 * 遍历字符串每个字符c， 若c为空格 添加%20，不为空格，添加c
	 * 
	 * 复杂性分析：时间复杂度O(n)，空间复杂度O(n)
	 * 
	 * 思路：
	 * 方法3：使用字符数组
	 * 步骤：
	 * 复杂性分析：时间复杂度O(n)，空间复杂度O(n)
	 * */
	
	
	public String replaceSpace(String s)
	{
		/*
		//方法1：使用Java内部函数
		String regex = " ";
		String res = s.replaceAll(regex, "%20");
		return res;
		*/
		
		/*
		//方法2：遍历添加
		StringBuilder res = new StringBuilder();
		for(Character c:s.toCharArray())
		{
			if(c == ' ' )
				res.append("%20");
			else
				res.append(c);		
		}
		return res.toString();
		*/
		
		//方法3：使用字符数组
		int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;

	}
	
	public static void main(String[] args)
	{
		String s = "We are happy.";
		ReplaceString str = new ReplaceString();
		String s1 = str.replaceSpace(s);
		System.out.println(s1);
	}
}
