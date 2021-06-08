package demo;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args)
	{
		
		Map<Character, Character> dic = new HashMap<>();
		
			dic.put('(', ')');
			dic.put('[', ']');
			dic.put('{', '}');
		
		System.out.println(dic);
	}
}
