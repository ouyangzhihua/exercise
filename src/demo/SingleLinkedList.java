/*
 *单向链表
 * 
 */

package demo;
import java.io.*;
import java.util.*;

public class SingleLinkedList
{
	class Node	//default类
	{
		String name;
		int score;
		int np;
		Node next;
		public Node(int np, String name, int score)
		{
			this.np = np;
			this.name = name;
			this.score = score;
			this.next = null;
		}
	}
	
	private Node first;	//创建节点
	private Node last;
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public void insertNode(int np, String name, int score)
	{
		Node newNode = new Node(np, name, score);
		if(this.isEmpty())
		{
			first = newNode;
			last = newNode;
		}
		else
		{
			last.next = newNode;
			last = newNode;
		}
	}
	
	//insertNode方法重载
	public void insertNode(Node inNo)
	{
		Node temp;
		Node newNode;
		if(this.isEmpty())
		{
			first = inNo;
			last = inNo;
		}
		else
		{
			if(inNo.next == first)
			{
				inNo.next = first;
				first = inNo;
			}
			else if(inNo.next == last.next)
			{
				last.next = inNo;
				last = inNo;
			}
			else
			{
				newNode = first;
				temp = first;
				while(inNo.next != newNode.next)
				{
					temp = newNode;
					newNode = newNode.next;
				}
				temp.next = inNo;
				inNo.next = newNode;
			}
		}
	}
	
	public void deleteNode(Node delNode)
	{
		Node newNode;
		//Node temp;
		if(first.np == delNode.np)
		{
			first = first.next;
		}
		else if(last.np == delNode.np)
		{
			newNode = first;
			while(newNode.next != last)
			{
				newNode = newNode.next;
			}
			newNode.next = null;
			//newNode.next = last.next;
			newNode = last;
		}
		else
		{
			newNode = first;
			//temp = first;
			while(newNode.next != delNode)
			{
				newNode = newNode.next;
			}
			newNode.next = delNode.next;
		}
	}
	
	public void print()
	{
		Node current = first;
		while(current != null)
		{
			//链表的打印为什么是反的？
			System.out.println("["+current.np+" "+current.name+" "+current.score+"]");
			current = current.next;
		}
	}
	
	
	//链表的反转
	public void reverseLinkedList()
	{
		Node current = first;
		Node before = null;
		while(current != null)
		{
			last = before;
			before = current;
			current = current.next;
			before.next = last;
		}
		first = before;
		
	}
	
	//链表的级联：两个或以上链表的连接
	public void concLinkedList(SingleLinkedList head1,SingleLinkedList head2)
	{
		SingleLinkedList temp = head1;
		while(temp.last.next != null)
			temp.last = temp.last.next;
		temp.last.next  = head2.first;	
	}
	
	public static void main(String[] args) throws IOException
	{
		//插入链表节点
		/*
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("input students' total：");
		int total = Integer.parseInt(buf.readLine());
		System.out.println("input " + total + " students' data：");
		SingleLinkedList list = new SingleLinkedList();
		for(int i = 1; i < total+1; i++)	//添加数据，建立链表
		{
			System.out.print("input "+i+"th"+" student's name：");
			String name = buf.readLine();
			System.out.print("input "+i+"th"+" student's np：");
			int np = Integer.parseInt(buf.readLine());
			System.out.print("input "+i+"th"+" student's score：");
			int score = Integer.parseInt(buf.readLine());
			list.insertNode(np, name, score);
			System.out.println("------------");
		}
		
		list.print();
		*/
		
		//删除链表节点
		/*
		BufferedReader buf;
		Random rand=new Random();
		buf=new BufferedReader(new InputStreamReader(System.in));
		SingleLinkedList list =new SingleLinkedList();
		int i,j,findnp=0,data[][]=new int[12][10];
		String name[]=new String[] {"Allen","Scott","Marry","Jon","Mark","Ricky","Lisa","Jasica","Hanson","Amy","Bob","Jack"};
		System.out.println("学号   成绩    学号    成绩    学号    成绩    学号   成绩\n ");
	    for (i=0;i<12;i++)
	    {
		   data[i][0]=i+1;
		   data[i][1]=(Math.abs(rand.nextInt(50)))+50;
		   list.insertNode(data[i][0], name[i], data[i][1]);
	    }
	    for (i=0;i<3;i++)
	    {
		   for(j=0;j<4;j++)
			   System.out.print("["+data[j*3+i][0]+"]  ["+data[j*3+i][1]+"]  ");
		   System.out.println();
	    }
		while(true)
		{
			System.out.print("input student's np needed to delete,end with -1:");
			findnp = Integer.parseInt(buf.readLine());
			if(findnp == -1)
				break;
			else
			{
				Node current = list.new Node(list.first.np, list.first.name, list.first.score);
				current.next = list.first.next;
				while(current.np != findnp)
					current = current.next;
				list.deleteNode(current);
			}
			list.print();
		}
		*/
		
		//反转打印链表
		Random rand=new Random();
		SingleLinkedList list =new SingleLinkedList();
		int i,data[][]=new int[12][10];
		String name[]=new String[] {"Allen","Scott","Marry","Jon","Mark","Ricky","Lisa","Jasica","Hanson","Amy","Bob","Jack"};
	    for (i=0;i<12;i++)
	    {
		   data[i][0]=i+1;
		   data[i][1]=(Math.abs(rand.nextInt(50)))+50;
		   list.insertNode(data[i][0], name[i], data[i][1]);
	    }
	    list.print();
	    list.reverseLinkedList();
		System.out.println("reversed linkedlist:");
		list.print();
		
	}
}



