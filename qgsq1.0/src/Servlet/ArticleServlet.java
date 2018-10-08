package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.ArticleBean;
import Bean.ContainBean;
import Bean.PagingBean;
import Bean.TopicBean;
import Dao.ArticleDao;
import Dao.ContainDao;
import Dao.TopicDao;
import Utils.Constants;
import Utils.StringUtil;

public class ArticleServlet extends HttpServlet {

	public void service(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String method=req.getParameter("method");
		if("add".equals(method))
		{
			add(req,resp);
		}
		else if("search".equals(method))
		{
			search(req,resp);
		}
		else if("details".equals(method))
		{
			details(req,resp);
		}
		else if("list".equals(method))
		{
			list(req,resp);
		}
		else if("readList".equals(method))
		{
			readList(req,resp);
		}
		else if("lists".equals(method))
		{
			lists(req,resp);
		}else if("addcontain".equals(method))
		{
			addcontain(req,resp);
		}else if("beforchange".equals(method))
		{
			beforchange(req,resp);
		}else if("change".equals(method))
		{
			change(req,resp);
		}
		
	}
	//修改
	private void change(HttpServletRequest req, HttpServletResponse resp)throws 
	IOException,ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		ArticleBean articleBean = new ArticleBean();
		
		String title=req.getParameter("title");
		String content=req.getParameter("intro");
		String detail=req.getParameter("information");
		int articleid=StringUtil.StringToInt(req.getParameter("articleid"));
		String username=req.getParameter("username");
		
		String password = req.getParameter("password");
		System.out.println(username+password);
		
		articleBean.setId(articleid);
		articleBean.setTitle(title);
		articleBean.setContent(content);
		articleBean.setDetail(detail);
		
		ArticleDao  articleDao = new ArticleDao();
		articleDao.update(articleBean);
		
