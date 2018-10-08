package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ���ݿ�Ĺ����࣬�����������ݿ�ͶϿ����ݿ�
 * �ܱ��� 2017 5 6
 * */

public class DBUtil {
	//eshopΪ���ݿ����ƣ�db_userΪ���ݿ��û���db_passwordΪ���ݿ�����
	//���ݿ����ӵ�ַΪjdbc:mysql://127.0.0.1/eshop?
	//�����û��Լ��趨���ݿ���룬�������ó�UTF-8
	public static String db_url="jdbc:mysql://127.0.0.1/qgsq?useUnicode=true&characterEcoding=utf-8";
	//�û���
	public static String db_user="root";
	public static String db_password="baohui";
	
	//��������
	public static Connection getConn(){
		//���ӳ�ʼ��
		Connection conn=null;
		try{
			//����jdbc����
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(db_url,db_user,db_password);
			//System.out.print("�ɹ���");
		}
		catch(Exception e){
			//�׳��쳣(����)
			e.printStackTrace();
			//System.out.println("ʧ�ܣ�");
		}
		return conn;
	}
	
	//���������ݼ������ӹر�
	public static void close(Statement state,Connection conn)
	{
		//�ж�
		if(state!=null)
		{
			try{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try{
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	//ʹ�ý���������ӹر�
	public static void close(ResultSet rs,Statement state,Connection conn){
		if(rs!=null)
		{
			try{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(state!=null)
		{
			try{
				state.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try{
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getConn();
	}
}
