package demo;
import java.util.*;

public class Stack 
{
	
	class CQueue 
	{
		/* 剑指 Offer 09. 用两个栈实现队列
		 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
		 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
		 * 
		 * 思路：
		 * */
		
		Deque<Integer> stack1;
    	Deque<Integer> stack2; 
	    public CQueue() 
	    {
	    	stack1 = new LinkedList<Integer>();
	    	stack1 = new LinkedList<Integer>();
	    }
	    
	    public void appendTail(int value) 
	    {
	    	stack1.push(value);
	    }
	    
	    public int deleteHead() 
	    {
	    	while(!stack1.isEmpty())
	    		stack2.push(stack1.pop());
	    	if(stack2.isEmpty())
	    		return -1;
	    	else 
	    		return stack2.pop();
	    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
