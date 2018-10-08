package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.AdminBean;
import Bean.ArticleBean;
import Bean.TopicBean;
import Bean.UserBean;
import Utils.DBUtil;

public class UserDao {
	//����
	public boolean checkLogin(String username)
	{
		String sql="select * from user";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		boolean f=false;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				if(rs.getString("username").equals(username))
				{
					f=true;
				}				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return f;
	}
	//����Ƿ񱻶���
	//����Ƿ񱻶��ᣨ��ɾ����
	public boolean checkstatus(int id)
	{
		String sql="select * from user where id='"+id+"' ";
		boolean f=false;
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				if(rs.getString("status").equals("1"))
				{
					f=true;
				}				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return f;
	}
	//��½���
	//��½����
		public int login(String username,String password)
		{
			String sql="select * from user where username='"+username+"'";
			Connection conn=null;
			Statement state=null;
			ResultSet rs=null;
			int a=0;//�˺Ų�����
			try
			{
				conn=DBUtil.getConn();
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				if(rs.next())
				{
					a=1;//�������
					if(password.equals(rs.getString("password")))
					{
						a=2;//�˺ű�����
						if(rs.getString("status").equals("0"))
						{
							a=3;//��½�ɹ�
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				DBUtil.close(rs, state, conn);
			}
			return a;
		}
	//�û�����
	public boolean add(UserBean userBean)
	{
		String sql="insert into  user(username,password,nickname,picture,sex,age,edugrade,datetime) values('"+userBean.getUsername()+"','"+userBean.getPassword()
		+"','"+userBean.getNickname()+"','"+userBean.getPicture()+"','"+userBean.getSex()+"','"+userBean.getAge()+"','"+userBean.getEdugrade()
		+"','"+userBean.getDatetime()+"')";
		Connection conn=DBUtil.getConn();
		boolean f=false;
		Statement state=null;
		int a = 0;
		try{
			state=conn.createStatement();
			a=state.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f=true;
		}
		return f;
	}
	//�޸�
	public boolean change(UserBean userBean)
	{
		String sql="update admin set username='"+userBean.getUsername()+"',password='"+userBean.getPassword()+"',salt='"+userBean.getSalt()
		+"',status='"+userBean.getStatus()+"',nickname='"+userBean.getNickname()+"',picture='"+userBean.getPicture()+"',sex='"+userBean.getSex()
		+"',age='"+userBean.getAge()+"',edugrade='"+userBean.getEdugrade()+"' where id='"+userBean.getId()+"'";
		Connection conn=DBUtil.getConn();
		boolean f=false;
		Statement state=null;
		int a = 0;
		try{
			state=conn.createStatement();
			a=state.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f=true;
		}
		return f;
	}
	//ɾ��
	public boolean delete(int id)
	{
		String sql="update user set status='1' where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		boolean f=false;
		Statement state=null;
		int a = 0;
		try{
			state=conn.createStatement();
			a=state.executeUpdate(sql);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f=true;
		}
		return f;
	}
	//ͨ��id��ȡuser����Ϣ
	public UserBean getById(int id)
	{
		String sql="select * from user where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		UserBean userBean = null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setSalt(rs.getString("salt"));
				userBean.setDatetime(rs.getString("datetime"));
				userBean.setStatus(rs.getString("status"));
				userBean.setNickname(rs.getString("nickname"));
				userBean.setPicture(rs.getString("picture"));
				userBean.setSex(rs.getString("sex"));
				userBean.setAge(rs.getInt("age"));
				userBean.setEdugrade(rs.getString("edugrade"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return userBean;
		
	}
	//ֱ�ӻ�ȡ�б�
	public List<UserBean> getList() {
		// TODO Auto-generated method stub
		String sql="select * from user  order by datetime desc";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<UserBean> userBeans = new ArrayList<UserBean>();
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			UserBean userBean=null;
			while(rs.next())
			{
				userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setSalt(rs.getString("salt"));
				userBean.setDatetime(rs.getString("datetime"));
				userBean.setStatus(rs.getString("status"));
				userBean.setNickname(rs.getString("nickname"));
				userBean.setPicture(rs.getString("picture"));
				userBean.setSex(rs.getString("sex"));
				userBean.setAge(rs.getInt("age"));
				userBean.setEdugrade(rs.getString("edugrade"));
				userBeans.add(userBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return userBeans;
	}
	//ͨ���û������
	public UserBean getByusername(String username) {
		// TODO Auto-generated method stub
		String sql="select * from user where username='"+username+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		UserBean userBean = null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setSalt(rs.getString("salt"));
				userBean.setDatetime(rs.getString("datetime"));
				userBean.setStatus(rs.getString("status"));
				userBean.setNickname(rs.getString("nickname"));
				userBean.setPicture(rs.getString("picture"));
				userBean.setSex(rs.getString("sex"));
				userBean.setAge(rs.getInt("age"));
				userBean.setEdugrade(rs.getString("edugrade"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return userBean;
	}
	
	//ͨ���û�id��û����б�
		public List<TopicBean> getToListById(int userid)
		{
			String sql="select * from topic where userid='"+userid+"'";
			System.out.println(sql);
			List<TopicBean> list=new ArrayList<>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try
			{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				while(rs.next())
				{
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String datetime=rs.getString("datetime");
					int agree=rs.getInt("agree");
					String status=rs.getString("status");
					//int userid=rs.getInt("userid");
					TopicBean topicBean=null;
					topicBean=new TopicBean(id,title,content,datetime,agree,status,userid);
					list.add(topicBean);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			
			return list;
			
		}
		
		//ͨ���û�id����ò����б�
		public List<ArticleBean> getBoListById(int userid)
		{
			String sql="select * from article where userid='"+userid+"'";
			System.out.println(sql);
			List<ArticleBean> list=new ArrayList<>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try
			{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				while(rs.next())
				{
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String datetime=rs.getString("datetime");
					String detail=rs.getString("detail");
					int agree=rs.getInt("agree");
					String status=rs.getString("status");
					//int userid=rs.getInt("userid");
					ArticleBean articleBean=null;
					articleBean=new ArticleBean(id,title,content,datetime,detail,agree,status,userid);
					list.add(articleBean);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			
			return list;
			
		}
		
		
		
		  //�޸ĸ�����Ϣ
		  public boolean update(UserBean userBean) { 
		    String sql = "update user set "; 
		    //sql += "id='" + userBean.getId()+"'"; 
		    if(userBean.getUsername()!=null){ 
		      sql += "username='" + userBean.getUsername()+"'"; 
		    } 
		    if(userBean.getPassword()!=null){ 
			      sql += ", password='" + userBean.getPassword()+"'"; 
			    } 
		    if(userBean.getSalt()!=null){ 
			      sql += ", salt='" + userBean.getSalt()+"'"; 
			    } 
		    if(userBean.getNickname()!=null){ 
			      sql += ", nickname='" + userBean.getNickname()+"'"; 
			    } 
		    if(userBean.getPicture()!=null){ 
			      sql += ",picture='" + userBean.getPicture()+"'"; 
			    } 
		    if(userBean.getSex()!=null){ 
			      sql += ", sex='" + userBean.getSex()+"'"; 
			    } 
		    if(userBean.getAge()>=0){ 
			      sql += ", age='" + userBean.getAge()+"'"; 
			    } 
		    if(userBean.getEdugrade()!=null){ 
			      sql += ", edugrade='" + userBean.getEdugrade()+"'"; 
			    } 
		    if(userBean.getDatetime()!=null){ 
			      sql += ", datetime='" + userBean.getDatetime()+"'"; 
			    } 
		    if(userBean.getStatus()!=null){ 
			      sql += ", status='" + userBean.getStatus()+"'"; 
			    } 
		    sql += " where id='" + userBean.getId()+"'"; 
		    System.out.println(sql);
		    Connection conn = DBUtil.getConn(); 
		    Statement state = null; 
		    boolean f = false; 
		    int a = 0; 
		    try { 
		      state = conn.createStatement(); 
		      a = state.executeUpdate(sql); 
		 
		    } catch (Exception e) { 
		      e.printStackTrace(); 
		    } finally { 
		      DBUtil.close(state, conn); 
		    } 
		    if (a > 0) { 
		      f = true; 
		    } 
		    return f; 
		  }

}
