package Dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Bean.ArticleBean;
import Bean.TopicBean;
import Bean.UserBean;
import Utils.DBUtil;
import Dao.UserDao;



public class TopicDao {
	
	//添加话题
	public boolean add(TopicBean topicBean)
	{
		String sql="insert into topic(title,content,datetime,userid)"+"values('"+topicBean.getTitle()+"','"+topicBean.getContent()+"',getdate(),'"+topicBean.getUserid()+"')";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		boolean f=false;
		int a=0;
		try
		{
			state=conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		if(a>0)
		{
			f=true;
		}
		return f;
	}
	
	//通过话题id来获取用户信息
	public UserBean getUser(int id)
	{
		String sql="select userid from topic where id='"+id+"'";
		UserBean userBean=null;
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			if(rs.next())
			{
				int userid=rs.getInt("userid");
				String sql2="select * from user where id='"+userid+"'";
				try
				{
					rs=state.executeQuery(sql2);
					if(rs.next())
					{
						String username=rs.getString("username");
						String password=rs.getString("password");
						String salt=rs.getString("salt");
						String nickname=rs.getString("nickname");
						String picture=rs.getString("picture");
						String sex=rs.getString("sex");
						int age=rs.getInt("age");
						String edugrade=rs.getString("edugrade");
						String datetime=rs.getString("datetime");
						String status=rs.getString("status");
						userBean=new UserBean(userid,username,password,salt,nickname,datetime,picture,sex,age,edugrade,status);
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					DBUtil.close(rs,state, conn);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		return userBean;
	}
	
	//删除话题
	public boolean delete(int id)
	{
		boolean f=false;
		String sql="delete from topic where id='"+id+"'";
		Connection conn=DBUtil.getConn();
		Statement state=null;
		int a=0;
		try
		{
			state=conn.createStatement();
			a=state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		
		return f;
	}
	
	//获取所有话题
	public List<TopicBean> getList()
	{
		String sql="select * from topic";
		System.out.println(sql);
		List<TopicBean> list=new ArrayList<TopicBean>();
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			TopicBean topicBean=null;
			while(rs.next())
			{
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String datetime=rs.getString("datetime");
				int agree=rs.getInt("agree");
				String status=rs.getString("status");
				int userid=rs.getInt("userid");
				UserBean userBean=null;
				UserDao userDao=new UserDao();
				userBean=userDao.getById(userid);
				topicBean=new TopicBean(id,title,content,datetime,agree,status,userBean);
				list.add(topicBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	//获取数据表中的数据总量
	public static int getCount()
	{
		ResultSet rs=null;
		Statement state=null;
		Connection conn=null;
		int size=0;
		try
		{
			conn=DBUtil.getConn();
			state=conn.createStatement();
			rs=state.executeQuery("select count(*) count from topic");
			
			if(rs.next())
			{
				size=rs.getInt("count");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.close(rs, state, conn);
		}
		
		return size;
	}
	
//	获取每一个分页的数据
	public List<TopicBean> getListByPage(int start,int size){
		String sql = "select * from topic limit "+ start +","+ size;
		System.out.println(sql);
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		List<TopicBean> topicBeans=new ArrayList<TopicBean>();
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			TopicBean topicBean;
			while(resultSet.next()){
				
				int id=resultSet.getInt("id");
				String title=resultSet.getString("title");
				String content=resultSet.getString("content");
				String datetime=resultSet.getString("datetime");
				int agree=resultSet.getInt("agree");
				String status=resultSet.getString("status");
				int userid=resultSet.getInt("userid");
				UserBean userBean=null;
				UserDao userDao=new UserDao();
				userBean=userDao.getById(userid);
				topicBean=new TopicBean(id,title,content,datetime,agree,status,userBean);
				topicBeans.add(topicBean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet,statement, connection);
		}
		return topicBeans;
	}
	
	
	//获取阅读排行榜（十条）
	public List<TopicBean> getReadList()
	{
		String sql="select * from topic  order by id desc limit 0,10";
		System.out.println(sql);
		List<TopicBean> list=new ArrayList<>();
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			TopicBean topicBean=null;
			while(rs.next())
			{
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String datetime=rs.getString("datetime");
				int agree=rs.getInt("agree");
				String status=rs.getString("status");
				int userid=rs.getInt("userid");
				UserBean userBean=null;
				UserDao userDao=new UserDao();
				userBean=userDao.getById(userid);
				topicBean=new TopicBean(id,title,content,datetime,agree,status,userBean);
				list.add(topicBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return list;
	}
	
	//查询搜索匹配的所有话题
	public List<TopicBean> getLists(String key)
	{
		String sql="select * from topic where title LIKE'%"+key+"%'";
		System.out.println(sql);
		List<TopicBean> list=new ArrayList<>();
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			TopicBean topicBean=null;
			while(rs.next())
			{
				int id=rs.getInt("id");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String datetime=rs.getString("datetime");
				int agree=rs.getInt("agree");
				String status=rs.getString("status");
				int userid=rs.getInt("userid");
				topicBean=new TopicBean(id,title,content,datetime,agree,status,userid);
				list.add(topicBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		
		return list;
	}
	
	//通过id来获取TopicBean对象
	public TopicBean getById(int id)
	{
		String sql="select * from topic where id="+id;
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		TopicBean topicBean=null;
		try
		{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			while(rs.next())
			{
				topicBean=new TopicBean();
				topicBean.setId(rs.getInt("id"));
				topicBean.setTitle(rs.getString("title"));
				topicBean.setContent(rs.getString("content"));
				topicBean.setDatetime(rs.getString("datetime"));
				topicBean.setAgree(rs.getInt("agree"));
				topicBean.setStatus(rs.getString("status"));
				topicBean.setUserid(rs.getInt("userid"));
				UserDao userDao=new UserDao();
				UserBean userBean=userDao.getById(rs.getInt("userid"));
				topicBean.setUserBean(userBean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return topicBean;
	}
	
	//通过title来获取TopicBean对象
		public TopicBean getByTitle(String title)
		{
			String sql="select * from topic where title='"+title+"'";
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			TopicBean topicBean=null;
			try
			{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				while(rs.next())
				{
					topicBean=new TopicBean();
					topicBean.setId(rs.getInt("id"));
					topicBean.setTitle(rs.getString("title"));
					topicBean.setContent(rs.getString("content"));
					topicBean.setDatetime(rs.getString("datetime"));
					topicBean.setAgree(rs.getInt("agree"));
					topicBean.setStatus(rs.getString("status"));
					topicBean.setUserid(rs.getInt("userid"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			return topicBean;
		}

		public List<TopicBean> getReadListbyid(int userid) {
			// TODO Auto-generated method stub
			String sql="select * from topic where userid='"+userid+"'";
			System.out.println(sql);
			List<TopicBean> list=new ArrayList<>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				TopicBean topicBean=null;
				while(rs.next())
				{
					topicBean=new TopicBean();
					topicBean.setId(rs.getInt("id"));
					topicBean.setTitle(rs.getString("title"));
					topicBean.setContent(rs.getString("content"));
					topicBean.setDatetime(rs.getString("datetime"));
					topicBean.setAgree(rs.getInt("agree"));
					topicBean.setStatus(rs.getString("status"));
					topicBean.setUserid(rs.getInt("userid"));
					UserDao userDao=new UserDao();
					UserBean userBean=userDao.getById(rs.getInt("userid"));
					topicBean.setUserBean(userBean);
					
					
					list.add(topicBean);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			return list;
		}


}
