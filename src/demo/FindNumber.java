/* 
�����ҵ������е�ĳ����
˼·��
���裺
*/
package demo;
import java.util.*;

/*����
 * ��ָoffer03���ҳ��������ظ������֡�
 * ��һ������Ϊ n ������ nums ����������ֶ��� 0��n-1 �ķ�Χ�ڡ�
 * ������ĳЩ�������ظ��ģ�����֪���м��������ظ��ˣ�Ҳ��֪��ÿ�������ظ��˼��Ρ����ҳ�����������һ���ظ������֡�
 * 
 * ˼·��
 * ����1����������
 * ֻҪ�ҵ������ظ�2�ε���
 * �������飬ÿ�ζ�������ǰ������Ƚ��Ƿ����
 * �ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n^2)
 * ----------------------------
 * 
 * ����2����ϣ����
 * ��ϣ����ɢ�У���ͨ���ض���ѧ����������������������ļ�ֵת���ɶ�Ӧ�����ݴ洢��ַ
 * ˼·���������飬�����ݴ����ϣ���ϣ������ܴ�����˵���������Ѿ�������������ҵ��ظ�����
 * �ռ临�Ӷ�O(n)��ʱ�临�Ӷ�O(n)
 * -----------------------------
 * 
 * ����3��ԭ���û�
 * ˼·�����鳤��Ϊn,ֵ��0��n-1 �ķ�Χ�ڣ���û���ظ���������ÿ���������Լ�������ֵ��ȡ�
 * ����������֮��ֵ������ֵ���ȵľ����ظ���
 * ���裺�������飬��ֵ������ֵ���ȣ��͵�ǰֵ��
 * 
 * 
 * */

public class FindNumber 
{
	//�ҳ��������ظ�������
	public int findRepeatNumber(int[] nums)
	{
		/*
		 * ����1����������
		 * 
		int repeat = -1;
		OUT:
		while(nums !=null)
		{
			for(int i = 0; i < nums.length; i++)
			{
				for(int j = 0; j < i; j++)
				{
					if(nums[i] == nums[j])
					{
						repeat = nums[i];
						break OUT;
					}
				}
			}
			break;
		}
		return repeat;
		*/
		
		//����2����ϣ����
		int repeat = -1;
		Set<Integer> set = new HashSet<Integer>();
		for(int num:nums)
		{
			if(!set.add(num))
			{
				repeat = num;
				break;
			}	
		}
		return repeat;
	}
	

	
	public static void main(String[] args)
	{
		int[] arr = {13,2,-9,2,1,5,4};
		FindNumber matrix = new FindNumber();
		int num = matrix.findRepeatNumber(arr);
		System.out.println(num);
	}
	
}


