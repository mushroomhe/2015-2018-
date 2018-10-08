package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.AdminBean;
import Utils.DBUtil;

public class AdminDao {

	//查重
	public boolean checkLogin(String username)
	{
		String sql="select * form admin";
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
	
	//检查是否被冻结（或删除）
	public boolean checkstatus(int id)
	{
		String sql="select * from admin where id='"+id+"' ";
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
	//登陆检验
	public int login(String username,String password)
	{
		String sql="select * from admin where username='"+username+"'";
		Connection conn=null;
		Statement state=null;
		ResultSet rs=null;
		int a=0;//账号不存在
		try
		{
			conn=DBUtil.getConn();
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				a=1;//密码错误
				if(password.equals(rs.getString("password")))
				{
					a=2;//账号被冻结
					if(rs.getString("status").equals("1"))
					{
						a=3;//登陆成功
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
	
	//增加
	public boolean add(AdminBean adminBean)
	{
		String sql="insert into  admin(username,password,salt,datetime) values('"+adminBean.getUsername()+"','"+adminBean.getPassword()
		+"','"+adminBean.getSalt()+"','"+adminBean.getDatetime()+"')";
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
	//删除
	public boolean delete(int id)
	{
		String sql="update admin set status='1' where id='"+id+"'";
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
	//修改
	public boolean change(AdminBean adminBean)
	{
		String sql="update admin set username='"+adminBean.getUsername()+"',password='"+adminBean.getPassword()+"',salt='"+adminBean.getSalt()
		+"',status='"+adminBean.getStatus()+"' where id='"+adminBean.getId()+"'";
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
	//通过查询内容获得列表
	public List<AdminBean> getListbySearch(String search) {
		// TODO Auto-generated method stub
		String sql="select * from admin  where username='"+search+"'  order by datetime desc ";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			AdminBean adminBean;
			while(rs.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(rs.getInt("id"));
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setSalt(rs.getString("salt"));
				adminBean.setDatetime(rs.getString("datetime"));
				adminBean.setStatus(rs.getString("status"));
				adminBeans.add(adminBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return adminBeans;
	}
	//通过id获取管理员信息
	public AdminBean getById(int id)
	{
		String sql="select * from admin where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		AdminBean adminBean = null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(rs.getInt("id"));
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setSalt(rs.getString("salt"));
				adminBean.setDatetime(rs.getString("datetime"));
				adminBean.setStatus(rs.getString("status"));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return adminBean;
		
	}
	//直接获取列表
	public List<AdminBean> getList() {
		// TODO Auto-generated method stub
		String sql="select * from admin  order by datetime desc";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			AdminBean adminBean;
			while(rs.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(rs.getInt("id"));
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setSalt(rs.getString("salt"));
				adminBean.setDatetime(rs.getString("datetime"));
				adminBean.setStatus(rs.getString("status"));
				adminBeans.add(adminBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return adminBeans;
	}
	//获取前几条数据
	public List<AdminBean> getListbyNumber(int a,int b) {
		// TODO Auto-generated method stub
		String sql="select * from admin  order by datetime desc limit "+a+" to "+b+"";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans = new ArrayList<AdminBean>();
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			AdminBean adminBean;
			while(rs.next())
			{
				adminBean = new AdminBean();
				adminBean.setId(rs.getInt("id"));
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setSalt(rs.getString("salt"));
				adminBean.setDatetime(rs.getString("datetime"));
				adminBean.setStatus(rs.getString("status"));
				adminBeans.add(adminBean);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return adminBeans;
	}

}
