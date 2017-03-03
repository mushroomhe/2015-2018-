//自动生成30道四则运算，（整数，真分数），题目避免重复，可定制数量。
package calculator;
import java.util.*;

public class calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//随机产生两位100以内的数
        int a,b;//（真分数即为分子比分母小）
        //随机产生运算符（+，-，*，/）
        char[] c={'+','-','*','/'};
        //随机生成整数运算或者真分数运算
        int[] e={1,2};
        System.out.println("请输入所要生成题目的数目：");
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        System.out.println("生成的题目如下：");
        Random random=new Random();
        for(int i=1;i<=number;i++){
        	int index1=random.nextInt(e.length);
        	if(index1==0)//生成整数
        		{
        a=(int)(100*Math.random());   
        b=(int)(100*Math.random());
        int index2=random.nextInt(c.length);
        char d=c[index2];
        System.out.print(a);
        System.out.print(d);
        System.out.println(b+"=");}
        	else if(index1==1)//生成真分数
        	{
        		
        	}		
        }
	}
        
//循环法求两个数的最大公约数

         

        //保证第一个参数大于第二个参数

        if(a<b)

        {

            int temp;

            temp=a;

            a=b;

            b=temp;

        }

        while(a%b!=0)   //在余数不为零时循环

        {

            int temp=a%b;

            a=b;

            b=temp;

        }

        return b;  //返回最大公约数

    }

     

    public static int minCommon(int a,int b)

    {

        //求最小公倍数

        return a*b/maxCommon(a,b);

    }

     

    public static String reduce(int a ,int b)

    {

        //对于a/b组成的分数进行约分

         

        //第一步就是找出两者的最大公约数

        int c=maxCommon(a,b);

         

        //分子分母约分

        a=a/c; 

        b=b/c;

         

        //把a,b转换成分数返回

        String temp=a+"/"+b;

        return temp;

    }

    public static String calculate(String a,String b,String c) //参数分别代表第一个分数，第二个分数，以及运算符

    {

        int a1=Integer.parseInt(a.substring(0,1));//代表a的分子

        int a2=Integer.parseInt(a.substring(2));//代表a的分母

        int b1=Integer.parseInt(b.substring(0,1));//代表b的分子

        int b2=Integer.parseInt(b.substring(2));//代表b的分母

        if(c.equals("+"))

        {

            //首先求两个分母的最小公倍数,进行通分

            int temp=minCommon(a2,b2);

            a1=temp/a2*a1;

            b1=temp/b2*b1;

             

            //求合并后的分子

            int temp1=a1+b1;

             

            String temp2=reduce(temp1,temp);

            return temp2;

        }

        else if(c.equals("-"))

        {

            //首先求两个分母的最小公倍数,进行通分

            int temp=minCommon(a2,b2);

            a1=temp/a2*a1;

            b1=temp/b2*b1;

             

            //求合并后的分子

            int temp1=a1-b1;

             

            return reduce(temp1,temp);

        }

        else if(c.equals("*"))

        {

            //直接把分母相乘得到合并后的分母

            int temp=a2*b2;

             

            //求合并后的分子

            int temp1=a1*b1;

             

            return reduce(temp1,temp);

        }

         

        else if(c.equals("/"))

        {

            //分数相除就是第一个分数乘以第二个分数的倒数

            int temp=a2*b1;

             

            //求合并后的分子

            int temp1=a1*b2;

             

            return reduce(temp1,temp);

        }

        String xuhaojun="成功了";

        return xuhaojun;

}
