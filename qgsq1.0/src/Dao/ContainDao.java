package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bean.ArticleBean;
import Bean.ContainBean;
import Bean.UserBean;
import Utils.DBUtil;

public class ContainDao {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<ContainBean> getarticalContainbyId(int id) {
		// TODO Auto-generated method stub
		String sql="select * from contain where articleid='"+id+"'";
		System.out.println(sql);
		List<ContainBean> list=new ArrayList<>();
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			ContainBean containBean=null;
			while(rs.next())
			{
				containBean=new ContainBean();
				containBean.setId(rs.getInt("id"));
				containBean.setContent(rs.getString("content"));
				containBean.setDatetime(rs.getString("datetime"));
				containBean.setArticleid(rs.getInt("articleid"));
				containBean.setTopicid(rs.getInt("topicid"));
				containBean.setUserid(rs.getInt("userid"));
				containBean.setStatus(rs.getString("status"));
				UserDao userDao = new UserDao();
				UserBean userBean = userDao.getById(rs.getInt("userid"));
				containBean.setUserBean(userBean);
				
				list.add(containBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return list;
	}

	public List<ContainBean> gettopicContainbyId(int id) {
		// TODO Auto-generated method stub
		String sql="select * from contain where topicid='"+id+"'";
		System.out.println(sql);
		List<ContainBean> list=new ArrayList<>();
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		try{
			state=conn.createStatement();
			rs=state.executeQuery(sql);
			ContainBean containBean=null;
			while(rs.next())
			{
				containBean=new ContainBean();
				containBean.setId(rs.getInt("id"));
				containBean.setContent(rs.getString("content"));
				containBean.setDatetime(rs.getString("datetime"));
				containBean.setArticleid(rs.getInt("articleid"));
				containBean.setTopicid(rs.getInt("topicid"));
				containBean.setUserid(rs.getInt("userid"));
				containBean.setStatus(rs.getString("status"));
				UserDao userDao = new UserDao();
				UserBean userBean = userDao.getById(rs.getInt("userid"));
				containBean.setUserBean(userBean);
				
				list.add(containBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return list;
	}

	//Ìí¼ÓÆÀÂÛ
	public void add(ContainBean containBean) {
		// TODO Auto-generated method stub
		String sql="insert into contain(content,datetime,userid,topicid,articleid) values('"+containBean.getContent()
		+"','"+containBean.getDatetime()+"','"+containBean.getUserid()+"','"+containBean.getTopicid()+"','"+containBean.getArticleid()+"')";
		
		Connection conn=DBUtil.getConn();
		Statement state=null;	
		try{
			state=conn.createStatement();
			state.executeUpdate(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			DBUtil.close(state, conn);
		}
	}

}
