package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.ArticleBean;
import Bean.UserBean;
import Utils.DBUtil;

public class ArticleDao {

	public boolean add(ArticleBean articleBean) {
		// TODO Auto-generated method stub
		boolean f=false;
		
		String sql="insert into article (title,content,detail,datetime,userid) values('"+articleBean.getTitle()+"','"+
				articleBean.getContent()+"','"+articleBean.getDetail()+"','"+articleBean.getDatetime()+"','"+articleBean.getUserid()+"')";
		int a=0;
		Connection conn=null;
		Statement state=null;
		try
		{
			conn=DBUtil.getConn();
			state=conn.createStatement();
			a=state.executeUpdate(sql);
		}
		catch(Exception e)
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
		
		//删除博文
		public boolean delete(int id)
		{
			boolean f=false;
			String sql="delete from article where id='"+id+"'";
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
			if(a>0)
			{
				f=true;
			}
			return f;
		}
		
		//获取所有话题
		public List<ArticleBean> getList()
		{
			String sql="select * from topic";
			System.out.println(sql);
			List<ArticleBean> list=new ArrayList<ArticleBean>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				ArticleBean articleBean=null;
				while(rs.next())
				{
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String detail=rs.getString("detail");
					String datetime=rs.getString("datetime");
					int agree=rs.getInt("agree");
					String status=rs.getString("status");
					int userid=rs.getInt("userid");
					UserBean userBean=null;
					UserDao userDao=new UserDao();
					userBean=userDao.getById(userid);
					articleBean=new ArticleBean(id, title,content, datetime,detail, agree, status,userBean);
					list.add(articleBean);
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
				rs=state.executeQuery("select count(*) count from article");
				
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
		
//		获取每一个分页的数据
		public List<ArticleBean> getListByPage(int start,int size){
			String sql = "select * from article limit "+ start +","+ size;
			System.out.println(sql);
			Connection connection=DBUtil.getConn();
			Statement statement=null;
			ResultSet resultSet=null;
			List<ArticleBean> articleBeans=new ArrayList<ArticleBean>();
			try{
				statement=connection.createStatement();
				resultSet=statement.executeQuery(sql);
				ArticleBean ArticBean;
				while(resultSet.next()){
					
					int id=resultSet.getInt("id");
					String title=resultSet.getString("title");
					String content=resultSet.getString("content");
					String datetime=resultSet.getString("datetime");
					String detail=resultSet.getString("detail");
					int agree=resultSet.getInt("agree");
					String status=resultSet.getString("status");
					int userid=resultSet.getInt("userid");
					UserBean userBean=null;
					UserDao userDao=new UserDao();
					userBean=userDao.getById(userid);
					ArticBean=new ArticleBean(id, title,content, datetime,detail, agree, status,userBean);
					articleBeans.add(ArticBean);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBUtil.close(resultSet,statement, connection);
			}
			return articleBeans;
		}
		
		
		//获取阅读排行榜（十条）
		public List<ArticleBean> getReadList()
		{
			String sql="select * from article  order by id desc limit 0,10";
			System.out.println(sql);
			List<ArticleBean> list=new ArrayList<>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				ArticleBean ArticBean=null;
				while(rs.next())
				{
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String datetime=rs.getString("datetime");
					int agree=rs.getInt("agree");
					String detail=rs.getString("detail");
					String status=rs.getString("status");
					int userid=rs.getInt("userid");
					UserBean userBean=null;
					UserDao userDao=new UserDao();
					userBean=userDao.getById(userid);
					ArticBean=new ArticleBean(id, title,content, datetime,detail, agree, status,userBean);
					
					list.add(ArticBean);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			return list;
		}
		
		//查询搜索匹配的所有话题
		public List<ArticleBean> getLists(String key)
		{
			String sql="select * from article where title LIKE'%"+key+"%'";
			System.out.println(sql);
			List<ArticleBean> list=new ArrayList<>();
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			try
			{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				ArticleBean ArticBean=null;
				while(rs.next())
				{
					int id=rs.getInt("id");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String datetime=rs.getString("datetime");
					int agree=rs.getInt("agree");
					String detail=rs.getString("detail");
					String status=rs.getString("status");
					int userid=rs.getInt("userid");
					ArticBean=new ArticleBean(id, title,content, datetime,detail, agree, status,userid);
					//ArticBean=new ArticBean(id,title,content,datetime,agree,status,userid);
					list.add(ArticBean);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			
			return list;
		}
		
		//通过id来获取ArticBean对象
		public ArticleBean getById(int id)
		{
			String sql="select * from article where id="+id;
			Connection conn=DBUtil.getConn();
			Statement state=null;
			ResultSet rs=null;
			ArticleBean ArticBean=null;
			try
			{
				state=conn.createStatement();
				rs=state.executeQuery(sql);
				while(rs.next())
				{
					ArticBean=new ArticleBean();
					ArticBean.setId(rs.getInt("id"));
					ArticBean.setTitle(rs.getString("title"));
					ArticBean.setContent(rs.getString("content"));
					ArticBean.setDatetime(rs.getString("datetime"));
					ArticBean.setDetail(rs.getString("detail"));
					ArticBean.setAgree(rs.getInt("agree"));
					ArticBean.setStatus(rs.getString("status"));
					ArticBean.setUserid(rs.getInt("userid"));
					UserDao userDao = new UserDao();
					UserBean userBean=userDao.getById(rs.getInt("userid"));
					ArticBean.setUserBean(userBean);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				DBUtil.close(rs, state, conn);
			}
			return ArticBean;
		}
		
		//通过title来获取ArticBean对象
			public ArticleBean getByTitle(String title)
			{
				String sql="select * from article where title='"+title+"'";
				Connection conn=DBUtil.getConn();
				Statement state=null;
				ResultSet rs=null;
				ArticleBean ArticBean=null;
				try
				{
					state=conn.createStatement();
					rs=state.executeQuery(sql);
					while(rs.next())
					{
						ArticBean=new ArticleBean();
						ArticBean.setId(rs.getInt("id"));
						ArticBean.setTitle(rs.getString("title"));
						ArticBean.setContent(rs.getString("content"));
						ArticBean.setDatetime(rs.getString("datetime"));
						ArticBean.setDetail(rs.getString("detail"));
						ArticBean.setAgree(rs.getInt("agree"));
						ArticBean.setStatus(rs.getString("status"));
						ArticBean.setUserid(rs.getInt("userid"));
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					DBUtil.close(rs, state, conn);
				}
				return ArticBean;
			}


			public List<ArticleBean> getReadListbyid(int id) {
				// TODO Auto-generated method stub
				String sql="select * from article where userid='"+id+"'";
				System.out.println(sql);
				List<ArticleBean> list=new ArrayList<>();
				Connection conn=DBUtil.getConn();
				Statement state=null;
				ResultSet rs=null;
				try{
					state=conn.createStatement();
					rs=state.executeQuery(sql);
					ArticleBean ArticBean=null;
					while(rs.next())
					{
						int id2=rs.getInt("id");
						String title=rs.getString("title");
						String content=rs.getString("content");
						String datetime=rs.getString("datetime");
						int agree=rs.getInt("agree");
						String detail=rs.getString("detail");
						String status=rs.getString("status");
						int userid=rs.getInt("userid");
						UserBean userBean=null;
						UserDao userDao=new UserDao();
						userBean=userDao.getById(userid);
						ArticBean=new ArticleBean(id2, title,content, datetime,detail, agree, status,userBean);
						
						list.add(ArticBean);
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					DBUtil.close(rs, state, conn);
				}
				return list;
			}


			public void update(ArticleBean articleBean) {
				// TODO Auto-generated method stub
				String sql="update article set  title='"+articleBean.getTitle()+"',content ='"+articleBean.getContent()+"',detail='"+
				articleBean.getDetail()+"' where id='"+articleBean.getId()+"' ";
				
				Connection conn=DBUtil.getConn();
				Statement state=null;
				try
				{
					state=conn.createStatement();
					System.out.println(sql);
					state.executeUpdate(sql);
				}catch(Exception e)
				{
					e.printStackTrace();
				}finally{
					DBUtil.close(state, conn);
				}
			}

	
}
