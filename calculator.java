//�Զ�����30���������㣬�������������������Ŀ�����ظ����ɶ���������
package calculator;
import java.util.*;

public class calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���������λ100���ڵ���
        int a,b;//���������Ϊ���ӱȷ�ĸС��
        //��������������+��-��*��/��
        char[] c={'+','-','*','/'};
        //���������������������������
        int[] e={1,2};
        System.out.println("��������Ҫ������Ŀ����Ŀ��");
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        System.out.println("���ɵ���Ŀ���£�");
        Random random=new Random();
        for(int i=1;i<=number;i++){
        	int index1=random.nextInt(e.length);
        	if(index1==0)//��������
        		{
        a=(int)(100*Math.random());   
        b=(int)(100*Math.random());
        int index2=random.nextInt(c.length);
        char d=c[index2];
        System.out.print(a);
        System.out.print(d);
        System.out.println(b+"=");}
        	else if(index1==1)//���������
        	{
        		
        	}		
        }
	}
        
//ѭ�����������������Լ��

         

        //��֤��һ���������ڵڶ�������

        if(a<b)

        {

            int temp;

            temp=a;

            a=b;

            b=temp;

        }

        while(a%b!=0)   //��������Ϊ��ʱѭ��

        {

            int temp=a%b;

            a=b;

            b=temp;

        }

        return b;  //�������Լ��

    }

     

    public static int minCommon(int a,int b)

    {

        //����С������

        return a*b/maxCommon(a,b);

    }

     

    public static String reduce(int a ,int b)

    {

        //����a/b��ɵķ�������Լ��

         

        //��һ�������ҳ����ߵ����Լ��

        int c=maxCommon(a,b);

         

        //���ӷ�ĸԼ��

        a=a/c; 

        b=b/c;

         

        //��a,bת���ɷ�������

        String temp=a+"/"+b;

        return temp;

    }

    public static String calculate(String a,String b,String c) //�����ֱ�����һ���������ڶ����������Լ������

    {

        int a1=Integer.parseInt(a.substring(0,1));//����a�ķ���

        int a2=Integer.parseInt(a.substring(2));//����a�ķ�ĸ

        int b1=Integer.parseInt(b.substring(0,1));//����b�ķ���

        int b2=Integer.parseInt(b.substring(2));//����b�ķ�ĸ

        if(c.equals("+"))

        {

            //������������ĸ����С������,����ͨ��

            int temp=minCommon(a2,b2);

            a1=temp/a2*a1;

            b1=temp/b2*b1;

             

            //��ϲ���ķ���

            int temp1=a1+b1;

             

            String temp2=reduce(temp1,temp);

            return temp2;

        }

        else if(c.equals("-"))

        {

            //������������ĸ����С������,����ͨ��

            int temp=minCommon(a2,b2);

            a1=temp/a2*a1;

            b1=temp/b2*b1;

             

            //��ϲ���ķ���

            int temp1=a1-b1;

             

            return reduce(temp1,temp);

        }

        else if(c.equals("*"))

        {

            //ֱ�Ӱѷ�ĸ��˵õ��ϲ���ķ�ĸ

            int temp=a2*b2;

             

            //��ϲ���ķ���

            int temp1=a1*b1;

             

            return reduce(temp1,temp);

        }

         

        else if(c.equals("/"))

        {

            //����������ǵ�һ���������Եڶ��������ĵ���

            int temp=a2*b1;

             

            //��ϲ���ķ���

            int temp1=a1*b2;

             

            return reduce(temp1,temp);

        }

        String xuhaojun="�ɹ���";

        return xuhaojun;

}