		resp.sendRedirect(req.getContextPath()+"/userServlet?method=userself&&username="+username+"&&password="+password+"&&status=artupdate");
	}
	//预修改
	private void beforchange(HttpServletRequest req, HttpServletResponse resp) throws 
	IOException,ServletException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int articleid=StringUtil.StringToInt(req.getParameter("articleid"));
		
		ArticleDao articleDao = new ArticleDao();
		ArticleBean article=articleDao.getById(articleid);
		
		req.getSession().setAttribute("aricleBean", article);
		resp.sendRedirect(req.getContextPath()+"/user/changebowen.jsp");
	}
	//添加评论
	private void addcontain(HttpServletRequest req, HttpServletResponse resp)throws 
	IOException,ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int userid=StringUtil.StringToInt(req.getParameter("userid"));
		int articleid=StringUtil.StringToInt(req.getParameter("articleid"));
		String content=req.getParameter("content");
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd");
		ContainBean containBean = new ContainBean();
		containBean.setUserid(userid);
		containBean.setArticleid(articleid);
		containBean.setContent(content);
		containBean.setDatetime(createDate.format(new Date()));
		
		ContainDao containDao = new ContainDao();
		containDao.add(containBean);
		
		//显示文章信息
		
		ArticleDao articalDao=new ArticleDao();
		ArticleBean articalBean=articalDao.getById(articleid);
		req.getSession().setAttribute("articalBean", articalBean);
		
		//显示写文章的人的文章
		int userid2=articalBean.getUserid();
		List<ArticleBean> list=new ArrayList<ArticleBean>();
		//ArticleDao articalDao=new ArticleDao();
		list=articalDao.getReadListbyid(userid2);
		req.getSession().setAttribute("newArticleBeans", list);
		
		//显示别人的评论
		List<ContainBean> contains=new ArrayList<ContainBean>();
		//ContainDao containDao=new ContainDao();
		contains=containDao.getarticalContainbyId(articleid);
		req.getSession().setAttribute("contains", contains);
		resp.sendRedirect(req.getContextPath()+"/user/userwen.jsp?status=1");
		
		
	}

	//逐条显示信息
	private void lists(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException,ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		//List<TopicBean> list=new ArrayList<TopicBean>();
		ArticleDao topicDao=new ArticleDao();
		int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
		int countSize=ArticleDao.getCount();
		//Constants.PAGE_SIZE_1: 该常量表示：	一页多少条数据,这里size1为一页一条数据
		PagingBean pagingBean=new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_5);
		List<ArticleBean> topicBeans=topicDao.getListByPage(currentPage * 
				Constants.PAGE_SIZE_5,Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(req.getContextPath()+"/articleServlet?method=list");
		pagingBean.setAnd(true);
		req.getSession().setAttribute("articleBeans", topicBeans); 
		req.getSession().setAttribute("pagingBean2", pagingBean);
		
		
		//list=topicDao.getList();
		//req.setAttribute("topicBeans", list);
		//req.getRequestDispatcher("index.jsp").forward(req,resp);
		
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		List<ArticleBean> list=new ArrayList<ArticleBean>();
		//ArticleDao topicDao=new ArticleDao();
		list=topicDao.getReadList();
		req.getSession().setAttribute("newArticleBeans", list);
		req.getRequestDispatcher("Article.jsp").forward(req,resp);
	}

	//发布话题算法
	private void add(HttpServletRequest req, HttpServletResponse resp)
	throws IOException,ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		ArticleBean articleBean = new ArticleBean();
		
		String title=req.getParameter("title");
		String content=req.getParameter("intro");
		String detail=req.getParameter("information");
		//此处需要修改
		int userid=StringUtil.StringToInt(req.getParameter("userid"));
		//现在时间
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd");
		articleBean.setTitle(title);
		articleBean.setContent(content);
		articleBean.setDetail(detail);
		articleBean.setDatetime(createDate.format(new Date()));
		articleBean.setUserid(userid);
		
		ArticleDao articleDao = new ArticleDao();
		
		if(articleDao.add(articleBean))
		{
			
			resp.sendRedirect(req.getContextPath()+"/user/addbowen.jsp?status=0");
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/user/addbowen.jsp?status=1");
		}
	}

	///寻找界面未添加
		private void search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException{
			// TODO Auto-generated method stub
			req.setCharacterEncoding("UTF-8");
			String chars=req.getParameter("key");
			ArticleDao articalDao=new ArticleDao();
			List<ArticleBean> topicBeans=new ArrayList<>();
			topicBeans=articalDao.getLists(chars);
			req.setAttribute("articleBeans", topicBeans);
			if(topicBeans.size()>0){
				try
				{
					req.getRequestDispatcher("Article.jsp").forward(req, resp);
				}catch(ServletException | IOException e){
					e.printStackTrace();
				}
			}
			else
			{
				try{
					req.getRequestDispatcher("Article.jsp?status=1").forward(req, resp);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		//显示博文详情
		private void details(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException{
			req.setCharacterEncoding("UTF-8");
			//显示文章信息
			int id=StringUtil.StringToInt(req.getParameter("id"));
			ArticleDao articalDao=new ArticleDao();
			ArticleBean articalBean=articalDao.getById(id);
			req.getSession().setAttribute("articalBean", articalBean);
			
			//显示写文章的人的文章
			int userid=articalBean.getUserid();
			List<ArticleBean> list=new ArrayList<ArticleBean>();
			//ArticleDao articalDao=new ArticleDao();
			list=articalDao.getReadListbyid(userid);
			req.getSession().setAttribute("newArticleBeans", list);
			
			//显示别人的评论
			List<ContainBean> contains=new ArrayList<ContainBean>();
			ContainDao containDao=new ContainDao();
			contains=containDao.getarticalContainbyId(id);
			req.getSession().setAttribute("contains", contains);
			resp.sendRedirect(req.getContextPath()+"/user/userwen.jsp");
			
		}
		
		//显示话题列表（点击话题界面）
		private void list(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException{
			req.setCharacterEncoding("utf-8");
			//List<TopicBean> list=new ArrayList<TopicBean>();
			ArticleDao topicDao=new ArticleDao();
			int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
			int countSize=TopicDao.getCount();
			//Constants.PAGE_SIZE_1: 该常量表示：	一页多少条数据,这里size1为一页一条数据
			PagingBean pagingBean=new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_5);
			List<ArticleBean> topicBeans=topicDao.getListByPage(currentPage * 
					Constants.PAGE_SIZE_5,Constants.PAGE_SIZE_5);
			pagingBean.setPrefixUrl(req.getContextPath()+"/topicServlet?method=list");
			pagingBean.setAnd(true);
			req.setAttribute("topicBeans", topicBeans); 
			req.setAttribute("pagingBean", pagingBean);
			
			
			//list=topicDao.getList();
			//req.setAttribute("topicBeans", list);
			req.getRequestDispatcher("Article.jsp").forward(req,resp);
		}
		
		//阅读排行榜
		private void readList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException{
			// TODO Auto-generated method stub
			req.setCharacterEncoding("utf-8");
			List<ArticleBean> list=new ArrayList<ArticleBean>();
			ArticleDao topicDao=new ArticleDao();
			list=topicDao.getReadList();
			req.setAttribute("newTopicBeans", list);
			req.getRequestDispatcher("Article.jsp").forward(req,resp);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
